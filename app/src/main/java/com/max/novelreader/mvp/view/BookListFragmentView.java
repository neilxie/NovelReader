package com.max.novelreader.mvp.view;

import com.max.novelreader.bean.NovelMainBean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/20.
 */

public interface BookListFragmentView extends FragmentView {

    void refreshNovelList(List<NovelMainBean> list);
}
