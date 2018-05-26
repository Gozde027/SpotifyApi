package com.gk.ghost.ghostbc.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gk.ghost.ghostbc.R
import com.gk.ghost.ghostbc.extension.inflate
import kotlinx.android.synthetic.main.fragment_playlistlist.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Gozde Kaval on 5/10/2018.
 */

class PlaylistListFragment : Fragment(){

    private val playListObserver by lazy { PlaylistObserver() }
    private val trackListObserver by lazy { TracksObserver() }
    private val playList by lazy { playlistList }

    private val combineButton by lazy { combinePlaylistButton }

    private val adapter = PlaylistAdapterHolder()

    private val linearLayout = LinearLayoutManager(context)

    private var totalPlayListCount: Int = 0
    private var offset : Int = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        playList.setHasFixedSize(true)
        playList.layoutManager = linearLayout
        playList.adapter = adapter
        playList.addOnScrollListener(PlaylistScrollListener(linearLayout,{getMorePlaylist()}))

        combineButton.setOnClickListener {
            combineLists(0,3)
        }

        getMorePlaylist(true)
    }

    private fun getMorePlaylist(firstCall: Boolean = false){
        if(firstCall){
            getListFromApi()
        }else{
            if(offset < totalPlayListCount){
                getListFromApi()
            }else{
                adapter.removeShowMore()
            }
        }
    }

    private fun getListFromApi(){
        playListObserver.getList("11132687799", offset = offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            //call req. page
                            adapter.addPlayList(it.items)
                            totalPlayListCount = it.total
                            offset += 20
                            Log.i("OUTPUT","offset" + offset)
                        } ,{
                    Log.i("OUTPUT","throwable"+it.message)
                },{
                    Log.i("OUTPUT","completed")
                }
                )
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_playlistlist)
    }

    private fun combineLists(index1 : Int, index2: Int){
        /*val playList1 = adapter.getPlayList(index1)
        Log.i("PLAYLIST_TRACKS_1",playList1.id)
        Log.i("PLAYLIST_TRACKS_1",playList1.toString())

        val playList2 = adapter.getPlayList(index2)
        Log.i("PLAYLIST_TRACKS_2",playList2.id)
        Log.i("PLAYLIST_TRACKS_2",playList2.toString())

        trackListObserver.getTracks(playlistId = playList1.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                            Log.i("PLAYLIST_TRACKS_TRACKS",it.items.toString())
                        },
                        {
                            Log.i("OUTPUT","throwable"+it.message)
                        },{
                            Log.i("OUTPUT","completed")
                        }
                )*/

        playListObserver.createPlayList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            //call req. page
                            if(it){
                                Toast.makeText(context,"DONE!!!",Toast.LENGTH_SHORT).show()

                            }else{
                                Toast.makeText(context,"NOT DONE!!!",Toast.LENGTH_SHORT).show()
                            }
                        } ,{
                    Log.i("OUTPUT","throwable"+it.message)
                },{
                    Log.i("OUTPUT","completed")
                }
                )
    }
}