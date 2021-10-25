package com.structure.base_mvvm.data.remote

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.structure.base_mvvm.domain.utils.BaseResponse
import com.structure.base_mvvm.domain.utils.ErrorResponse
import com.structure.base_mvvm.domain.utils.FailureStatus
import com.structure.base_mvvm.domain.utils.Resource
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

open class BaseRemoteDataSource @Inject constructor() {

  suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    println(apiCall)
    try {
      val apiResponse = apiCall.invoke()
      println(apiResponse)
      when ((apiResponse as BaseResponse<*>).code) {
        403 -> {
          return Resource.Failure(FailureStatus.TOKEN_EXPIRED)
        }
        200 -> {
          return Resource.Success(apiResponse)
        }
        401 -> {
          return Resource.Failure(
            FailureStatus.EMPTY,
            (apiResponse as BaseResponse<*>).code,
            (apiResponse as BaseResponse<*>).message
          )
        }
        else -> {
          return Resource.Failure(FailureStatus.API_FAIL)
        }
      }
    } catch (throwable: Throwable) {
      println(throwable)
      when (throwable) {
        is HttpException -> {
          when {
            throwable.code() == 422 -> {
              val jObjError = JSONObject(throwable.response()!!.errorBody()!!.string())
              val apiResponse = jObjError.toString()
              val response = Gson().fromJson(apiResponse, BaseResponse::class.java)

              return Resource.Failure(FailureStatus.API_FAIL, throwable.code(), response.message)
            }
            throwable.code() == 401 -> {
              val errorResponse = Gson().fromJson(
                throwable.response()?.errorBody()!!.charStream().readText(),
                ErrorResponse::class.java
              )

              return Resource.Failure(
                FailureStatus.API_FAIL,
                throwable.code(),
                errorResponse.detail
              )
            }
            else -> {
              return if (throwable.response()?.errorBody()!!.charStream().readText()
                  .isNullOrEmpty()
              ) {
                Resource.Failure(FailureStatus.API_FAIL, throwable.code())
              } else {
                try {
                  val errorResponse = Gson().fromJson(
                    throwable.response()?.errorBody()!!.charStream().readText(),
                    ErrorResponse::class.java
                  )

                  Resource.Failure(FailureStatus.API_FAIL, throwable.code(), errorResponse.detail)
                } catch (ex: JsonSyntaxException) {
                  Resource.Failure(FailureStatus.API_FAIL, throwable.code())
                }
              }
            }
          }
        }

        is UnknownHostException -> {
          return Resource.Failure(FailureStatus.NO_INTERNET)
        }

        is ConnectException -> {
          return Resource.Failure(FailureStatus.NO_INTERNET)
        }

        else -> {
          return Resource.Failure(FailureStatus.OTHER)
        }
      }
    }
  }
}