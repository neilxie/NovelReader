package com.max.novelreader.ui;

import android.support.v7.app.AppCompatActivity;

import com.max.novelreader.App;
import com.max.novelreader.di.components.AppComponent;

/**
 * Created by Administrator on 2017/1/19.
 */

public class BaseActivity extends AppCompatActivity {

    AppComponent getAppComponent() {
        return ((App)getApplication()).getAppComponent();
    }
}
