package com.structure.base_mvvm.presentation.splash

import com.structure.base_mvvm.domain.general.use_case.GeneralUseCases
import com.structure.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val generalUseCases: GeneralUseCases) :
  BaseViewModel() {

  fun isFirstTime() = generalUseCases.checkFirstTimeUseCase()
}