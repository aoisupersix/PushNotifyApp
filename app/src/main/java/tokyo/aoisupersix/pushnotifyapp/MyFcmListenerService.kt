package tokyo.aoisupersix.pushnotifyapp

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

import kotlin.collections.Map

class MyFcmListenerService: FirebaseMessagingService() {
    private val tag: String = MyInstanceIDListenerService::class.simpleName!!

    override fun onMessageReceived(p0: RemoteMessage?) {
        val title: String? = p0?.notification?.title
        val body: String? = p0?.notification?.body

        Log.d(tag, "Message-Title: $title")
        Log.d(tag, "data: $body")

        sendNotification(title, body)
    }

    private fun sendNotification(title: String?, body: String?) {
        val intentArray: Array<Intent?> = arrayOfNulls(1)
        intentArray[0] = Intent(this, MainActivity::class.java)
        intentArray[0]?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivities(this, 0, intentArray, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setStyle(NotificationCompat.BigTextStyle().bigText(body))
                .setContentIntent(pendingIntent)

        val notificationManager: NotificationManager? = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager?.notify(0, notificationBuilder.build())
    }
}