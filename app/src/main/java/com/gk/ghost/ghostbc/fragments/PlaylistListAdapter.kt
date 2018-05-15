package com.gk.ghost.ghostbc.fragments

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.gk.ghost.ghostbc.R
import com.gk.ghost.ghostbc.extension.inflate
import com.gk.ghost.ghostbc.interfaces.PlaylistAdapterDelegate
import com.gk.ghost.ghostbc.interfaces.PlaylistAdapterItem
import com.gk.ghost.ghostbc.interfaces.PlaylistItemType
import com.gk.ghost.ghostbc.model.Playlist
import kotlinx.android.synthetic.main.playlist_row.view.*

/**
 * Created by Gozde Kaval on 5/15/2018.
 */
class PlaylistListAdapter : PlaylistAdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
       return PlayListItemHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: PlaylistAdapterItem) {
        (holder as PlayListItemHolder).bind(item as Playlist)
    }

    private inner class PlayListItemHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(parent?.inflate(R.layout.playlist_row)){
        private val name = itemView.playlistName

        fun bind(playlist : Playlist){
            name.text = playlist.name
        }
    }
}