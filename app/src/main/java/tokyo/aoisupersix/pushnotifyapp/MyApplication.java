package tokyo.aoisupersix.pushnotifyapp;

import android.app.Application;
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
