package com.selsela.example.ui.productlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.materialize.color.Material;
import com.selsela.example.R;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.favorites.FavoriteRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.apptik.widget.MultiSlider;

public class ProductListActivity extends BaseActivity {
    FavoriteRecyclerViewAdapter favouriteAdapter;
   @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.list_textView)
    TextView listTextView;
    @BindView(R.id.innerLine)
    View innerLine;
    @BindView(R.id.grid_textView)
    TextView gridTextView;
    @BindView(R.id.innerLine2)
    View innerLine2;
    @BindView(R.id.filter_textView)
    TextView filterTextView;
    @BindView(R.id.filter_results)
    TextView filterResults;
    @BindView(R.id.product_list)
    RecyclerView productList;
    public static int GRID = 1;
    public static int LINEAR = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        getActivityComponent().inject(this);

        ButterKnife.bind(this);
//
//      favouriteAdapter = new FavoriteRecyclerViewAdapter();
//        onLinear();
    }

    @OnClick({R.id.list_textView, R.id.grid_textView, R.id.filter_textView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.list_textView:
                listTextView.setBackgroundColor(getResources().getColor(R.color.white));
                listTextView.setTextColor(getResources().getColor(R.color.brown));
                gridTextView.setBackgroundColor(getResources().getColor(R.color.gray));
                gridTextView.setTextColor(getResources().getColor(R.color.colorprimary));
                filterTextView.setBackgroundColor(getResources().getColor(R.color.gray));
                filterTextView.setTextColor(getResources().getColor(R.color.colorprimary));
                onLinear();
                break;
            case R.id.grid_textView:
                onGrid();
                break;
            case R.id.filter_textView:
                showChangeDialog();
                break;
        }
    }

    private void onGrid() {
        productList.setLayoutManager(new GridLayoutManager(this, 2));
        favouriteAdapter.setLayout(GRID);
        productList.setAdapter(favouriteAdapter);
        favouriteAdapter.notifyDataSetChanged();
    }

    private void onLinear() {
        //linearMenu.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_linear));
        // gridMenu.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_grid_un));
        productList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        favouriteAdapter.setLayout(LINEAR);
        productList.setAdapter(favouriteAdapter);
        favouriteAdapter.notifyDataSetChanged();
    }

    private void showChangeDialog() {

        final MaterialDialog dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_filter, false)
                .contentGravity(GravityEnum.START)
                .build();
        dialog.show();
        View view2 = dialog.getCustomView();
        RecyclerView colorRecyclerView = view2.findViewById(R.id.color_list);
        colorRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        colorRecyclerView.setAdapter(new ColorRecyclerViewAdapter());
        RecyclerView sizeRecyclerView = view2.findViewById(R.id.size_list);
        sizeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        sizeRecyclerView.setAdapter(new SizeRecyclerViewAdapter());
        TextView verifyButton = view2.findViewById(R.id.fltert_back);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }


}
