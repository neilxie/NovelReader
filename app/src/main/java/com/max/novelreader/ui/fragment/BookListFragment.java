package com.max.novelreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.novelreader.R;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booklist, container, false);
    }
}
