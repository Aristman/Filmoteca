package ru.marslab.filmoteca.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.domain.repository.Constants


class MainFCMService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        val messageData = message.data
        if (messageData.isNotEmpty()) {
            handleMessage(messageData.toMap())
        }
    }

    private fun handleMessage(messageData: Map<String, String>) {
        val title = messageData[Constants.PUSH_KEY_TITLE]
        val body = messageData[Constants.PUSH_KEY_BODY]
        if (!title.isNullOrBlank() && !body.isNullOrBlank()) {
            showNotification(title, body)
        }
    }

    private fun showNotification(title: String, body: String) {
        val notificationBuilder =
            NotificationCompat.Builder(applicationContext, Constants.PUSH_KEY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_films_24)
                .setContentTitle(title)
                .setContentText(body)
                .setStyle(
                    NotificationCompat
                        .BigTextStyle()
                        .bigText(body)
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }
        notificationManager.notify(Constants.PUSH_KEY_NOTIFICATION_ID, notificationBuilder.build())
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val name = "Channel name"
        val descriptionText = "Channel description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(Constants.PUSH_KEY_CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        notificationManager.createNotificationChannel(channel)
    }

    override fun onNewToken(token: String) {
    }

}