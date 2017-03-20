package com.max.novelreader.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.max.novelreader.R;
import com.max.novelreader.bean.Book;
import com.max.novelreader.mvp.presenter.BookshelfPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/13.
 */

public class BookshelfAdapter extends RecyclerView.Adapter<BookshelfAdapter.BookshelfViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Book> bookList;
    private boolean isEditMode = false;
    private BookshelfPresenter bookshelfPresenter;

    public BookshelfAdapter(Context context, List<Book> books, BookshelfPresenter presenter) {
        bookList = books;
        layoutInflater = LayoutInflater.from(context);
        bookshelfPresenter = presenter;
    }

    public void setBookList(List<Book> books) {
        bookList = books;
    }

    public void setEditMode(boolean editMode) {
        isEditMode = editMode;
    }

    @Override
    public BookshelfViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookshelfViewHolder(layoutInflater.inflate(R.layout.item_bookshelf, parent, false), bookshelfPresenter);
    }

    @Override
    public void onBindViewHolder(BookshelfViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.updateViews(book, isEditMode);
        if(position == bookList.size() -1 ) {
            holder.hideDivider();
        } else {
            holder.showDivider();
        }
    }

    @Override
    public int getItemCount() {
        return bookList != null ? bookList.size() : 0;
    }

    public static class BookshelfViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_book_conver)
        ImageView ivBookConver;
        @BindView(R.id.tv_book_title)
        TextView tvBookTitle;
        @BindView(R.id.tv_book_author)
        TextView tvBookAuthor;
        @BindView(R.id.tv_history)
        TextView tvHistory;
        @BindView(R.id.iv_book_select)
        AppCompatImageView ivSelect;
        @BindView(R.id.v_divider)
        View vDivider;

        Context context;
        Book currentBook;
        boolean isEditMode;
        BookshelfPresenter bookshelfPresenter;

        public BookshelfViewHolder(View itemView, BookshelfPresenter presenter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            bookshelfPresenter = presenter;
        }

        public void updateViews(Book book, boolean isEditMode) {
            this.isEditMode = isEditMode;
            currentBook = book;
            tvBookTitle.setText(book.getName());
            tvBookAuthor.setText(Html.fromHtml(context.getString(R.string.bookshelf_author, book.getAuthor())));
            String readChapter = book.getReadChapterName();
            if(!TextUtils.isEmpty(readChapter)) {
                tvHistory.setText(context.getString(R.string.book_read_history, readChapter));
            }
            ivSelect.setVisibility(isEditMode ? View.VISIBLE : View.GONE);
            setSelectIconColor();
            Glide.with(context).load(book.getCoverUrl()).centerCrop().into(ivBookConver);
        }

        public void hideDivider() {
            vDivider.setVisibility(View.GONE);
        }

        public void showDivider() {
            vDivider.setVisibility(View.VISIBLE);
        }

        private void setSelectIconColor() {
            ivSelect.setColorFilter(bookshelfPresenter.isBookSelected(currentBook) ? context.getResources().getColor(R.color.colorAccent) : context.getResources().getColor(R.color.gray_b7b5b6));
        }

        @OnClick(R.id.bookshelf_item_view)
        public void onItemClick(View view) {
            if(isEditMode) {
                setSelectIconColor();
                if(bookshelfPresenter != null) {
                    bookshelfPresenter.onBookItemClick(currentBook);
                }
            } else {
                // start read activity
            }
        }
    }
}
