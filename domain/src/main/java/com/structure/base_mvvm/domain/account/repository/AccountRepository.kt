package com.structure.base_mvvm.domain.account.repository

import com.structure.base_mvvm.domain.account.entity.request.SendFirebaseTokenRequest
import com.structure.base_mvvm.domain.auth.entity.model.User
import com.structure.base_mvvm.domain.utils.BaseResponse
import com.structure.base_mvvm.domain.utils.Resource

interface AccountRepository {

  suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest): Resource<BaseResponse<Boolean>>

  suspend fun logOut(): Resource<BaseResponse<Boolean>>

  fun isFirstTime(): Boolean

  fun isLoggedIn(): Boolean

  fun saveFirebaseTokenToLocal(firebaseToken: String)

  fun getFirebaseToken(): String?

  fun saveUserToLocal(user: User)

  fun setFirstTime(isFirstTime: Boolean)

  fun clearPreferences()
}