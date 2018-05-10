package com.gk.ghost.ghostbc.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.gk.ghost.ghostbc.R
import com.gk.ghost.ghostbc.fragments.PlaylistListFragment

/**
 * Created by Gozde Kaval on 5/10/2018.
 */
class PlaylistListActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_playlistlist)
        addFragment(PlaylistListFragment())
    }

    private fun addFragment(f: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.replace(R.id.playlistFrame, f)
        ft.addToBackStack(null)
        ft.commit()
    }
}