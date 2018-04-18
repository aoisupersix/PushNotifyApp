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
    }

    /**
     * MyApplicationのインスタンスを取得します。
     * @return MyApplicationのインスタンス(not null)
     */
    public static MyApplication getInstance() {
        return instance;
    }
}
