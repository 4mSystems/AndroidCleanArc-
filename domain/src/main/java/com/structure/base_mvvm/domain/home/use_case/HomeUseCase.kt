package com.structure.base_mvvm.domain.home.use_case

import com.structure.base_mvvm.domain.home.models.HomePaginateData
import com.structure.base_mvvm.domain.home.repository.HomeRepository
import com.structure.base_mvvm.domain.utils.BaseResponse
import com.structure.base_mvvm.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class HomeUseCase @Inject constructor(
  private val homeRepository: HomeRepository
) {

  fun getHome(page: Int, showProgress: Boolean): Flow<Resource<BaseResponse<HomePaginateData>>> =
    flow {
      emit(Resource.Loading)
      val result = homeRepository.home(page)

      emit(result)
    }.flowOn(Dispatchers.IO)
}