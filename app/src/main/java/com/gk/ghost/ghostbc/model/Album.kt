package com.gk.ghost.ghostbc.model

import com.squareup.moshi.Json
data class Album(
    @Json(name = "album_type") val albumType: String,
    @Json(name = "available_markets") val availableMarkets: List<String>,
    @Json(name = "external_urls") val externalUrls: ExternalUrls,
    @Json(name = "href") val href: String,
    @Json(name = "id") val id: String,
    @Json(name = "images") val images: List<Image>,
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: String,
    @Json(name = "uri") val uri: String
)