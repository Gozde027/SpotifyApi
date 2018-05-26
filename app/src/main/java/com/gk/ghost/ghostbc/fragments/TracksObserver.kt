package com.gk.ghost.ghostbc.fragments

import com.gk.ghost.ghostbc.SpotifyApi.SpotifyApi
import com.gk.ghost.ghostbc.model.PlaylistList
import com.gk.ghost.ghostbc.model.Tracks
import rx.Observable

/**
 * Created by Gozde Kaval on 5/15/2018.
 */
class TracksObserver {

    fun getTracks(userId: String = "", playlistId : String) : Observable<Tracks> {

        return Observable.create {
            subscriber ->

            val restApi = SpotifyApi()
            val callResponse = restApi.getTracksOfPlaylist(playlistId= playlistId)
            val response = callResponse.execute()

            if(response.isSuccessful){
                val trackList = response.body()!!
                subscriber.onNext(trackList)
                subscriber.onCompleted()
            }else{
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}