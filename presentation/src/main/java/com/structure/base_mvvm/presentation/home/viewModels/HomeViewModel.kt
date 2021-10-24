package com.structure.base_mvvm.presentation.home.viewModels

import com.structure.base_mvvm.presentation.base.BaseViewModel
import com.structure.base_mvvm.presentation.base.utils.SingleLiveEvent
import com.structure.base_mvvm.presentation.home.adapters.HomeAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

  val showPrettyPopUp = SingleLiveEvent<Void>()
  val adapter: HomeAdapter = HomeAdapter()
//TODO Continue with adapter binding adapter
  fun onShowPrettyPopUpClicked() {
    showPrettyPopUp.call()
  }
}