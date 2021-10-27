package com.structure.base_mvvm.domain.home.models

import com.google.gson.annotations.SerializedName
import com.structure.base_mvvm.domain.general.paginate.Links
import com.structure.base_mvvm.domain.general.paginate.Meta
import com.structure.base_mvvm.domain.general.paginate.Paginate

class HomePaginateData(
  @SerializedName("data")
  val list: ArrayList<HomeData> = arrayListOf(), meta: Meta= Meta(), links: Links=Links()
) : Paginate(meta, links)