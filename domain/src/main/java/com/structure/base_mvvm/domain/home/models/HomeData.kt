package com.structure.base_mvvm.domain.home.models

data class HomeData(
  val created_at: String,
  val text: String,
  val id: Int,
  val type: String,
  val user_id: Int,
  val order_id: Int,
  val order_service_id: Int
)