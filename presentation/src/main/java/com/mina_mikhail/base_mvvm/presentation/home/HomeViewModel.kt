package com.mina_mikhail.base_mvvm.presentation.home

import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import com.mina_mikhail.base_mvvm.presentation.base.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

  val showPrettyPopUp = SingleLiveEvent<Void>()

  fun onShowPrettyPopUpClicked() {
    showPrettyPopUp.call()
  }
}