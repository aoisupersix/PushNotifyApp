package tokyo.aoisupersix.pushnotifyapp

import io.realm.Realm
import java.util.*

/**
 * FCMからのメッセージ情報を管理するオブジェクトです。
 */
object LocationInfoManager {

    /**
     * 保存されたメッセージを取得します。
     * @return すべてのメッセージ
     */
    fun getMessages(): MutableList<LocationListViewItem> {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(PushMessageInfo::class.java).findAll()
        val items: MutableList<LocationListViewItem> = mutableListOf()
        for(res in results){
            items.add(LocationListViewItem(res.userName, res.location, res.time))
        }

        return items
    }

    /**
     * メッセージを追加します。
     * @param userName ユーザ名
     * @param location 位置情報
     * @param time メッセージ送信時刻
     */
    fun addMessages(userName: String, location: String, time: String) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val model = realm.createObject(PushMessageInfo::class.java, UUID.randomUUID().toString())
        model.userName = userName
        model.location = location
        model.time = time
        realm.commitTransaction()
    }

    /**
     * 保持している全てのメッセージを削除します
     */
    fun deleteAllMessages() {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        var model = realm.where(PushMessageInfo::class.java).findAll()
        model.deleteAllFromRealm()
    }

}