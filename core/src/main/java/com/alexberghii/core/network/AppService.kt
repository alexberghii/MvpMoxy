package com.alexberghii.core.network

import com.alexberghii.core.network.response.CatResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface AppService {

    companion object {

        const val BASE_URL = "https://api.thecatapi.com/v1/"
        const val API_KEY = "78d9774d-fa94-46dd-b14e-0d8b67b4fb33"
    }

    @GET("images/search")
    fun getCats(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("order") order: String = "ASC"
    ): Observable<Response<List<CatResponse>>>
}