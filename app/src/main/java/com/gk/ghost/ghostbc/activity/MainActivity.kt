package com.gk.ghost.ghostbc.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.gk.ghost.ghostbc.LocalProperties
import com.gk.ghost.ghostbc.R
import com.gk.ghost.ghostbc.SpotifyApi.AuthScope
import com.gk.ghost.ghostbc.SpotifyApi.SpotifyObserver
import com.gk.ghost.ghostbc.application.MyApplication
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.player.ConnectionStateCallback
import com.spotify.sdk.android.player.Error
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity(), ConnectionStateCallback {

    private val spotifyObserver by lazy {
        SpotifyObserver()
    }

    companion object {
        const val REQUEST_CODE = 1234
        const val TAG = "GOZDESPOTIFY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews(){

        authButton.setOnClickListener(View.OnClickListener {
            requestLogin()
        })

        textView.text = "BEFORE"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE){
            val authResponse = AuthenticationClient.getResponse(resultCode,data)
            if(authResponse.type == AuthenticationResponse.Type.TOKEN){

                val editor = MyApplication.prefHelper.defaultPrefs().edit()
                editor.putString("TOKEN",authResponse.accessToken)
                editor.apply()

                openPlayListActivity()

            }
        }
    }

    private fun requestLogin(){
        val builder = AuthenticationRequest.Builder(LocalProperties.CLIENT_ID,
                AuthenticationResponse.Type.TOKEN, LocalProperties.REDIRECT_URI)
        builder.setScopes(AuthScope.getDesiredScopeList())
        val request = builder.build()
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE,request)
    }

    fun tryApi(accessToken : String){
        spotifyObserver.getUserInfo(accessToken = accessToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    openPlayListActivity()
                }
                ,{
                    Log.i("OUTPUT","throwable"+it.message)
                },{
                    Log.i("OUTPUT","completed")
                }
            )
    }

    private fun openPlayListActivity(){

        val intent = Intent(applicationContext, PlaylistListActivity::class.java)
        startActivity(intent)
    }

    override fun onLoggedOut() {
        Log.i(TAG,"onLoggedOut")
    }

    override fun onLoggedIn() {
        Log.i(TAG,"onLoggedIn")
       // mPlayer.playUri(null, "spotify:track:2TpxZ7JUBn3uw46aR7qd6V", 0, 0);
    }

    override fun onConnectionMessage(p0: String?) {
        Log.i(TAG,"onConnectionMessage")
    }

    override fun onLoginFailed(p0: Error?) {
        Log.i(TAG,"onLoginFailed")
    }

    override fun onTemporaryError() {
        Log.i(TAG,"onTemporaryError")
    }

}
