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
import android.app.NotificationChannel
import android.graphics.Color
import android.os.Build
import tokyo.aoisupersix.pushnotifyapp.R.mipmap.ic_launcher





/**
 * FCMからの通知を取得するクラスです。
 */
class MyFcmListenerService: FirebaseMessagingService() {
    /**
     * ログで利用するタグ名
     */
    private val tag: String = MyInstanceIDListenerService::class.simpleName!!

    /**
     * FCMから通知を取得した際にデバイスへプッシュ通知を送信します。
     * @param p0 取得した通知内容
     */
    override fun onMessageReceived(p0: RemoteMessage?) {
        val title: String = p0!!.data["notifyTitle"] ?: return
        val body: String = p0.data["location"] ?: return
        val time: String = p0.data["time"] ?: return

        //通知情報を格納
        LocationInfoManager.addMessages(title, body, time)

        Log.d(tag, "Message-Title: $title")
        Log.d(tag, "data: $body")
        Log.d(tag, "time: $time")

        sendNotification(title, body, time)
    }

    /**
     * プッシュ通知をデバイスに送ります。
     * @param title プッシュ通知のタイトル
     * @param body プッシュ通知の本文
     * @param time FCMからのプッシュ通知の送信時間
     */
    private fun sendNotification(title: String?, body: String?, time: String?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val NOTIFICATION_CHANNEL_ID = "my_channel_id_01"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH)

            // Configure the notification channel.
            notificationChannel.description = "Channel description"
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }


        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setTicker("Hearty365")
                //     .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo(time)

        notificationManager.notify(/*notification id*/1, notificationBuilder.build())
    }
}