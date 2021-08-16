package ru.marslab.filmoteca.ui.util

import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar

const val LOG_TAG = "MOVIE"

fun View.showMessage(message: String, longShow: Boolean = true) {
    Snackbar.make(
        this,
        message,
        if (longShow) Snackbar.LENGTH_LONG
        else Snackbar.LENGTH_SHORT,
    ).show()
}

fun View.showMessage(messageResource: Int, longShow: Boolean = true) {
    showMessage(
        this.context.getString(messageResource),
        longShow
    )
}

fun View.showMessageWithAction(
    message: String,
    actionText: String,
    length: Int = Snackbar.LENGTH_INDEFINITE,
    action: (View) -> Unit
) {
    Snackbar.make(this, message, length)
        .setAction(actionText, action)
        .show()
}

fun View.showMessageWithAction(
    messageResource: Int,
    actionTextResource: Int,
    length: Int = Snackbar.LENGTH_INDEFINITE,
    action: (View) -> Unit
) {
    with(this.context) {
        showMessageWithAction(
            getString(messageResource),
            getString(actionTextResource),
            length,
            action
        )
    }
}

fun View.viewShow() {
    if (this.visibility == View.GONE) {
        this.visibility = View.VISIBLE
    }
}

fun View.viewHide() {
    if (this.visibility == View.VISIBLE) {
        this.visibility = View.GONE
    }
}

fun logMessage(message: String) {
    Log.d(LOG_TAG, message)
}