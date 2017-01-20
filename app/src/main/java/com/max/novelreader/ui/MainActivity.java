package com.max.novelreader.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

import com.max.novelreader.R;
import com.max.novelreader.di.components.DaggerMainComponent;
import com.max.novelreader.di.components.MainComponent;
import com.max.novelreader.di.modules.MainModule;
import com.max.novelreader.event.DelBsEvent;
import com.max.novelreader.event.HideBsDelBtnEvent;
import com.max.novelreader.event.ShowBsDelBtnEvent;
import com.max.novelreader.event.UpdateBsDelNumEvent;
import com.max.novelreader.mvp.presenter.MainPresenter;
import com.max.novelreader.mvp.view.MainView;
import com.max.novelreader.ui.fragment.BookStoreFragment;
import com.max.novelreader.ui.fragment.BookshelfFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView, BookshelfFragment.OnFragmentInteractionListener {

    @Inject
    MainPresenter mainPresenter;
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.btn_bs_del)
    Button btnBsDel;

    List<Fragment> fragments;
    MainComponent mainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainComponent = DaggerMainComponent.builder().appComponent(getAppComponent()).mainModule(new MainModule()).build();
        mainComponent.inject(this);

        mainPresenter.attach(this);
        mainPresenter.onCreate();

        EventBus.getDefault().register(this);
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initViews() {
//        bottomBar.setInActiveTabColor();
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                int position = bottomBar.getTabWithId(tabId).getIndexInTabContainer();
                mainPresenter.onTabSelete(position);
            }
        });

    }

    @Override
    public void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(BookshelfFragment.newInstance("", ""));
        fragments.add(BookStoreFragment.newInstance());
    }

    @Override
    public void showPage(int index) {
        int size = fragments.size();
        if (index >= size) {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = fragments.get(index);
        if (fragment.isAdded()) {
            if (!fragment.isVisible()) {
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
        if (index >= size) {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = fragments.get(index);
        if (fragment.isAdded()) {
            transaction.hide(fragment);
        }

        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Bundle bundle) {

    }

    @OnClick(R.id.btn_bs_del)
    public void onDelBtnClick(View v) {
        EventBus.getDefault().post(new DelBsEvent());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowDelBtnEvent(ShowBsDelBtnEvent evnet) {
        btnBsDel.setVisibility(View.VISIBLE);
        btnBsDel.setText(getString(R.string.boolshelf_del_num, 0));
        btnBsDel.startAnimation(AnimationUtils.loadAnimation(this, R.anim.design_bottom_sheet_slide_in));
    }

    @Subscribe(threadMode =  ThreadMode.MAIN)
    public void onHideDelBtnEvent(HideBsDelBtnEvent event) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.design_bottom_sheet_slide_out);
        btnBsDel.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btnBsDel.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateDelNumEvent(UpdateBsDelNumEvent event) {
        btnBsDel.setText(getString(R.string.boolshelf_del_num, event.delBookNum));
    }
}