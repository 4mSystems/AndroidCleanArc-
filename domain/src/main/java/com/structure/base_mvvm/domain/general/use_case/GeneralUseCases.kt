package com.structure.base_mvvm.domain.general.use_case

import com.structure.base_mvvm.domain.account.use_case.CheckFirstTimeUseCase
import com.structure.base_mvvm.domain.account.use_case.CheckLoggedInUserUseCase
import com.structure.base_mvvm.domain.account.use_case.SetFirstTimeUseCase

class GeneralUseCases(
  val checkFirstTimeUseCase: CheckFirstTimeUseCase,
  val checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
  val setFirstTimeUseCase: SetFirstTimeUseCase,
  val clearPreferencesUseCase: ClearPreferencesUseCase
)