package com.structure.base_mvvm.presentation.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.afollestad.assent.Permission
import com.afollestad.assent.askForPermissions
import com.afollestad.assent.isAllGranted
import com.afollestad.assent.showSystemAppDetailsPage
import com.structure.base_mvvm.presentation.base.utils.SingleLiveEvent
import com.structure.base_mvvm.presentation.base.utils.hideLoadingDialog
import com.structure.base_mvvm.presentation.base.utils.showLoadingDialog
import gun0912.tedbottompicker.TedRxBottomPicker
import java.util.Locale

abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

  private var _binding: VB? = null
  open val binding get() = _binding!!
  private var mRootView: View? = null
  private var hasInitializedRootView = false
  private var progressDialog: Dialog? = null
  val selectedImages = SingleLiveEvent<Uri>()

  override
  fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    if (mRootView == null) {
      initViewBinding(inflater, container)
    }

    return mRootView
  }

  private fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
    _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

    mRootView = binding.root
    binding.lifecycleOwner = this
    binding.executePendingBindings()
  }

  override
  fun onResume() {
    super.onResume()

    registerListeners()
  }

  override
  fun onPause() {
    unRegisterListeners()

    super.onPause()
  }

  override
  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    if (!hasInitializedRootView) {
      getFragmentArguments()
      setBindingVariables()
      setUpViews()
      observeAPICall()
      setupObservers()

      hasInitializedRootView = true
    }
  }

  @LayoutRes
  abstract fun getLayoutId(): Int

  open fun registerListeners() {}

  open fun unRegisterListeners() {}

  open fun getFragmentArguments() {}

  open fun setBindingVariables() {}

  open fun setUpViews() {}

  open fun observeAPICall() {}

  open fun setupObservers() {}

  fun showLoading() {
    hideLoading()
    progressDialog = showLoadingDialog(requireActivity(), null)
  }

  fun showLoading(hint: String?) {
    hideLoading()
    progressDialog = showLoadingDialog(requireActivity(), hint)
  }

  fun hideLoading() = hideLoadingDialog(progressDialog, requireActivity())

  fun setLanguage(language: String) {
    (requireActivity() as BaseActivity<*>).updateLocale(language)
  }

  val currentLanguage: Locale
    get() = Locale.getDefault()

  // check Permissions
  private fun checkGalleryPermissions(fragmentActivity: FragmentActivity): Boolean {
    fragmentActivity.askForPermissions(
      Permission.WRITE_EXTERNAL_STORAGE,
      Permission.READ_EXTERNAL_STORAGE,
      Permission.CAMERA
    ) {}
    return if (fragmentActivity.isAllGranted(
        Permission.WRITE_EXTERNAL_STORAGE,
        Permission.READ_EXTERNAL_STORAGE,
        Permission.CAMERA
      )
    )
      true
    else {
      fragmentActivity.showSystemAppDetailsPage()
      false
    }
  }

  // Pick Single image
  @SuppressLint("CheckResult")
  fun singleTedBottomPicker(fragmentActivity: FragmentActivity) {
    if (checkGalleryPermissions(fragmentActivity)) {
      TedRxBottomPicker.with(fragmentActivity)
        .show()
        .subscribe({
          selectedImages.value = it
        }, Throwable::printStackTrace)
    }
  }

  @SuppressLint("CheckResult")
  fun multiTedBottomPicker(fragmentActivity: FragmentActivity) {
    if (checkGalleryPermissions(fragmentActivity)) {
      TedRxBottomPicker.with(fragmentActivity)
        .show()
        .subscribe({
          selectedImages.value = it
        }, Throwable::printStackTrace)
    }
  }

}