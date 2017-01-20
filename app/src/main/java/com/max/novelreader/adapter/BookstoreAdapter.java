package com.max.novelreader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */

public class BookstoreAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> pageTitleList;

    public BookstoreAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);

        fragmentList = fragments;
        pageTitleList = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitleList.get(position);
    }
}
