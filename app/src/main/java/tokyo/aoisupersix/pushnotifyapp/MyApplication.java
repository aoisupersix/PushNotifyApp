package tokyo.aoisupersix.pushnotifyapp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * アプリケーションコンテキストを保持するクラス
 */
public class MyApplication extends Application {
    private static MyApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        //Realmの初期化処理
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            // Create channel to show notifications.
//            String channelId  = getString(R.string);
//            String channelName = getString(R.string.default_notification_channel_name);
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
//                    channelName, NotificationManager.IMPORTANCE_LOW));
//        }
    }

    /**
     * MyApplicationのインスタンスを取得します。
     * @return MyApplicationのインスタンス(not null)
     */
    public static MyApplication getInstance() {
        return instance;
    }
}
