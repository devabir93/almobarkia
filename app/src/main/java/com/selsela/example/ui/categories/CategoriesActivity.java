package com.selsela.example.ui.categories;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.selsela.example.R;
import com.selsela.example.data.model.home.MainCategory;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.productlist.ProductListActivity;
import com.selsela.example.util.Const;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesActivity extends BaseActivity implements CategoryMvpView {

    @BindView(R.id.categories_list)
    RecyclerView categoriesList;
    @Inject
    CategoryPresenter categoryPresenter;
    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout pullToRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        categoryPresenter.attachView(this);
        categoryPresenter.getCategories();
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categoryPresenter.getCategories();
            }
        });
        activityTitle = getString(R.string.categories_label);
        initToolbar();


    }

    @Override
    public void showCategories(List<MainCategory> categoryList) {
        categoriesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        categoriesList.setAdapter(new CategoriesRecyclerViewAdapter(categoryList, this, new CategoriesRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onCategorySelected(MainCategory category, int position) {
               Intent intent = new Intent(CategoriesActivity.this, ProductListActivity.class);
                intent.putExtra(Const.Details,(ArrayList) category.getProducts());
                intent.putExtra(Const.Name,category.getName());

                startActivity(intent);

            }
        }));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
