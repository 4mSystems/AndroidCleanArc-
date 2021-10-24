package com.structure.base_mvvm.data.search.repository

import com.structure.base_mvvm.data.search.data_source.remote.SearchRemoteDataSource
import com.structure.base_mvvm.domain.search.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val remoteDataSource: SearchRemoteDataSource) :
  SearchRepository