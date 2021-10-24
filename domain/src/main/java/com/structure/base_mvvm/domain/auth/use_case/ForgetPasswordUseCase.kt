package com.structure.base_mvvm.domain.auth.use_case

import com.structure.base_mvvm.domain.auth.entity.request.ForgetPasswordRequest
import com.structure.base_mvvm.domain.auth.entity.request.ForgetPasswordValidationException
import com.structure.base_mvvm.domain.auth.entity.request.LogInValidationException
import com.structure.base_mvvm.domain.auth.enums.AuthFieldsValidation
import com.structure.base_mvvm.domain.auth.repository.AuthRepository
import com.structure.base_mvvm.domain.utils.BaseResponse
import com.structure.base_mvvm.domain.utils.Resource
import com.structure.base_mvvm.domain.utils.isValidEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ForgetPasswordUseCase @Inject constructor(
  private val authRepository: AuthRepository) {

  @Throws(LogInValidationException::class)
  operator fun invoke(request: ForgetPasswordRequest): Flow<Resource<BaseResponse<*>>> = flow {
    if (request.phone.isEmpty()) {
      throw ForgetPasswordValidationException(AuthFieldsValidation.EMPTY_EMAIL.value.toString())
    }

    if (!request.phone.isValidEmail()) {
      throw ForgetPasswordValidationException(AuthFieldsValidation.INVALID_EMAIL.value.toString())
    }
    emit(Resource.Loading)
    val result = authRepository.forgetPassword(request)
    emit(result)
  }.flowOn(Dispatchers.IO)
}