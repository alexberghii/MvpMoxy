package com.alexberghii.core.network.repository

import com.alexberghii.core.network.AppService
import com.alexberghii.core.network.extension.getResult


class CatsRepository(private val appService: AppService) {

    fun getCats(page: Int, itemsPerPage: Int) = appService.getCats(page, itemsPerPage).map { it.getResult() }
}