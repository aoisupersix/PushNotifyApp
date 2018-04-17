package tokyo.aoisupersix.pushnotifyapp

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * 保存するメッセージのRealmModelです。
 * @param id メッセージを識別する一意のID
 * @param userName メッセージのユーザ名
 * @param location メッセージの位置情報
 * @param time メッセージの送信時刻
 */
open class PushMessageInfo constructor(
        @PrimaryKey open var id: String = UUID.randomUUID().toString(),
        open var userName: String = "",
        open var location: String = "",
        open var time: String = ""
): RealmObject()