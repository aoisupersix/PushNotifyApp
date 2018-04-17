package tokyo.aoisupersix.pushnotifyapp

import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.messaging.FirebaseMessaging

/**
 * メッセージ情報の表示等を行うActivityクラス
 */
class MainActivity : AppCompatActivity() {

    private var locationListViewAdapter: LocationListViewAdapter? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.deleteAll -> {
                LocationInfoManager.deleteAllMessages()
                updateListView()
                return true
            }
            R.id.refresh -> {
                updateListView()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Push通知の購読開始
        FirebaseMessaging.getInstance().subscribeToTopic("topic")
    }

    override fun onResume() {
        super.onResume()

        updateListView()
    }

    private fun updateListView() {
        //ListViewにLocation情報をセット
        val locationListView = findViewById<ListView>(R.id.locationListView)

        //LocationMessageの取得/セット
        locationListViewAdapter = LocationListViewAdapter(this,LocationInfoManager.getMessages())

        locationListView.emptyView = findViewById(R.id.emptyLocationView)
        locationListView.adapter = locationListViewAdapter
    }
}
