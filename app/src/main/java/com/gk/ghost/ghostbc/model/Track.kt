package com.gk.ghost.ghostbc.model

import com.squareup.moshi.Json
data class Track(
    @Json(name = "album") val album: Album,
    @Json(name = "artists") val artists: List<Artist>,
    @Json(name = "available_markets") val availableMarkets: List<String>,
    @Json(name = "disc_number") val discNumber: Int,
    @Json(name = "duration_ms") val durationMs: Int,
    @Json(name = "explicit") val explicit: Boolean,
    @Json(name = "external_ids") val externalIds: ExternalIds,
    @Json(name = "external_urls") val externalUrls: ExternalUrls,
    @Json(name = "href") val href: String,
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "popularity") val popularity: Int,
    @Json(name = "preview_url") val previewUrl: String,
    @Json(name = "track_number") val trackNumber: Int,
    @Json(name = "type") val type: String,
    @Json(name = "uri") val uri: String
)