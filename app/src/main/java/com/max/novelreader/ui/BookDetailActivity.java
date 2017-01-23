package com.max.novelreader.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.max.novelreader.R;
import com.max.novelreader.bean.NovelBean;
import com.max.novelreader.bean.NovelMainBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Administrator on 2017/1/23.
 */

public class BookDetailActivity extends BaseActivity {

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

    NovelMainBean novelMainBean;
    boolean isIntroShowAll = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        novelMainBean = (NovelMainBean) getIntent().getSerializableExtra(EXTRA_BOOK);

        initView();
    }

    @OnClick(R.id.iv_back)
    public void onClickBack(View view) {
        finish();
    }

    @OnClick(R.id.tv_collapse)
    public void onCollapseClick(View view) {
        isIntroShowAll = !isIntroShowAll;
        if(isIntroShowAll) {
            showNovelIntroAll();
        } else {
            hideNovelIntro();
        }
    }

    private void initView() {
        NovelBean novelBean = novelMainBean.getNovel();
        tvBookName.setText(novelBean.getName());
        tvAuthor.setText(Html.fromHtml(getString(R.string.bookshelf_author, novelMainBean.getAuthor().getName())));
        String state = novelBean.isOver() ? getString(R.string.novel_over) : getString(R.string.novel_serial_ing);
        tvStatus.setText(getString(R.string.book_state, state));
        int count = Integer.parseInt(novelMainBean.getData().getAllvisit());
        String visit = count / 10000 < 10 ? String.valueOf(count) : getString(R.string.unit_wan, count / 10000);
        tvVisit.setText(getString(R.string.novel_visit_count, visit));

        Glide.with(this).load(novelBean.getCover()).placeholder(R.drawable.nocover).centerCrop().into(ivBookCover);
        Glide.with(this).load(novelBean.getCover()).bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this)).crossFade().into(ivBg);

        hideNovelIntro();
    }

    private void showNovelIntroAll() {
        tvIntro.setText(novelMainBean.getNovel().getIntro());
        tvCollapse.setText(R.string.collapse);
        Drawable drawable = getResources().getDrawable(R.drawable.bookdetail_intro_load_more_hide);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        tvCollapse.setCompoundDrawables(null, null, drawable, null);
    }

    private void hideNovelIntro() {
        String intro = novelMainBean.getNovel().getIntro();
        if(intro.length() > 60) {
            intro = intro.substring(0, 60) + "...";
        }
        tvIntro.setText(intro);
        tvCollapse.setText(R.string.open);
        Drawable drawable = getResources().getDrawable(R.drawable.bookdetail_intro_load_more);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        tvCollapse.setCompoundDrawables(null, null, drawable, null);
    }

}
