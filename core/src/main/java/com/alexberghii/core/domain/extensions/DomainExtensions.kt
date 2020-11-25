package com.alexberghii.core.domain.extensions

import com.alexberghii.core.database.cats.DbCatItem
import com.alexberghii.core.domain.mapper.DbCatItemToDomainMapper
import com.alexberghii.core.domain.mapper.DomainCatItemToDbMapper
import com.alexberghii.core.domain.mapper.NetworkCatItemToDomainMapper
import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.core.network.response.CatResponse


fun CatResponse.mapToDomain() = NetworkCatItemToDomainMapper.map(this)

fun CatItem.mapToDb() = DomainCatItemToDbMapper.map(this)

fun DbCatItem.mapToDomain() = DbCatItemToDomainMapper.map(this)