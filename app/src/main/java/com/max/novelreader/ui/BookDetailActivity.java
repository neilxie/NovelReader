package com.max.novelreader.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.max.novelreader.R;
import com.max.novelreader.bean.Catalog;
import com.max.novelreader.bean.Chapter;
import com.max.novelreader.bean.NovelBean;
import com.max.novelreader.bean.NovelLastBean;
import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.di.components.BookDetailComponent;
import com.max.novelreader.di.components.DaggerBookDetailComponent;
import com.max.novelreader.di.modules.BookDetailModule;
import com.max.novelreader.mvp.presenter.BookDetailPresenter;
import com.max.novelreader.mvp.view.BookDetailView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Administrator on 2017/1/23.
 */

public class BookDetailActivity extends BaseActivity implements BookDetailView {

    public static final String EXTRA_BOOK = "book";

    @BindView(R.id.tv_book_name)
    TextView tvBookName;
    @BindView(R.id.iv_book_cover)
    ImageView ivBookCover;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_visit)
    TextView tvVisit;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.tv_collapse)
    TextView tvCollapse;

    @Inject
    BookDetailPresenter presenter;

    BookDetailComponent component;
    @BindView(R.id.progress)
    ContentLoadingProgressBar progress;
//    @BindView(R.id.tv_chapter_name)
    TextView tvLatestChapterName;
//    @BindView(R.id.tv_chapter_name)
    TextView tvFirstChapterName;
//    @BindView(R.id.tv_chapter_name)
    TextView tvSecondChapterName;
//    @BindView(R.id.tv_chapter_name)
    TextView tvThirdChapterName;
//    @BindView(R.id.rl_catalog_item)
    View vLatestChapter;
//    @BindView(R.id.rl_catalog_item)
    View vFirstChapter;
//    @BindView(R.id.rl_catalog_item)
    View vSecondChapter;
//    @BindView(R.id.rl_catalog_item)
    View vThirdChapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);
        initView();

        component = DaggerBookDetailComponent.builder().bookDetailModule(new BookDetailModule()).build();
        component.inject(this);

        presenter.attach(this);
        NovelMainBean bean = (NovelMainBean) getIntent().getSerializableExtra(EXTRA_BOOK);
        presenter.setBook(bean);
    }

    private void initView() {
        vLatestChapter = findViewById(R.id.v_last);
        vFirstChapter = findViewById(R.id.v_first);
        vSecondChapter = findViewById(R.id.v_second);
        vThirdChapter = findViewById(R.id.v_third);

        tvLatestChapterName = (TextView) vLatestChapter.findViewById(R.id.tv_chapter_name);
        tvFirstChapterName = (TextView) vFirstChapter.findViewById(R.id.tv_chapter_name);
        tvSecondChapterName = (TextView) vSecondChapter.findViewById(R.id.tv_chapter_name);
        tvThirdChapterName = (TextView) vThirdChapter.findViewById(R.id.tv_chapter_name);
    }

    @OnClick(R.id.iv_back)
    public void onClickBack(View view) {
        finish();
    }

    @OnClick(R.id.tv_collapse)
    public void onCollapseClick(View view) {
        presenter.onCollapseClick();
    }

    @OnClick(R.id.tv_catalog_more)
    public void onClickCatalogMore(View view) {
        presenter.onClickCatalogMore(this);
    }

    @OnClick(R.id.v_last)
    public void onClickCatalogLatest(View view) {
        presenter.onClickCatalogLatest(this);
    }

    @OnClick(R.id.v_first)
    public void onClickCatalogFirst(View view) {
        presenter.onClickCatalogFirst(this);
    }

    @OnClick(R.id.v_second)
    public void onClickCatalogSecond(View view) {
        presenter.onClickCatalogSecond(this);
    }

    @OnClick(R.id.v_third)
    public void onClickCatalogThird(View view) {
        presenter.onClickCatalogThird(this);
    }

    @Override
    public void showNovel(NovelMainBean bean) {
        NovelBean novelBean = bean.getNovel();
        tvBookName.setText(novelBean.getName());
        tvAuthor.setText(Html.fromHtml(getString(R.string.bookshelf_author, bean.getAuthor().getName())));
        String state = novelBean.isOver() ? getString(R.string.novel_over) : getString(R.string.novel_serial_ing);
        tvStatus.setText(getString(R.string.book_state, state));
        int count = Integer.parseInt(bean.getData().getAllvisit());
        String visit = count / 10000 < 10 ? String.valueOf(count) : getString(R.string.unit_wan, count / 10000);
        tvVisit.setText(getString(R.string.novel_visit_count, visit));

        Glide.with(this).load(novelBean.getCover()).placeholder(R.drawable.nocover).centerCrop().into(ivBookCover);
        Glide.with(this).load(novelBean.getCover()).bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this)).crossFade().into(ivBg);
    }

    @Override
    public void showIntroAll(String intro) {
        tvIntro.setText(intro);
        tvCollapse.setText(R.string.collapse);
        Drawable drawable = getResources().getDrawable(R.drawable.bookdetail_intro_load_more_hide);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        tvCollapse.setCompoundDrawables(null, null, drawable, null);
    }

    @Override
    public void showIntroLess(String intro) {
        tvIntro.setText(intro);
        tvCollapse.setText(R.string.open);
        Drawable drawable = getResources().getDrawable(R.drawable.bookdetail_intro_load_more);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        tvCollapse.setCompoundDrawables(null, null, drawable, null);
    }

    @Override
    public void showCatalog(NovelLastBean bean, Catalog catalog) {
        tvLatestChapterName.setText(getString(R.string.latest_chapter, bean.getName()));
        tvLatestChapterName.setTextColor(getResources().getColor(R.color.orange_d2bf86));
        List<Chapter> chapters = catalog.getData();
        if(chapters != null && !chapters.isEmpty()) {

            tvFirstChapterName.setText(chapters.get(0).getName());
            tvSecondChapterName.setText(chapters.get(1).getName());
            tvThirdChapterName.setText(chapters.get(2).getName());
        }
    }

    @Override
    public void showCatalogProgress() {
        progress.setVisibility(View.VISIBLE);
        vLatestChapter.setVisibility(View.GONE);
        vFirstChapter.setVisibility(View.GONE);
        vSecondChapter.setVisibility(View.GONE);
        vThirdChapter.setVisibility(View.GONE);
    }

    @Override
    public void hideCatalogProgress() {
        progress.setVisibility(View.GONE);
        vLatestChapter.setVisibility(View.VISIBLE);
        vFirstChapter.setVisibility(View.VISIBLE);
        vSecondChapter.setVisibility(View.VISIBLE);
        vThirdChapter.setVisibility(View.VISIBLE);
    }
}
