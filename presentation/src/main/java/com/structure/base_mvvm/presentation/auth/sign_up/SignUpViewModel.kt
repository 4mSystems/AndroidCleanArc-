package com.structure.base_mvvm.presentation.auth.sign_up

import com.structure.base_mvvm.domain.auth.repository.AuthRepository
import com.structure.base_mvvm.presentation.base.BaseViewModel
import com.structure.base_mvvm.presentation.base.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) : BaseViewModel() {

  val backToPreviousScreen = SingleLiveEvent<Void>()

  fun onBackClicked() {
    backToPreviousScreen.call()
  }
}