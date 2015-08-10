package com.superdroid.helloas;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


public class BaseActivity extends FragmentActivity {
public String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,getClass().getSimpleName());
    }
}
