package com.structure.base_mvvm.presentation.auth.forgot_password

import androidx.fragment.app.viewModels
import com.structure.base_mvvm.domain.auth.enums.AuthFieldsValidation
import com.structure.base_mvvm.presentation.R
import com.structure.base_mvvm.presentation.base.BaseFragment
import com.structure.base_mvvm.presentation.base.extensions.backToPreviousScreen
import com.structure.base_mvvm.presentation.base.extensions.showSnackBar
import com.structure.base_mvvm.presentation.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {

  private val viewModel: ForgotPasswordViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_forgot_password

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setupObservers() {
    viewModel.backToPreviousScreen.observe(this) { backToPreviousScreen() }
    viewModel.validationException.observe(this) {
      when (it) {
        AuthFieldsValidation.EMPTY_EMAIL.value -> {
          requireView().showSnackBar(resources.getString(R.string.empty_email))
        }
        AuthFieldsValidation.INVALID_EMAIL.value -> {
          requireView().showSnackBar(resources.getString(R.string.invalid_email))
        }
      }
    }
    //TODO LISTEN to FORGET
  }
}