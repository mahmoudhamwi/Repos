package com.challenge.repos.utils

import android.view.View

/**
 * Created by Mahmoud Hamwi on 16-Jul-22.
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    hide(true)
}

fun View.hide(gone: Boolean) {
    if (gone) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.INVISIBLE
    }
}
