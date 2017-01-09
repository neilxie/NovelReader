package com.max.novelreader.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.max.novelreader.R;
import com.max.novelreader.di.components.MainComponent;
import com.max.novelreader.mvp.presenter.MainPresenter;
import com.max.novelreader.mvp.view.MainView;
import com.max.novelreader.ui.fragment.BookshelfFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, BookshelfFragment.OnFragmentInteractionListener {

    @Inject
    MainPresenter mainPresenter;
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;
    @BindView(R.id.container)
    FrameLayout container;
    List<Fragment> fragments;

    MainComponent mainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mainPresenter = new MainPresenterImpl(this);
//        mainComponent = DaggerMainComponent.b

        mainPresenter.attach(this);
        mainPresenter.onCreate();
    }

    @Override
    public void initViews() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

            }
        });
    }

    @Override
    public void initFragment() {
        fragments = new ArrayList<>();
        Fragment fragment = BookshelfFragment.newInstance("", "");
        fragments.add(fragment);
    }

    @Override
    public void showPage(int index) {
        int size = fragments.size();
        if(index >= size) {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = fragments.get(index);
        if(fragment.isAdded()) {
            if(!fragment.isVisible()) {
                transaction.show(fragment);
            }
        } else {
            transaction.add(R.id.container, fragment);
        }

        transaction.commit();
    }

    @Override
    public void hidePage(int index) {
        int size = fragments.size();
        if(index >= size) {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = fragments.get(index);
        if(fragment.isAdded()) {
            transaction.hide(fragment);
        }

        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}