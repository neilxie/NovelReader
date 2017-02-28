package com.max.novelreader.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.max.novelreader.R;
import com.max.novelreader.bean.NovelBean;
import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.mvp.presenter.BookListPresenter;
import com.max.novelreader.ui.BookDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/20.
 */

public class BookListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;

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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            return new BookListViewHolder(layoutInflater.inflate(R.layout.item_booklist, parent, false));
        } else {
            return new FooterViewHolder(layoutInflater.inflate(R.layout.item_footer_load_more, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof BookListViewHolder) {
            BookListViewHolder viewHolder = (BookListViewHolder) holder;
            viewHolder.bind(bookList.get(position));
            if(position >= bookList.size() - 1) {
                viewHolder.hideDevider();
            } else {
                viewHolder.showDivider();
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position < bookList.size() ? TYPE_ITEM : TYPE_FOOTER;
    }

    @Override
    public int getItemCount() {
        int size = bookList.size();
        return size > 0 ? size + 1 : size;
    }

    public static class BookListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_book_cover)
        ImageView ivBookCover;
        @BindView(R.id.tv_book_name)
        TextView tvBookName;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_book_desc)
        TextView tvBookDesc;
        @BindView(R.id.tv_over)
        TextView tvOver;
        @BindView(R.id.tv_category)
        TextView tvCategory;
        @BindView(R.id.v_divider)
        View vDivider;

        NovelMainBean novelMainBean;
        Context context;

        public BookListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bind(NovelMainBean mainBean) {
            novelMainBean = mainBean;
            NovelBean novelBean = mainBean.getNovel();
            Glide.with(context).load(novelBean.getCover()).placeholder(R.drawable.nocover).centerCrop().into(ivBookCover);
            tvBookName.setText(novelBean.getName());
            tvAuthor.setText(Html.fromHtml(context.getString(R.string.bookshelf_author, mainBean.getAuthor().getName())));
            tvBookDesc.setText(novelBean.getIntro());
            tvOver.setText(novelBean.isOver() ? R.string.novel_over : R.string.novel_serial);
            tvCategory.setText(mainBean.getCategory().getName());
        }

        public void showDivider() {
            vDivider.setVisibility(View.VISIBLE);
        }

        public void hideDevider() {
            vDivider.setVisibility(View.GONE);
        }

        @OnClick(R.id.v_book_item)
        public void onItemClick(View v) {
//            Intent intent = new Intent(context, BookDetailActivity.class);
//            intent.putExtra(BookDetailActivity.EXTRA_BOOK, novelMainBean);
//            context.startActivity(intent);

            BookDetailActivity.showBookDetail((Activity) context, ivBookCover, novelMainBean);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
