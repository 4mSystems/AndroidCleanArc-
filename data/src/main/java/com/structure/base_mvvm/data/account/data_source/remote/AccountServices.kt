package com.structure.base_mvvm.data.account.data_source.remote

import com.structure.base_mvvm.domain.account.entity.request.SendFirebaseTokenRequest
import com.structure.base_mvvm.domain.utils.BaseResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT

interface AccountServices {

  @PUT("Account/FirebaseToken/Update")
  suspend fun sendFirebaseToken(@Body request: SendFirebaseTokenRequest): BaseResponse<Boolean>

  @DELETE("Account/Logout")
  suspend fun logOut(): BaseResponse<Boolean>
}