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
        val title: String? = p0?.notification?.title ?: return
        val body: String? = p0?.notification?.body ?: return
        val time: String? = p0?.data["time"] ?:return

        //通知情報を格納
        LocationInfoManager.locationListViewItems.add(LocationListViewItem(
                title as String,
                body as String,
                time as String))

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
        val intentArray: Array<Intent?> = arrayOfNulls(1)
        intentArray[0] = Intent(this, MainActivity::class.java)
        intentArray[0]?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivities(this, 0, intentArray, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(defaultSoundUri)
                .setStyle(NotificationCompat.BigTextStyle().bigText(body))
                .setContentIntent(pendingIntent)

        val notificationManager: NotificationManager? = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager?.notify(0, notificationBuilder.build())
    }
}