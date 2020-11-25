package com.alexberghii.core.network.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatResponse(
    @Json(name = "id")
    val id: String? = "",
    @Json(name = "url")
    val url: String? = "",
    @Json(name = "height")
    val height: Int? = 0,
    @Json(name = "width")
    val width: Int? = 0
)