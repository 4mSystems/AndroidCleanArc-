package com.structure.base_mvvm.domain.account.use_case

import com.structure.base_mvvm.domain.account.repository.AccountRepository
import com.structure.base_mvvm.domain.utils.BaseResponse
import com.structure.base_mvvm.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val accountRepository: AccountRepository) {

  operator fun invoke(): Flow<Resource<BaseResponse<Boolean>>> = flow {
    emit(Resource.Loading)

    val result = accountRepository.logOut()
    if (result is Resource.Success && result.value.data) {
      accountRepository.clearPreferences()
    }

    emit(result)
  }.flowOn(Dispatchers.IO)
}