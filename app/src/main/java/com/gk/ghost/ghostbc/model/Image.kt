package com.gk.ghost.ghostbc.model

import com.squareup.moshi.Json
data class Image(
    @Json(name = "height") val height: Any?,
    @Json(name = "url") val url: String,
    @Json(name = "width") val width: Any?
)