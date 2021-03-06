package com.structure.base_mvvm.core.di.module

import com.structure.base_mvvm.data.account.data_source.remote.AccountRemoteDataSource
import com.structure.base_mvvm.data.account.repository.AccountRepositoryImpl
import com.structure.base_mvvm.data.auth.data_source.remote.AuthRemoteDataSource
import com.structure.base_mvvm.data.auth.repository.AuthRepositoryImpl
import com.structure.base_mvvm.data.general.data_source.remote.GeneralRemoteDataSource
import com.structure.base_mvvm.data.general.repository.GeneralRepositoryImpl
import com.structure.base_mvvm.data.home.data_source.remote.HomeRemoteDataSource
import com.structure.base_mvvm.data.home.repository.HomeRepositoryImpl
import com.structure.base_mvvm.data.local.preferences.AppPreferences
import com.structure.base_mvvm.data.search.data_source.remote.SearchRemoteDataSource
import com.structure.base_mvvm.data.search.repository.SearchRepositoryImpl
import com.structure.base_mvvm.domain.account.repository.AccountRepository
import com.structure.base_mvvm.domain.auth.repository.AuthRepository
import com.structure.base_mvvm.domain.general.repository.GeneralRepository
import com.structure.base_mvvm.domain.home.repository.HomeRepository
import com.structure.base_mvvm.domain.search.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

  @Provides
  @Singleton
  fun provideGeneralRepository(
    remoteDataSource: GeneralRemoteDataSource,
    appPreferences: AppPreferences
  ): GeneralRepository = GeneralRepositoryImpl(remoteDataSource, appPreferences)

  @Provides
  @Singleton
  fun provideAuthRepository(
    remoteDataSource: AuthRemoteDataSource,
  ): AuthRepository = AuthRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideAccountRepository(
    remoteDataSource: AccountRemoteDataSource,
    appPreferences: AppPreferences
  ): AccountRepository = AccountRepositoryImpl(remoteDataSource, appPreferences)

  @Provides
  @Singleton
  fun provideSearchRepository(
    remoteDataSource: SearchRemoteDataSource
  ): SearchRepository = SearchRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideHomeRepository(
    remoteDataSource: HomeRemoteDataSource
  ): HomeRepository = HomeRepositoryImpl(remoteDataSource)

}