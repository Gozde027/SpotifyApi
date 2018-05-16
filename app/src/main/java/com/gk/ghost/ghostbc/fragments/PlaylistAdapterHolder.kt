package com.gk.ghost.ghostbc.fragments

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.gk.ghost.ghostbc.interfaces.PlaylistAdapterDelegate
import com.gk.ghost.ghostbc.interfaces.PlaylistAdapterItem
import com.gk.ghost.ghostbc.interfaces.PlaylistItemType
import com.gk.ghost.ghostbc.model.Playlist

/**
 * Created by Gozde Kaval on 5/15/2018.
 */
class PlaylistAdapterHolder : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private var items : ArrayList<PlaylistAdapterItem>
    private var delegateAdapters = SparseArrayCompat<PlaylistAdapterDelegate>()

    private var showMoreItem = object : PlaylistAdapterItem{
        override fun getViewType() = PlaylistItemType.SHOW_MORE.ordinal
    }

    init {
        delegateAdapters.put(PlaylistItemType.PLAYLIST_ROW.ordinal,PlaylistListAdapter())
        delegateAdapters.put(PlaylistItemType.SHOW_MORE.ordinal,PlaylistShowMoreAdapter())

        items = ArrayList()
        items.add(showMoreItem)
    }

    fun addPlayList(movieList : List<Playlist>){
        val lastItem = items.size - 1
        items.removeAt(lastItem)
        items.addAll(movieList)
        items.add(showMoreItem)
        notifyDataSetChanged()
    }

    fun removeShowMore(){
        items.remove(showMoreItem)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
        delegateAdapters.get(viewType).onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        delegateAdapters.get(this.items[position].getViewType()).onBindViewHolder(holder,this.items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}