package com.structure.base_mvvm.data.general.repository

import com.structure.base_mvvm.data.general.data_source.remote.GeneralRemoteDataSource
import com.structure.base_mvvm.data.local.preferences.AppPreferences
import com.structure.base_mvvm.domain.general.repository.GeneralRepository
import javax.inject.Inject

class GeneralRepositoryImpl @Inject constructor(
  private val remoteDataSource: GeneralRemoteDataSource,
  private val appPreferences: AppPreferences
) : GeneralRepository