package com.gk.ghost.ghostbc.fragments

import com.gk.ghost.ghostbc.SpotifyApi.SpotifyApi
import com.gk.ghost.ghostbc.model.PlaylistList
import rx.Observable


/**
 * Created by Gozde Kaval on 5/15/2018.
 */
class PlaylistObserver {

    fun getList(userId: String, limit: Int = 20, offset : Int = 0) : Observable<PlaylistList> {

        return Observable.create {
            subscriber ->

            val restApi = SpotifyApi()
            val callResponse = restApi.getPlaylistList(userId,limit,offset)
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

    fun createPlayList() : Observable<Boolean>{
        return Observable.create {
            subscriber ->

            val options = HashMap<String, Any>()
            options["name"] = "My first new playlist"
            options["description"] = "My first new playlist description"
           // options["public"] = true

            val restApi = SpotifyApi()
            val callResponse = restApi.createPlayList(body = options)
            val response = callResponse.execute()

            if(response.isSuccessful){

                subscriber.onNext(true)
                subscriber.onCompleted()
            }else{
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}