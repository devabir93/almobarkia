package net.selsela.almobarakeya.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.model.user_fav.favProduct;
import net.selsela.almobarakeya.ui.base.BaseActivity;
import net.selsela.almobarakeya.ui.home.ProductRecyclerViewAdapter;
import net.selsela.almobarakeya.ui.productdeatials.ProductDetailsActivity;
import net.selsela.almobarakeya.ui.productlist.ProductListActivity;
import net.selsela.almobarakeya.util.Const;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FavoritesActivity extends BaseActivity implements FavMvpView {

    @Inject
    FavouritesPresenter favouritesPresenter;
    @BindView(R.id.favoritelist)
    RecyclerView favoritelist;
    Unbinder unbinder;
    @BindView(R.id.empty_fav)
    LinearLayout emptyFav;

    FavoriteRecyclerViewAdapter favAdapter;
    private int selectedPos;

    private boolean isEmpty;
    private Product favProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_item_list);
        getActivityComponent().inject(this);
        unbinder = ButterKnife.bind(this);
        favouritesPresenter.attachView(this);
        activityTitle = getString(R.string.title_fav);
        initToolbar();

        getFav();
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFav();
            }
        });
    }

    @Override
    public void showFav(List<favProduct> favorites) {


        favAdapter = new FavoriteRecyclerViewAdapter(favorites, this, new ProductRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onproductSelected(Product product, int position) {
                Intent intent = new Intent(FavoritesActivity.this, ProductDetailsActivity.class);
                intent.putExtra(Const.Details, product);
                startActivity(intent);
            }

            @Override
            public void onFavProduct(Product product, int pos) {
                selectedPos = pos;
                favProduct = product;
                favouritesPresenter.addToFav(product.getProductId(), getApplicationContext());

            }
        });
        favAdapter.setLayout(ProductListActivity.GRID);
        favAdapter.setCurrency(getCurrency());
        favoritelist.setLayoutManager(new GridLayoutManager(this, 2));
        favoritelist.setAdapter(favAdapter);
        emptyFav.setVisibility(View.GONE);

    }

    @Override
    public void isSuccessAll(Boolean isSucess) {
        if (isSucess) {
            showEmpty();
        } else
            getFav();
    }

    @Override
    public void isSuccess(Boolean status) {
        super.isSuccess(status);
        if (status) {
            EventBus.getDefault().postSticky(new FavProduct(favProduct));

            favAdapter.removeAt(selectedPos);
            checkAdapterIsEmpty();
        }

    }

    private void checkAdapterIsEmpty() {
        if (favAdapter.getItemCount() == 0) {
            emptyFav.setVisibility(View.VISIBLE);
            favoritelist.setVisibility(View.GONE);
        } else {
            emptyFav.setVisibility(View.GONE);
        }
    }

    private void getFav() {
        if (hasInternetConnection()) {
            favouritesPresenter.get_user_favorites();
        } else {
            stopRefreshing();
            showEmpty();
            hasActiveInternetConnection(false);
        }
    }

    @Override
    public void showEmpty() {
        stopRefreshing();
        isEmpty = true;
        favoritelist.setVisibility(View.GONE);
        emptyFav.setVisibility(View.VISIBLE);
    }
}
