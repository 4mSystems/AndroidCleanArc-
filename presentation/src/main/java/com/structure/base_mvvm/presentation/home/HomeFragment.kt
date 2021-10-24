package com.structure.base_mvvm.presentation.home

import androidx.fragment.app.viewModels
import codes.mina_mikhail.pretty_pop_up.PrettyPopUpHelper
import com.structure.base_mvvm.presentation.R
import com.structure.base_mvvm.presentation.base.BaseFragment
import com.structure.base_mvvm.presentation.base.extensions.getMyColor
import com.structure.base_mvvm.presentation.base.extensions.getMyString
import com.structure.base_mvvm.presentation.base.extensions.hide
import com.structure.base_mvvm.presentation.databinding.FragmentHomeBinding
import com.structure.base_mvvm.presentation.home.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

  private val viewModel: HomeViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_home

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setUpViews() {
    setUpToolBar()
  }

  private fun setUpToolBar() {
    binding.includedToolbar.toolbarTitle.text = getMyString(R.string.home)
    binding.includedToolbar.backIv.hide()
  }


  override
  fun setupObservers() {
    viewModel.showPrettyPopUp.observe(this) { showPrettyPopUp() }
  }

  private fun showPrettyPopUp() {
    PrettyPopUpHelper.Builder(childFragmentManager)
      .setStyle(PrettyPopUpHelper.Style.STYLE_1_HORIZONTAL_BUTTONS)
      .setTitle("Hello!")
      .setTitleColor(getMyColor(R.color.colorPrimaryDark))
      .setContent("Hello to my base MVVM project from my pretty pop up helper")
      .setContentColor(getMyColor(R.color.gray))
      .setPositiveButtonBackground(R.drawable.btn_accent)
      .setPositiveButtonTextColor(getMyColor(R.color.colorPrimaryDark))
      .setPositiveButton("Okay") {
        it.dismiss()
      }
      .setNegativeButtonBackground(R.drawable.btn_gray)
      .setNegativeButtonTextColor(getMyColor(R.color.white))
      .setNegativeButton(getMyString(R.string.cancel), null)
      .create()
  }
}