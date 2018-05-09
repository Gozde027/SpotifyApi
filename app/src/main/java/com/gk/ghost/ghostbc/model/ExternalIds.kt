package com.gk.ghost.ghostbc.model

import com.squareup.moshi.Json
data class ExternalIds(
    @Json(name = "isrc") val isrc: String
)