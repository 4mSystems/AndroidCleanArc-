package com.structure.base_mvvm.domain.general.paginate

data class Meta (
  val path:String,
  val per_page:Int,
  val total:Int,
  val last_page:Int,
  val from:Int,
  val to:Int,
  val current_page:Int,
  )