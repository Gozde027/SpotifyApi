package com.gk.ghost.ghostbc.SpotifyApi

/**
 * Created by Gozde Kaval on 5/10/2018.
 */
enum class AuthScope(val scope : String) {

    //Playlists
    MODIFY_PRIVATE("playlist-modify-private"),
    READ_PRIVATE("playlist-read-private"),
    MODIFY_PUBLIC("playlist-modify-public"),
    READ_COLLABORATIVE("playlist-read-collaborative"),
    //Follow
    FOLLOW_READ("user-follow-read"),
    FOLLOW_MODIFY("user-follow-modify"),
    //Users
    USER_READ_PRIVATE("user-read-private"),
    USER_READ_EMAIL("user-read-email"),
    USER_READ_BIRTHDATE("user-read-birthdate"),
    //Listening History
    TOP_READ(" user-top-read"),
    READ_RECENTLY_PLAYED("user-read-recently-played"),
    //Library
    LIBRARY_MODIFY("user-library-modify"),
    LIBRARY_READ("user-library-read"),
    //Spotify Connect
    READ_PLAYBACK_STATE("user-read-playback-state"),
    READ_CURRENTLY_PLAYING("user-read-currently-playing"),
    MODIFY_PLAYBACK_STATE("user-modify-playback-state"),
    //Playback
    STREAMING("streaming");

    companion object {
        fun getDesiredScopeList() : Array<String> {
            return arrayOf(READ_PRIVATE.scope,READ_COLLABORATIVE.scope,USER_READ_PRIVATE.scope)
        }
    }

}
