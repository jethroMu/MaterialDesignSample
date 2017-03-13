package example.jethro.com.materialdemo;

import android.app.Application;
import android.content.Context;


/**
 * Created by lin on 2016/3/12.
 */
public class MyApp extends Application {

    private static Context mContext;
    private static MyApp sMyApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        sMyApp = this;
    }

    public static MyApp getMyApp() {
        return sMyApp;
    }

    public static Context getContext() {
        return mContext;
    }

}
