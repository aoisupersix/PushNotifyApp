package tokyo.aoisupersix.pushnotifyapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * データベースへのアクセスを行うヘルパークラスです。
 */
private class DBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "AppData.db"
        const val DB_VERSION = 1

        const val TABLE_NAME = "LocationInfo"
        const val COL_ID = "id"
        const val COL_USERNAME = "username"
        const val COL_LOCATION = "location"
        const val COL_TIME = "time"
    }

    /**
     * アクセスするデータベース
     */
    val db: SQLiteDatabase = writableDatabase

    /**
     * データベース作成時にテーブルを作成します。
     * @param db SQLiteデータベース
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_USERNAME TEXT, $COL_LOCATION TEXT, $COL_TIME TEXT);")
    }

    /**
     * データベース更新時にテーブルを再作成します。
     * @param db SQLiteデータベース
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    /**
     * すべてのレコードを削除します。
     * @return 正常に削除できたかどうか
     */
    fun deleteAllRecord(): Boolean {
        return db.delete(TABLE_NAME, null, null) > 0
    }

    /**
     * 引数に与えられたIDのレコードを削除します。
     * @param id 削除するカラムのID
     * @return 正常に削除できたかどうか
     */
    fun deleteRecord(id: Int): Boolean {
        return db.delete(TABLE_NAME, "$COL_ID=$id", null) > 0
    }

    /**
     * すべてのレコードを取得します。
     * @return 全レコード
     */
    fun getAllRecord(): Cursor {
        return db.query(TABLE_NAME, null, null, null, null, null, null)
    }

    /**
     * 引数に与えられたレコードをデータベースに追加します。
     * @param item 追加するレコード
     */
    fun addRecord(item: LocationListViewItem) {
        val values = ContentValues()
        values.put(COL_USERNAME, item.userName)
        values.put(COL_LOCATION, item.location)
        values.put(COL_TIME, item.time)
        db.insertOrThrow(TABLE_NAME, null, values)
    }
}