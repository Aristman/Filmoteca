package ru.marslab.filmoteca.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import ru.marslab.filmoteca.R

class MainBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            Toast.makeText(
                context,
                context?.getString(R.string.connection_change_notify, intent.action),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}