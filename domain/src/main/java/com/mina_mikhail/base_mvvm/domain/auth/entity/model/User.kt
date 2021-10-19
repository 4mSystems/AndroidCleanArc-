package com.mina_mikhail.base_mvvm.domain.auth.entity.model

data class User(
  val email: String,
  val id: Int,
  val image: String,
  val latitude: Any,
  val longitude: Any,
  val name: String,
  val phone: String,
  val token: String
)