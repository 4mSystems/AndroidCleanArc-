package com.structure.base_mvvm.presentation.auth.log_in

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.structure.base_mvvm.domain.auth.enums.AuthFieldsValidation
import com.structure.base_mvvm.domain.utils.Resource
import com.structure.base_mvvm.presentation.R
import com.structure.base_mvvm.presentation.base.BaseFragment
import com.structure.base_mvvm.presentation.base.extensions.handleApiError
import com.structure.base_mvvm.presentation.base.extensions.hideKeyboard
import com.structure.base_mvvm.presentation.base.extensions.navigateSafe
import com.structure.base_mvvm.presentation.base.extensions.openActivityAndClearStack
import com.structure.base_mvvm.presentation.base.extensions.showSnackBar
import com.structure.base_mvvm.presentation.databinding.FragmentLogInBinding
import com.structure.base_mvvm.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>() {

  private val viewModel: LogInViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_log_in

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setupObservers() {
    viewModel.openForgotPassword.observe(this) {
      openForgotPassword()
    }
    viewModel.signUpClick.observe(this) {
      openSignUp()
    }

    viewModel.validationException.observe(this) {
      when (it) {
        AuthFieldsValidation.EMPTY_EMAIL.value -> {
          requireView().showSnackBar(resources.getString(R.string.empty_email))
        }
        AuthFieldsValidation.INVALID_EMAIL.value -> {
          requireView().showSnackBar(resources.getString(R.string.invalid_email))
        }
        AuthFieldsValidation.EMPTY_PASSWORD.value -> {
          requireView().showSnackBar(resources.getString(R.string.empty_password))
        }
      }
    }

    lifecycleScope.launchWhenResumed {
      viewModel.logInResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            openHome()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it, retryAction = { viewModel.onLogInClicked() })
          }
        }
      }
    }
  }

  private fun openForgotPassword() {
    navigateSafe(LogInFragmentDirections.actionOpenForgotPasswordFragment())
  }

  private fun openSignUp() {
    navigateSafe(LogInFragmentDirections.actionOpenSignUpFragment())
  }

  private fun openHome() {
    requireActivity().openActivityAndClearStack(HomeActivity::class.java)
  }
}