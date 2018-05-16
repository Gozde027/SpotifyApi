package com.gk.ghost.ghostbc.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private val playList by lazy { playlistList }

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
}