package com.max.novelreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.mvp.presenter.BookListPresenter;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/20.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder> {

    BookListPresenter presenter;
    List<NovelMainBean> bookList;
    LayoutInflater layoutInflater;

    public BookListAdapter(Context context, BookListPresenter presenter, List<NovelMainBean> list) {
        this.presenter = presenter;
        bookList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setNovelList(List<NovelMainBean> list) {
        bookList = list;
    }

    @Override
    public BookListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BookListViewHolder holder, int position) {
        holder.bind(bookList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class BookListViewHolder extends RecyclerView.ViewHolder {

        NovelMainBean novelMainBean;

        public BookListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(NovelMainBean mainBean) {
            novelMainBean = mainBean;
        }
    }
}
