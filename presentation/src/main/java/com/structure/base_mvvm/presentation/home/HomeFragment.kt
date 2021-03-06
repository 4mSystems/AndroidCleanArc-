package com.structure.base_mvvm.presentation.home

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import codes.mina_mikhail.pretty_pop_up.PrettyPopUpHelper
import com.structure.base_mvvm.domain.utils.Resource
import com.structure.base_mvvm.presentation.R
import com.structure.base_mvvm.presentation.base.BaseFragment
import com.structure.base_mvvm.presentation.base.extensions.*
import com.structure.base_mvvm.presentation.base.utils.SwipeToDeleteCallback
import com.structure.base_mvvm.presentation.databinding.FragmentHomeBinding
import com.structure.base_mvvm.presentation.home.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

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
    lifecycleScope.launchWhenResumed {
      viewModel.homeResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            viewModel.homePaginateData = it.value.data
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }
    //swipe to delete
    val swipeHandler = object : SwipeToDeleteCallback(requireActivity()) {
      override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        viewModel.homePaginateData.list.removeAt(viewHolder.adapterPosition)
        viewModel.adapter.differ.submitList(viewModel.homePaginateData.list)
        viewModel.adapter.notifyItemRemoved(viewHolder.adapterPosition)
      }
    }
    val itemTouchHelper = ItemTouchHelper(swipeHandler)
    itemTouchHelper.attachToRecyclerView(binding.rcNotifications)
    binding.includedToolbar.ivAction.setOnClickListener {
      singleTedBottomPicker(requireActivity())
    }
    selectedImages.observeForever {
      Log.e("selectedImages", "selectedImages: ${selectedImages.value?.path}")
    }
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