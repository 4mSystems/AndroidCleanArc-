package com.structure.base_mvvm.presentation.intro.intro

import com.structure.base_mvvm.domain.general.use_case.GeneralUseCases
import com.structure.base_mvvm.presentation.base.BaseViewModel
import com.structure.base_mvvm.presentation.base.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(private val generalUseCases: GeneralUseCases) : BaseViewModel() {

  val openLogIn = SingleLiveEvent<Void>()

  fun onLogInClicked() {
    openLogIn.call()
  }

  fun setFirstTime(isFirstTime: Boolean) = generalUseCases.setFirstTimeUseCase(isFirstTime)
}