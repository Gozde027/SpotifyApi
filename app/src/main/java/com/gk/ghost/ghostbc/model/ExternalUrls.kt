package com.gk.ghost.ghostbc.model

import com.squareup.moshi.Json
data class ExternalUrls(
    @Json(name = "spotify") val spotify: String
)