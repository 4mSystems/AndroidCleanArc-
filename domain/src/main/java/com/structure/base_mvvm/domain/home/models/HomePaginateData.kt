package com.structure.base_mvvm.domain.home.models

import com.structure.base_mvvm.domain.general.paginate.Links
import com.structure.base_mvvm.domain.general.paginate.Meta
import com.structure.base_mvvm.domain.general.paginate.Paginate

class HomePaginateData(
  val list: List<HomeData>, meta: Meta, links: Links
) : Paginate(meta, links)