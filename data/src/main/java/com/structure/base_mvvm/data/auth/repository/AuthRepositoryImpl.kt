package com.structure.base_mvvm.data.auth.repository

import com.structure.base_mvvm.data.auth.data_source.remote.AuthRemoteDataSource
import com.structure.base_mvvm.domain.auth.entity.request.ForgetPasswordRequest
import com.structure.base_mvvm.domain.auth.entity.request.LogInRequest
import com.structure.base_mvvm.domain.auth.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

  override
  suspend fun logIn(request: LogInRequest) = remoteDataSource.logIn(request)
  override suspend fun forgetPassword(request: ForgetPasswordRequest) =
    remoteDataSource.forgetPassword(request)
}