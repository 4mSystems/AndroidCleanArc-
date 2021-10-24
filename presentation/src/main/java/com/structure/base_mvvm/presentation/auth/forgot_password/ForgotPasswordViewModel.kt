package com.structure.base_mvvm.presentation.auth.forgot_password

import androidx.lifecycle.viewModelScope
import com.structure.base_mvvm.domain.auth.entity.model.User
import com.structure.base_mvvm.domain.auth.entity.request.ForgetPasswordRequest
import com.structure.base_mvvm.domain.auth.entity.request.LogInRequest
import com.structure.base_mvvm.domain.auth.repository.AuthRepository
import com.structure.base_mvvm.domain.auth.use_case.ForgetPasswordUseCase
import com.structure.base_mvvm.domain.utils.BaseResponse
import com.structure.base_mvvm.domain.utils.Resource
import com.structure.base_mvvm.presentation.base.BaseViewModel
import com.structure.base_mvvm.presentation.base.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val forgetPasswordUseCase: ForgetPasswordUseCase) :
  BaseViewModel() {
  var request = ForgetPasswordRequest()
  private val _forgetPasswordResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val forgetPasswordResponse = _forgetPasswordResponse
  var validationException = SingleLiveEvent<Int>()

  val backToPreviousScreen = SingleLiveEvent<Void>()

  fun onBackClicked() {
    backToPreviousScreen.call()
  }

  fun sendCode() {
    forgetPasswordUseCase(request)
      .catch { exception -> validationException.value = exception.message?.toInt() }
      .onEach { result ->
        _forgetPasswordResponse.value = result
      }
      .launchIn(viewModelScope)
  }
}