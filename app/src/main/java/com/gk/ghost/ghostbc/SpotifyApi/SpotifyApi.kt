package com.gk.ghost.ghostbc.SpotifyApi

import com.gk.ghost.ghostbc.model.Playlist
import com.gk.ghost.ghostbc.model.PlaylistList
import com.gk.ghost.ghostbc.model.Track
import com.gk.ghost.ghostbc.model.User
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Gozde Kaval on 5/5/2018.
 */
class SpotifyApi(accessToken : String) {

    companion object {
        private const val BASE_URL = "https://api.spotify.com/"
        private const val USER_ID = ""
    }

    private val spotifyInterface: SpotifyInterface
    private val clientAccessToken = accessToken

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory (MoshiConverterFactory.create())
                .client(getClient())
                .build()

        spotifyInterface = retrofit.create(SpotifyInterface::class.java)
    }

    private fun getClient() : OkHttpClient{
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor {
            val original = it.request()
            val request = original.newBuilder()
                    //"Bearer " + clientAccessToken
                    .addHeader("Authorization", "Bearer " + clientAccessToken)
                  //  .addHeader("Scope", "playlist-read-private")
                    .method(original.method(),original.body())
                    .build()
             it.proceed(request)
        })

        return httpClient.build()
    }

    fun getUserInformation(): Call<User> {
        return spotifyInterface.getMe()
    }

    fun getTrack(trackId:String = "3n3Ppam7vgaVa1iaRUc9Lp"): Call<Track> {
        return spotifyInterface.getTrack(trackId)
    }

    fun getPlaylist(playlistId:String):Call<Playlist>{
        return spotifyInterface.getPlaylist(USER_ID,playlistId)
    }

    fun getPlaylistList(userId: String): Call<PlaylistList> {
        return spotifyInterface.getPlaylistListMe()
    }

    fun getPlaylistMe(): Call<PlaylistList> {
        return spotifyInterface.getPlaylistListMe()
    }
}