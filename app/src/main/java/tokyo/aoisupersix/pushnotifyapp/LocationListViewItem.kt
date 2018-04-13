package tokyo.aoisupersix.pushnotifyapp

/**
 * プッシュ通知リストビューのアイテムを示すクラスです。
 * @param userName 通知送信者
 * @param location 送信者の位置情報
 * @param time 通知送信時間
 */
class LocationListViewItem(var userName: String, var location: String, var time: String)