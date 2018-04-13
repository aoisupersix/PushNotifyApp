package tokyo.aoisupersix.pushnotifyapp

import com.google.firebase.messaging.RemoteMessage

object LocationInfoManager {
    val remoteMessages: MutableList<RemoteMessage> = mutableListOf()
    //TODO データ永続化
}