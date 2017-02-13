package com.max.novelreader.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.max.novelreader.R;
import com.max.novelreader.bean.Category;
import com.max.novelreader.ui.CategoryListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/22.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    LayoutInflater layoutInflater;
    List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> list) {
        layoutInflater = LayoutInflater.from(context);
        categoryList = list;
    }

    public void setCategoryList(List<Category> list) {
        categoryList = list;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(layoutInflater.inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.bind(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_category_name)
        TextView tvCategoryName;
        Category category;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Category category) {
            tvCategoryName.setText(category.getName());
            this.category = category;
        }

        @OnClick(R.id.v_category_item)
        public void onItemClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, CategoryListActivity.class);
            intent.putExtra(CategoryListActivity.EXTRA_CATEGORY, category);
            context.startActivity(intent);
        }
    }
}
