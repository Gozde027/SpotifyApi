package com.gk.ghost.ghostbc.model

import com.squareup.moshi.Json
data class AddedBy(
    @Json(name = "external_urls") val externalUrls: ExternalUrls,
    @Json(name = "href") val href: String,
    @Json(name = "id") val id: String,
    @Json(name = "type") val type: String,
    @Json(name = "uri") val uri: String
)