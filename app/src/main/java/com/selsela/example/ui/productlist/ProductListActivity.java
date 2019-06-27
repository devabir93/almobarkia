package com.selsela.example.ui.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.selsela.example.R;
import com.selsela.example.data.model.filter.Color;
import com.selsela.example.data.model.filter.Size;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.ui.base.BaseActivity;
import com.selsela.example.ui.home.ProductRecyclerViewAdapter;
import com.selsela.example.ui.productdeatials.ProductDetailsActivity;
import com.selsela.example.util.Const;
import com.selsela.example.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.apptik.widget.MultiSlider;

public class ProductListActivity extends BaseActivity implements FilterMvpView {
    @Inject
    FilterPresenter filterPresenter;
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
    RecyclerView productListRecyclerview;
    public static int GRID = 1;
    public static int LINEAR = 0;
    private ArrayList<Product> productlist;
    private ProductRecyclerViewAdapter adapter;
    private RecyclerView colorRecyclerView;
    private RecyclerView sizeRecyclerView;
    private List<Color> colors;
    private MaterialDialog dialog;
    private Size selectedSize;
    private Color selectedColor;
    private int fromPrice, toPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        filterPresenter.attachView(this);
        activityTitle = getIntent().getStringExtra(Const.Name);
        initToolbar();
        productlist = getIntent().getParcelableArrayListExtra(Const.Details);
        adapter = new ProductRecyclerViewAdapter(productlist, this, new ProductRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onproductSelected(Product product, int position) {
                Intent intent = new Intent(ProductListActivity.this, ProductDetailsActivity.class);
                intent.putExtra(Const.Details, product);

                startActivity(intent);
            }
        });
        adapter.setCurrency(getCurrency());
        onLinear();
        ViewUtil.setTextViewDrawableColor(listTextView, getResources().getColor(R.color.brown));
    }

    @OnClick({R.id.list_textView, R.id.grid_textView, R.id.filter_textView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.list_textView:
                listTextView.setTextColor(getResources().getColor(R.color.brown));
                ViewUtil.setTextViewDrawableColor(listTextView, getResources().getColor(R.color.brown));

                gridTextView.setTextColor(getResources().getColor(R.color.colorprimary));
                ViewUtil.setTextViewDrawableColor(gridTextView, getResources().getColor(R.color.colorprimary));

                filterTextView.setTextColor(getResources().getColor(R.color.colorprimary));
                ViewUtil.setTextViewDrawableColor(filterTextView, getResources().getColor(R.color.colorprimary));
                filterResults.setVisibility(View.GONE);
                onLinear();
                break;
            case R.id.grid_textView:
                gridTextView.setTextColor(getResources().getColor(R.color.brown));
                ViewUtil.setTextViewDrawableColor(gridTextView, getResources().getColor(R.color.brown));
                listTextView.setTextColor(getResources().getColor(R.color.colorprimary));
                ViewUtil.setTextViewDrawableColor(listTextView, getResources().getColor(R.color.colorprimary));

                filterTextView.setTextColor(getResources().getColor(R.color.colorprimary));
                ViewUtil.setTextViewDrawableColor(filterTextView, getResources().getColor(R.color.colorprimary));
                onGrid();
                break;
            case R.id.filter_textView:
                filterTextView.setTextColor(getResources().getColor(R.color.brown));
                ViewUtil.setTextViewDrawableColor(filterTextView, getResources().getColor(R.color.brown));
                listTextView.setTextColor(getResources().getColor(R.color.colorprimary));
                ViewUtil.setTextViewDrawableColor(listTextView, getResources().getColor(R.color.colorprimary));

                gridTextView.setTextColor(getResources().getColor(R.color.colorprimary));
                ViewUtil.setTextViewDrawableColor(gridTextView, getResources().getColor(R.color.colorprimary));
                filterResults.setVisibility(View.VISIBLE);

                showChangeDialog();
                break;
        }
    }

    private void onGrid() {
        productListRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        adapter.setLayout(GRID);
        productListRecyclerview.setAdapter(adapter);
        filterResults.setVisibility(View.GONE);

        adapter.notifyDataSetChanged();
    }

    private void onLinear() {
        //linearMenu.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_linear));
        // gridMenu.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_grid_un));
        ViewUtil.setTextViewDrawableColor(listTextView, getResources().getColor(R.color.brown));
        productListRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter.setLayout(LINEAR);
        productListRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listTextView.setTextColor(getResources().getColor(R.color.brown));
        filterResults.setVisibility(View.GONE);
    }

    private void showChangeDialog() {
        filterPresenter.get_filter_const();

        dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_filter, false)
                .contentGravity(GravityEnum.START)
                .build();
        dialog.show();

        View view2 = dialog.getCustomView();

        sizeRecyclerView = view2.findViewById(R.id.size_list);
        colorRecyclerView = view2.findViewById(R.id.color_list);
        TextView verifyButton = view2.findViewById(R.id.filter_action);
        MultiSlider multiSlider = view2.findViewById(R.id.range_slider5);
        final TextView from_price = view2.findViewById(R.id.start_price);
        final TextView to_price = view2.findViewById(R.id.endprice_label_box);
        multiSlider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                if (thumbIndex == 0) {
                    fromPrice = value;
                    from_price.setText(fromPrice + "");
                } else
                    toPrice = value;
                to_price.setText(toPrice + "");
            }
        });
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedColor!=null&&selectedSize!=null&&productlist.get(0)!=null)
                   filterPresenter.filter(selectedColor.getId(), selectedSize.getId(), productlist.get(0).getCategoryId(), fromPrice, toPrice);
            }
        });


    }


    @Override
    public void showSize(List<Size> sizeList) {
        sizeRecyclerView.setAdapter(new SizeRecyclerViewAdapter(sizeList, this, new SizeRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void oncolorSelected(Size size, int position) {
                selectedSize = size;

            }
        }));


    }

    @Override
    public void showColor(List<Color> colorList) {
        this.colors = colorList;
        colorRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        colorRecyclerView.setAdapter(new ColorRecyclerViewAdapter(colorList, this, new ColorRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void oncolorSelected(Color color, int position) {
                selectedColor = color;


            }
        }));

    }

    @Override
    public void showProducts(List<Product> products) {
        dialog.dismiss();
        adapter.setProducts(products);
    }

    @Override
    public void isSuccess(boolean isSuccess) {
        if (isSuccess)
            dialog.dismiss();

    }

    @Override
    public void showEmptyFilterResult() {
        dialog.dismiss();
        showMessageDialog(getString(R.string.empty_filter_result));
    }
}
