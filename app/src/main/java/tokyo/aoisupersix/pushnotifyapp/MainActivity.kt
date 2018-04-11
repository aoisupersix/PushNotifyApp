package tokyo.aoisupersix.pushnotifyapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.messaging.FirebaseMessaging
import tokyo.aoisupersix.pushnotifyapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Push通知の購読開始
        FirebaseMessaging.getInstance().subscribeToTopic("mytopic")
    }
}
