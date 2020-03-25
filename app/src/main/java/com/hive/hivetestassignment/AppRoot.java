package com.hive.hivetestassignment;

import android.app.Application;
import android.provider.Settings;

public class AppRoot extends Application {
    private static AppRoot mAppRootInstance;

    public static AppRoot getInstance() {
        return mAppRootInstance;
    }

    public static void setInstance(AppRoot app) {
        mAppRootInstance = app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
    }
}
