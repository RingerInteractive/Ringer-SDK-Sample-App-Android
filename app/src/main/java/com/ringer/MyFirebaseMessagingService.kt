package com.ringer

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ringer.sdk.firebase.LibrarySDKMessagingService
import com.ringer.sdk.pref.Preferences

class MyFirebaseMessagingService : FirebaseMessagingService() {

    // [START receive_message]
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")
        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
                LibrarySDKMessagingService().sendNotification(this, remoteMessage,applicationContext.resources.getString(
                    R.string.default_notification_channel_id), applicationContext.resources.getString(R.string.app_name))
        }else
            Log.d(TAG, "Message data payload: empty")

    }
    // [END receive_message]

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        Preferences().setFCMToken(this,token)
    }
    // [END on_new_token]

    companion object {
        private const val TAG = "RingerFirebaseMsgService"
    }

}
