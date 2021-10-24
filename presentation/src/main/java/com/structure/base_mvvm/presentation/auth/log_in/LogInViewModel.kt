package com.structure.base_mvvm.presentation.auth.log_in

import androidx.lifecycle.viewModelScope
import com.structure.base_mvvm.domain.auth.entity.model.User
import com.structure.base_mvvm.domain.auth.entity.request.LogInRequest
import com.structure.base_mvvm.domain.auth.use_case.LogInUseCase
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
class LogInViewModel @Inject constructor(
  private val logInUseCase: LogInUseCase
) : BaseViewModel() {

  var request = LogInRequest()
  private val _logInResponse = MutableStateFlow<Resource<BaseResponse<User>>>(Resource.Default)
  val logInResponse = _logInResponse

  var signUpClick = SingleLiveEvent<Void>()
  var openForgotPassword = SingleLiveEvent<Void>()

  var validationException = SingleLiveEvent<Int>()

  fun onSignUpClicked() {
    signUpClick.call()
  }

  fun onForgotPasswordClicked() {
    openForgotPassword.call()
  }

  fun onLogInClicked() {
    logInUseCase.login(request)
      .catch { exception -> validationException.value = exception.message?.toInt() }
      .onEach { result ->
        _logInResponse.value = result
      }
      .launchIn(viewModelScope)
  }
}