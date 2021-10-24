package com.structure.base_mvvm.domain.auth.repository

import com.structure.base_mvvm.domain.auth.entity.model.User
import com.structure.base_mvvm.domain.auth.entity.request.ForgetPasswordRequest
import com.structure.base_mvvm.domain.auth.entity.request.LogInRequest
import com.structure.base_mvvm.domain.utils.BaseResponse
import com.structure.base_mvvm.domain.utils.Resource

interface AuthRepository {

  suspend fun logIn(request: LogInRequest): Resource<BaseResponse<User>>
  suspend fun forgetPassword(request: ForgetPasswordRequest): Resource<BaseResponse<*>>
}