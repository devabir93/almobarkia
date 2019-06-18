package com.selsela.example.ui.categories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.selsela.example.R;
import com.selsela.example.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesActivity extends BaseActivity {

    @BindView(R.id.categories_list)
    RecyclerView categoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
       // categoriesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
      //  categoriesList.setAdapter(new CategoriesRecyclerViewAdapter());

    }
}
