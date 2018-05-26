package com.gk.ghost.ghostbc.SpotifyApi

import com.gk.ghost.ghostbc.LocalProperties
import com.gk.ghost.ghostbc.application.MyApplication
import com.gk.ghost.ghostbc.model.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Gozde Kaval on 5/5/2018.
 */
class SpotifyApi() {

    companion object {
        private const val BASE_URL = "https://api.spotify.com/"
    }

    private val accessToken : String by lazy {

        val sharedPref = MyApplication.prefHelper.defaultPrefs()
        sharedPref.getString("TOKEN","")
    }

    private val spotifyInterface: SpotifyInterface

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
                    .addHeader("Authorization", "Bearer " + accessToken)
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

    fun getPlaylist(userId: String = LocalProperties.USER_ID, playlistId:String):Call<Playlist>{
        return spotifyInterface.getPlaylist(userId,playlistId)
    }

    fun getPlaylistList(userId: String, limit: Int, offset :Int): Call<PlaylistList> {
        return spotifyInterface.getPlaylistListMe(limit,offset)
    }

    fun getPlaylistMe(): Call<PlaylistList> {
        return spotifyInterface.getPlaylistListMe()
    }

    fun getTracksOfPlaylist(userId: String = LocalProperties.USER_ID, playlistId: String) : Call<Tracks>{
        return spotifyInterface.getTracksOfPlaylist(userId,playlistId)
    }

    //POST
    fun createPlayList(userId: String = LocalProperties.USER_ID, body : Map<String, Any>) : Call<Playlist>{
        return spotifyInterface.createNewPlayList(userId,body)
    }
}