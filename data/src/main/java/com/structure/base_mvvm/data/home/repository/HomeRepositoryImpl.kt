package com.structure.base_mvvm.data.home.repository

import com.structure.base_mvvm.data.home.data_source.remote.HomeRemoteDataSource
import com.structure.base_mvvm.domain.home.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: HomeRemoteDataSource) :
  HomeRepository