package com.gk.ghost.ghostbc.model

import com.squareup.moshi.Json
data class Followers(
    @Json(name = "href") val href: Any?,
    @Json(name = "total") val total: Int
)