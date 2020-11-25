package com.alexberghii.core.domain.mapper

import com.alexberghii.core.domain.extensions.mapToDomain
import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.core.mapper.Mapper
import com.alexberghii.core.network.response.CatResponse


object CatItemListMapper : Mapper<List<CatResponse>, List<CatItem>> {

    override fun map(input: List<CatResponse>) = input.map { it.mapToDomain() }
}