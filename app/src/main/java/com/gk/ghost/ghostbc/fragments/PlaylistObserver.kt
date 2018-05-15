package com.gk.ghost.ghostbc.fragments

import com.gk.ghost.ghostbc.SpotifyApi.SpotifyApi
import com.gk.ghost.ghostbc.model.PlaylistList
import rx.Observable

/**
 * Created by Gozde Kaval on 5/15/2018.
 */
class PlaylistObserver {

    fun getList(userId: String) : Observable<PlaylistList> {

        return Observable.create {
            subscriber ->

            val restApi = SpotifyApi("ac")
            val callResponse = restApi.getPlaylistList(userId)
            val response = callResponse.execute()

            if(response.isSuccessful){
                val playListListOutput = response.body()!!
                subscriber.onNext(playListListOutput)
                subscriber.onCompleted()
            }else{
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}