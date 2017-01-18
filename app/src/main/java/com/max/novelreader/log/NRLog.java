package com.max.novelreader.log;

import android.util.Log;

import com.max.novelreader.BuildConfig;

/**
 * Created by Administrator on 2017/1/10.
 */

public class NRLog {

    private static final String TAG = "NovelReader";

    public static void d(String msg) {
        if(BuildConfig.DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if(BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String msg) {
        if(BuildConfig.DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if(BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }
}
