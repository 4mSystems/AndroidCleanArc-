package com.structure.base_mvvm.data.home.data_source.remote

import com.structure.base_mvvm.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: HomeServices) :
  BaseRemoteDataSource() {

  suspend fun home(page: Int) = safeApiCall {
    apiService.home(page)
  }
}