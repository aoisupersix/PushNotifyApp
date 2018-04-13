package tokyo.aoisupersix.pushnotifyapp

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.support.v4.app.NotificationCompat
import android.util.Log

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFcmListenerService: FirebaseMessagingService() {
    private val tag: String = MyInstanceIDListenerService::class.simpleName!!

    override fun onMessageReceived(p0: RemoteMessage?) {
        val title: String? = p0?.notification?.title ?: return
        val body: String? = p0?.notification?.body ?: return
        val time: String? = p0?.data["time"] ?:return

        LocationInfoManager.remoteMessages.add(p0)

        Log.d(tag, "Message-Title: $title")
        Log.d(tag, "data: $body")
        Log.d(tag, "time: $time")

        sendNotification(title, body, time)
    }

    private fun sendNotification(title: String?, body: String?, time: String?) {
        val intentArray: Array<Intent?> = arrayOfNulls(1)
        intentArray[0] = Intent(this, MainActivity::class.java)
        intentArray[0]?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivities(this, 0, intentArray, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(Notification.PRIORITY_HIGH)
                .setSound(defaultSoundUri)
                .setStyle(NotificationCompat.BigTextStyle().bigText(body))
                .setContentIntent(pendingIntent)

        val notificationManager: NotificationManager? = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager?.notify(0, notificationBuilder.build())
    }
}