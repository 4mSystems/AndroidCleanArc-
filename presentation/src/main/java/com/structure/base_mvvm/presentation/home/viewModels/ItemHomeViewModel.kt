package com.structure.base_mvvm.presentation.home.viewModels

import com.structure.base_mvvm.domain.home.models.HomeData
import com.structure.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemHomeViewModel @Inject constructor(val homeData: HomeData) : BaseViewModel()