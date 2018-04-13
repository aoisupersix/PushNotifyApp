package tokyo.aoisupersix.pushnotifyapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Push通知の購読開始
        FirebaseMessaging.getInstance().subscribeToTopic("topic")

        //ListViewのテスト
        val locationListView = findViewById<ListView>(R.id.locationListView)
        val listViewItems: MutableList<LocationListViewItem> = mutableListOf()
        for (i in 0..10) {
            listViewItems.add(LocationListViewItem("item-$i"))
        }

        val adapter = LocationListViewAdapter(this, listViewItems)

        locationListView.emptyView = findViewById(R.id.emptyLocationView)
        locationListView.adapter = adapter
    }
}
