package com.gk.ghost.ghostbc.model

import com.squareup.moshi.Json

data class Playlist(
    @Json(name = "collaborative") val collaborative: Boolean,
    @Json(name = "description") val description: String,
    @Json(name = "external_urls") val externalUrls: ExternalUrls,
    @Json(name = "followers") val followers: Followers,
    @Json(name = "href") val href: String,
    @Json(name = "id") val id: String,
    @Json(name = "images") val images: List<Image>,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: Owner,
    @Json(name = "public") val public: Any?,
    @Json(name = "snapshot_id") val snapshotId: String,
    @Json(name = "tracks") val tracks: Tracks,
    @Json(name = "type") val type: String,
    @Json(name = "uri") val uri: String
)