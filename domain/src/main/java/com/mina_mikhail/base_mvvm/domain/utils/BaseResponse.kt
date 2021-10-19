package com.mina_mikhail.base_mvvm.domain.utils

data class BaseResponse<T>(
  val data: T,
  val message: String,
  val code: Int,
)