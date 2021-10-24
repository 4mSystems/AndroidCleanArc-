package com.structure.base_mvvm.presentation.shared.web_view

import com.structure.base_mvvm.domain.general.repository.GeneralRepository
import com.structure.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(private val generalRepository: GeneralRepository) :
  BaseViewModel()