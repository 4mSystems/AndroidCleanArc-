package com.structure.base_mvvm.presentation.home.viewModels

import androidx.lifecycle.viewModelScope
import com.structure.base_mvvm.domain.home.models.HomePaginateData
import com.structure.base_mvvm.domain.home.use_case.HomeUseCase
import com.structure.base_mvvm.domain.utils.BaseResponse
import com.structure.base_mvvm.domain.utils.Resource
import com.structure.base_mvvm.presentation.base.BaseViewModel
import com.structure.base_mvvm.presentation.base.utils.SingleLiveEvent
import com.structure.base_mvvm.presentation.home.adapters.HomeAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : BaseViewModel() {

  val showPrettyPopUp = SingleLiveEvent<Void>()
  val adapter: HomeAdapter = HomeAdapter()

  private val _homeResponse =
    MutableStateFlow<Resource<BaseResponse<HomePaginateData>>>(Resource.Default)
  val homeResponse = _homeResponse

  init {
    getHome(1, true)
  }

  fun onShowPrettyPopUpClicked() {
    showPrettyPopUp.call()
  }

  fun getHome(page: Int, showProgress: Boolean) {
    homeUseCase.getHome(page, showProgress)
      .onEach { result ->
        _homeResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  var homePaginateData: HomePaginateData = HomePaginateData()
    set(value) {
      adapter.differ.submitList(value.list)
      field = value
    }
}