package com.gk.ghost.ghostbc.SpotifyApi

import com.gk.ghost.ghostbc.model.*
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

/**
 * Created by Gozde Kaval on 5/5/2018.
 */
interface SpotifyInterface {

    //https://api.spotify.com/v1/me
    @GET("/v1/me")
    fun getMe() : Call<User>

    //https://api.spotify.com/v1/me/playlists
    //https://api.spotify.com/v1/users/{user_id}/playlists
    @GET("/v1/users/{user_id}/playlists")
    fun getPlaylistList(@Path("user_id")  userId : String) : Call<PlaylistList>

    //limit	Optional. The maximum number of playlists to return.
    // Default: 20. Minimum: 1. Maximum: 50.

    //offset Optional. The index of the first playlist to return.
    // Default: 0 (the first object). Maximum offset: 100.000. Use with limit to get the next set of playlists.
    @GET("/v1/me/playlists")
    fun getPlaylistListMe(@Query("limit") limit:Int = 20, @Query("offset") offset: Int = 0) : Call<PlaylistList>

    //https://api.spotify.com/v1/tracks/{id}
    @GET("/v1/tracks/{id}")
    fun getTrack(@Path("id")  trackId: String) : Call<Track>

    //https://api.spotify.com/v1/users/{user_id}/playlists/{playlist_id}
    @GET("/v1/users/{user_id}/playlists/{playlist_id}")
    fun getPlaylist(@Path("user_id")  userId: String, @Path("playlist_id") playlist_id: String) : Call<Playlist>

    //https://api.spotify.com/v1/users/{user_id}/playlists/{playlist_id}/tracks
    @GET("/v1/users/{user_id}/playlists/{playlist_id}/tracks")
    fun getTracksOfPlaylist(@Path("user_id") userId: String, @Path("playlist_id") playlist_id: String) : Call<Tracks>

    //POST https://api.spotify.com/v1/users/{user_id}/playlists
    @Headers("content-type: application/json")
    @POST("/v1/users/{user_id}/playlists")
    fun createNewPlayList(@Path("user_id") userId: String,
                          @Body body : Map<String, @JvmSuppressWildcards Any>
        ) : Call<Playlist>
}