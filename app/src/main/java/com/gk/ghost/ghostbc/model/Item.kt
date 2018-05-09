package com.gk.ghost.ghostbc.model

import com.squareup.moshi.Json
data class Item(
    @Json(name = "added_at") val addedAt: String,
    @Json(name = "added_by") val addedBy: AddedBy,
    @Json(name = "is_local") val isLocal: Boolean,
    @Json(name = "track") val track: Track
)