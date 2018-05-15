package com.gk.ghost.ghostbc.fragments

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.gk.ghost.ghostbc.R
import com.gk.ghost.ghostbc.extension.inflate
import com.gk.ghost.ghostbc.interfaces.PlaylistAdapterDelegate
import com.gk.ghost.ghostbc.interfaces.PlaylistAdapterItem
import kotlinx.android.synthetic.main.layout_progress_view.view.*

/**
 * Created by Gozde Kaval on 5/15/2018.
 */
class PlaylistShowMoreAdapter : PlaylistAdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        return ShowMoreItemHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: PlaylistAdapterItem) {
        (holder as ShowMoreItemHolder).bind()
    }

    private inner class ShowMoreItemHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(parent?.inflate(R.layout.layout_progress_view)){
        private val progress = itemView.progressBar

        fun bind(){
            progress.isIndeterminate = true
        }
    }
}