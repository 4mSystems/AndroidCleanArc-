package com.structure.base_mvvm.data.general.data_source.remote

import com.structure.base_mvvm.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class GeneralRemoteDataSource @Inject constructor(private val apiService: GeneralServices) : BaseRemoteDataSource()