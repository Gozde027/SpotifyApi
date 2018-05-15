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

//TODO basic list add
class PlaylistListFragment : Fragment(){

    private val playListObserver by lazy { PlaylistObserver() }
    private val playList by lazy { playlistList }

    private val adapter = PlaylistAdapterHolder()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        playList.setHasFixedSize(true)
        playList.layoutManager = LinearLayoutManager(context)
        playList.adapter = adapter

        getList()
    }

    fun getList(){
        getListFromApi()
    }

    private fun getListFromApi(){
        playListObserver.getList("11132687799")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            //call req. page
                            adapter.addPlayList(it.items)

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