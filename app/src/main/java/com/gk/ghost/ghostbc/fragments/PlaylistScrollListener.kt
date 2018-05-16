package com.gk.ghost.ghostbc.fragments

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

/**
 * Created by Gozde Kaval on 5/16/2018.
 */
class PlaylistScrollListener(private val layoutManager : LinearLayoutManager,
                             private val func: () -> Unit) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true
    private var visibleThreshold = 1
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        //vertical scrolling
        if(dy > 0){
            visibleItemCount = recyclerView.childCount //21 show
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }
            if (!loading && (totalItemCount - visibleItemCount)
                            <= (firstVisibleItem  + visibleThreshold)) {
                // End has been reached
                Log.i("InfiniteScrollListener", "End reached");
                func()
                loading = true
            }
        }
    }
}