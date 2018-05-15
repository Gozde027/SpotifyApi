@file:JvmName("ExtensionsUtils")
package com.gk.ghost.ghostbc.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Gozde Kaval on 5/10/2018.
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}