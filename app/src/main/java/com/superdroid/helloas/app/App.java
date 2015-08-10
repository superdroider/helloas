package com.superdroid.helloas.app;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by GT on 2015/8/7.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
    }
}
