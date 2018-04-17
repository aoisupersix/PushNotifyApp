package tokyo.aoisupersix.pushnotifyapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.messaging.FirebaseMessaging

/**
 * メッセージ情報の表示等を行うActivityクラス
 */
class MainActivity : AppCompatActivity() {

    var locationListViewAdapter: LocationListViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Push通知の購読開始
        FirebaseMessaging.getInstance().subscribeToTopic("topic")
    }

    override fun onResume() {
        super.onResume()
        LocationInfoManager.addMessages("userName", "location", "time")

        //ListViewにLocation情報をセット
        val locationListView = findViewById<ListView>(R.id.locationListView)

        //LocationMessageの取得/セット
        locationListViewAdapter = LocationListViewAdapter(this,LocationInfoManager.getMessages())

        locationListView.emptyView = findViewById(R.id.emptyLocationView)
        locationListView.adapter = locationListViewAdapter
    }
}
