package tokyo.aoisupersix.pushnotifyapp
import android.util.Log

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyInstanceIDListenerService : FirebaseInstanceIdService() {

    private val tag: String = MyInstanceIDListenerService::class.simpleName!!

    override fun onTokenRefresh() {
        //InstanceIdを取得
        val refreshedToken: String? = FirebaseInstanceId.getInstance().token
        Log.d(tag, "Refreshed token: $refreshedToken")
    }
}