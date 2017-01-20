package com.max.novelreader.db;

import android.content.Context;

import com.max.novelreader.bean.Book;
import com.max.novelreader.dao.DaoMaster;
import com.max.novelreader.dao.DaoSession;

import java.util.List;

/**
 * Created by Administrator on 2017/1/19.
 */

public class DaoManager {

    private static final String DB_NAME = "NovelReader.db";

    private DaoMaster daoMaster;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private DaoSession daoSession;

    public DaoManager(Context context) {
        devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public List<Book> listBookshelf() {
        return daoSession.loadAll(Book.class);
    }


}
