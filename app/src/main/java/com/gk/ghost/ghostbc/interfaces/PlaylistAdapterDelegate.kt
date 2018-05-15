package com.gk.ghost.ghostbc.interfaces

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Gozde Kaval on 5/15/2018.
 */
interface PlaylistAdapterDelegate {
    fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: PlaylistAdapterItem)
}