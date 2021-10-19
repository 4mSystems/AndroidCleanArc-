package com.mina_mikhail.base_mvvm.domain.auth.entity.model

data class UserResponse(
  val code: Int,
  val data: User,
  val message: String
)