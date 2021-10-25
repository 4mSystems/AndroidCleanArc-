package com.structure.base_mvvm.data.home.data_source.remote

import com.structure.base_mvvm.domain.home.models.HomePaginateData
import com.structure.base_mvvm.domain.utils.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeServices {
  @GET("v1/client/notifications?")
  suspend fun home(@Query("page=")page: Int): BaseResponse<HomePaginateData>

}