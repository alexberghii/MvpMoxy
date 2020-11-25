package com.alexberghii.core.domain.mapper

import com.alexberghii.core.database.cats.DbCatItem
import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.core.mapper.Mapper


object DbCatItemToDomainMapper : Mapper<DbCatItem, CatItem> {

    override fun map(input: DbCatItem) = CatItem(
        id = input.id,
        url = input.url,
        height = input.height,
        width = input.width,
    )
}