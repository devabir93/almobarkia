package net.selsela.almobarakeya.ui.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.ui.base.BaseActivity;
import net.selsela.almobarakeya.ui.home.ProductRecyclerViewAdapter;
import net.selsela.almobarakeya.ui.productdeatials.ProductDetailsActivity;
import net.selsela.almobarakeya.util.Const;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends BaseActivity implements FilterMvpView, SearchView.OnQueryTextListener {
    @Inject
    FilterPresenter filterPresenter;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.filter_results)
    TextView filterResults;
    @BindView(R.id.product_list)
    RecyclerView productListRecyclerview;
    public static int GRID = 1;
    public static int LINEAR = 0;
    @BindView(R.id.empty_view)
    TextView emptyView;
    private List<Product> productlist;
    private ProductRecyclerViewAdapter adapter;
    private int selectedPos;
    private boolean isFromSearch;
    private EditText searchEditText;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        filterPresenter.attachView(this);
        if (getIntent().hasExtra("search")) {
            isFromSearch = true;
            invalidateOptionsMenu();
        } else {

            activityTitle = getIntent().getStringExtra(Const.Name);
            productlist = getIntent().getParcelableArrayListExtra(Const.Details);
            initAdapter();
        }
        initToolbar();
    }

    private void initAdapter() {
        if (adapter == null) {
            adapter = new ProductRecyclerViewAdapter(productlist, this, new ProductRecyclerViewAdapter.UpdateDataClickListener() {
                @Override
                public void onproductSelected(Product product, int position) {
                    Intent intent = new Intent(ProductListActivity.this, ProductDetailsActivity.class);
                    intent.putExtra(Const.Details, product);
                    startActivity(intent);
                }

                @Override
                public void onFavProduct(Product product, int pos) {
                    selectedPos = pos;
                    filterPresenter.addToFav(product.getProductId(),getApplicationContext());
                }

            });
            productListRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
            productListRecyclerview.setAdapter(adapter);
            adapter.setCurrency(getCurrency());
        } else adapter.setProducts(productlist);
    }

    @Override
    public void isSuccess(Boolean status) {
        super.isSuccess(status);
        if (status) {
            toggleFav();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchItem.setVisible(isFromSearch);
        searchView.setOnQueryTextListener(this);
        searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorprimary));
        searchEditText.setHintTextColor(getResources().getColor(R.color.transcolorprimary));
        searchEditText.setHint(getString(R.string.search_label));
        searchEditText.setBackground(ContextCompat.getDrawable(this, R.drawable.rect_brown));
        int closeButtonId = getResources().getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeButtonImage = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closeButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEditText.setText("");
                searchView.setQuery("", false);

            }
        });
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(true);
        searchView.requestFocusFromTouch();
        searchView.clearFocus();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.action_search:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleFav() {
        productlist.get(selectedPos).setInFavorite(productlist.get(selectedPos).getInFavorite() == 1 ? 0 : 1);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showSearchResult(List<Product> products) {
        this.productlist = products;
        filterResults.setVisibility(View.VISIBLE);
        filterResults.setText(getString(R.string.search_result));
        initAdapter();

    }


    @Override
    public void showEmpty() {
        super.showEmpty();
        filterResults.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        filterPresenter.search(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

}
