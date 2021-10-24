package com.structure.base_mvvm.data.auth.data_source.remote

import com.structure.base_mvvm.domain.auth.entity.model.User
import com.structure.base_mvvm.domain.auth.entity.request.ForgetPasswordRequest
import com.structure.base_mvvm.domain.auth.entity.request.LogInRequest
import com.structure.base_mvvm.domain.utils.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServices {

  @POST("v1/client/login")
  suspend fun logIn(@Body request: LogInRequest): BaseResponse<User>
  @POST("v1/client/code_send")
  suspend fun forgetPassword(@Body request: ForgetPasswordRequest): BaseResponse<*>

}