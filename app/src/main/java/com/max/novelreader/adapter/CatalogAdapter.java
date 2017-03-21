package com.max.novelreader.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.max.novelreader.R;
import com.max.novelreader.bean.Chapter;
import com.max.novelreader.mvp.presenter.CatalogPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/21.
 */

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> {

    LayoutInflater layoutInflater;
    List<Chapter> chapterList;
    int curChapterOId;
    CatalogPresenter presenter;

    public CatalogAdapter(Context context, List<Chapter> list, int curChapter, CatalogPresenter presenter) {
        layoutInflater = LayoutInflater.from(context);
        chapterList = list;
        curChapterOId = curChapter;
        this.presenter = presenter;
    }

    @Override
    public CatalogAdapter.CatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CatalogViewHolder(layoutInflater.inflate(R.layout.item_catalog, parent, false), presenter, curChapterOId);
    }

    @Override
    public void onBindViewHolder(CatalogAdapter.CatalogViewHolder holder, int position) {
        Chapter chapter = chapterList.get(position);
        holder.bind(chapter, position);
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public static class CatalogViewHolder extends RecyclerView.ViewHolder {

        Chapter chapter;
        int position;
        @BindView(R.id.tv_chapter_name)
        TextView tvChapterName;
        @BindView(R.id.iv_arrow)
        ImageView ivArrow;
        CatalogPresenter presenter;
        int curChapterOId;

        public CatalogViewHolder(View itemView, CatalogPresenter presenter, int curChapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            curChapterOId = curChapter;
            this.presenter = presenter;
            ivArrow.setVisibility(View.GONE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CatalogViewHolder.this.presenter.onChapterClick(v.getContext(), chapter, position);
                }
            });
        }

        public void bind(Chapter chapter, int position) {
            this.chapter = chapter;
            this.position = position;
            tvChapterName.setText(chapter.getName());
            Context context = tvChapterName.getContext();
            int color = chapter.getOid() == curChapterOId ? context.getResources().getColor(R.color.orange_efd78f) : Color.BLACK;
            tvChapterName.setTextColor(color);
        }
    }
}
