package com.alexberghii.core.domain.mapper

import com.alexberghii.core.database.cats.DbCatItem
import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.core.mapper.Mapper


object DomainCatItemToDbMapper : Mapper<CatItem, DbCatItem> {

    override fun map(input: CatItem) = DbCatItem(
        id = input.id,
        url = input.url,
        height = input.height,
        width = input.width,
    )
}