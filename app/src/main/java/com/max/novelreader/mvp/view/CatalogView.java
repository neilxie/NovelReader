package com.max.novelreader.mvp.view;

import com.max.novelreader.bean.Chapter;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */

public interface CatalogView {

    void hideProgress();
    void showCatalog(List<Chapter> chapterList, int curPos);
}
