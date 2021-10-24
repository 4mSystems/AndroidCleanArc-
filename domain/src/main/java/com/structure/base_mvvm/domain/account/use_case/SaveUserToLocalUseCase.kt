package com.structure.base_mvvm.domain.account.use_case

import com.structure.base_mvvm.domain.account.repository.AccountRepository
import com.structure.base_mvvm.domain.auth.entity.model.User
import javax.inject.Inject

class SaveUserToLocalUseCase @Inject constructor(private val accountRepository: AccountRepository) {
  operator fun invoke(user: User) = accountRepository.saveUserToLocal(user)
  operator fun invoke(firebaseToken: String) =
    accountRepository.saveFirebaseTokenToLocal(firebaseToken)
}