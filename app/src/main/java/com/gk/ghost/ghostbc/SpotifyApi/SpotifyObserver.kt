package com.gk.ghost.ghostbc.SpotifyApi

import com.gk.ghost.ghostbc.model.Playlist
import com.gk.ghost.ghostbc.model.PlaylistList
import com.gk.ghost.ghostbc.model.Track
import com.gk.ghost.ghostbc.model.User
import rx.Observable

/**
 * Created by Gozde Kaval on 5/5/2018.
 */
class SpotifyObserver {

    fun getTrack(trackId : String = "3n3Ppam7vgaVa1iaRUc9Lp", accessToken : String) : Observable<Track>{

        return Observable.create {
            subscriber ->

            val restApi = SpotifyApi()
            val callResponse = restApi.getTrack(trackId)
            val response = callResponse.execute()

            if(response.isSuccessful){
                val searchOutput = response.body()
                subscriber.onNext(searchOutput)
                subscriber.onCompleted()
            }else{
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    fun getUserInfo(accessToken : String) : Observable<User>{

        return Observable.create {
            subscriber ->

            val restApi = SpotifyApi()
            val callResponse = restApi.getUserInformation()
            val response = callResponse.execute()

            if(response.isSuccessful){
                val searchOutput = response.body()
                subscriber.onNext(searchOutput)
                subscriber.onCompleted()
            }else{
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    fun getPlaylistList(userId: String, accessToken : String) : Observable<PlaylistList>{

        return Observable.create {
            subscriber ->

            val restApi = SpotifyApi()
            val callResponse = restApi.getPlaylistList(userId,2,2)
            val response = callResponse.execute()

            if(response.isSuccessful){
                val searchOutput = response.body()
                subscriber.onNext(searchOutput)
                subscriber.onCompleted()
            }else{
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    fun getMyPlaylist(accessToken : String) : Observable<PlaylistList>{

        return Observable.create {
            subscriber ->

            val restApi = SpotifyApi()
            val callResponse = restApi.getPlaylistMe()
            val response = callResponse.execute()

            if(response.isSuccessful){
                val searchOutput = response.body()
                subscriber.onNext(searchOutput)
                subscriber.onCompleted()
            }else{
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}