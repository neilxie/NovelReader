package com.max.novelreader.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2017/1/18.
 */

public class BookListFragment extends Fragment {

    private static final String PARAM = "param";

    public BookListFragment() {}

    public static BookListFragment newInstance(String param) {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();
        args.putString(PARAM, param);
        fragment.setArguments(args);

        return fragment;
    }
}
