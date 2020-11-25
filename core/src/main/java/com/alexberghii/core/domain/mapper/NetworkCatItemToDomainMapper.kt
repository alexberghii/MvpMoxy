package com.alexberghii.core.domain.mapper

import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.core.mapper.Mapper
import com.alexberghii.core.network.response.CatResponse


object NetworkCatItemToDomainMapper : Mapper<CatResponse, CatItem> {

    override fun map(input: CatResponse) = CatItem(
        id = input.id ?: "",
        url = input.url ?: "",
        height = input.height ?: 0,
        width = input.width ?: 0,
    )
}