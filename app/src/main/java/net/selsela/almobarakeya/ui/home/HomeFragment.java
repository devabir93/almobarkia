package net.selsela.almobarakeya.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.MainCategory;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.model.home.Store;
import net.selsela.almobarakeya.data.remote.SelselaService;
import net.selsela.almobarakeya.ui.base.BaseFragment;
import net.selsela.almobarakeya.ui.categories.CategoriesRecyclerViewAdapter;
import net.selsela.almobarakeya.ui.countries.CountriesActivity;
import net.selsela.almobarakeya.ui.favorites.FavProduct;
import net.selsela.almobarakeya.ui.favorites.FavoritesActivity;
import net.selsela.almobarakeya.ui.main.MainActivity;
import net.selsela.almobarakeya.ui.productdeatials.ProductDetailsActivity;
import net.selsela.almobarakeya.ui.productlist.ProductListActivity;
import net.selsela.almobarakeya.ui.shoppingbasket.ShoppingBasketActivity;
import net.selsela.almobarakeya.util.CartBadge;
import net.selsela.almobarakeya.util.Const;
import net.selsela.almobarakeya.util.DialogFactory;
import net.selsela.almobarakeya.util.SpannedGridLayoutManager2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;


public class HomeFragment extends BaseFragment implements HomeMvpView, BaseSliderView.OnSliderClickListener
        , ViewPagerEx.OnPageChangeListener, SearchView.OnQueryTextListener, CategoriesRecyclerViewAdapter.UpdateDataClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Inject
    HomePresenter homePresenter;
    @BindView(R.id.categories_label)
    TextView categoriesLabel;
    @BindView(R.id.showall_label)
    TextView showallLabel;
    @BindView(R.id.categories_homeList)
    RecyclerView categoriesHomeList;
    @BindView(R.id.newcollection_label)
    TextView newcollectionLabel;
    @BindView(R.id.showallnew_label)
    TextView showallnewLabel;
    @BindView(R.id.newcollectiom_homeList)
    RecyclerView newcollectiomHomeList;
    @BindView(R.id.themostorder_label)
    TextView themostorderLabel;
    @BindView(R.id.showallthemost_label)
    TextView showallthemostLabel;
    @BindView(R.id.themostorder_list)
    RecyclerView themostorderList;
    @BindView(R.id.slider)
    SliderLayout mDemoSlider;
    @BindView(R.id.custom_indicator)
    PagerIndicator custom_indicator;
    @BindView(R.id.search_toolbar)
    ImageView searchToolbar;
    @BindView(R.id.fav_toolbar)
    ImageView favToolbar;
    @BindView(R.id.flag_toolbar)
    ImageView flagToolbar;
    @BindView(R.id.bag_iv)
    ImageView bagIv;
    @BindView(R.id.cart_count)
    TextView cartCount;
    //    @BindView(R.id.notification)
//    RelativeLayout notification;
    @BindView(R.id.home_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.imagecount)
    TextView imagecount;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.logo_imageView)
    ImageView logoImageView;


    private List<Product> sliderProducts;
    private SpannedGridLayoutManager2 spannedGridLayoutManager;
    private List<Product> lastProducts, mostProducts, productList;
    private int mNotifCount = 0;
    private int selectedPos;
    private List<MainCategory> categoryList;
    private CategoriesRecyclerViewAdapter categriesAdapter;
    private ProductRecyclerViewAdapter laseAdapter, mostAdapter;
    private String flag = "";
    private String MostPopular = "MostPopular";
    private String LastProducts = "LastProducts";
    private Product favedProduct;
    private boolean found;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setHasOptionsMenu(true);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //hideToolbar();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        homePresenter.attachView(this);
        homePresenter.getHome();
        homePresenter.getCartBadge();
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.getHome();
            }
        });
        initToolbar();
        return view;
    }

    private void initToolbar() {
        searchToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toggleVisibility();
                startActivity(new Intent(getContext(), ProductListActivity.class).putExtra("search", "search"));

            }
        });
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                toggleVisibility();
                return true;
            }
        });
        RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(getContext()).load(SelselaService.IMAGE_URL + preferencesHelper.getCountry().getFlag()).apply(requestOptions).into(flagToolbar);
        flagToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CountriesActivity.class).putExtra("update", "update"));

            }
        });
        favToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserLogged()) {
                    startActivity(new Intent(getContext(), FavoritesActivity.class).putExtra("Fav", "Fav"));
                } else
                    DialogFactory.showAlertDialog(getActivity(), getString(R.string.signin_confrimation));

            }
        });
    }

    private void toggleVisibility() {
        if (searchView.getVisibility() == View.VISIBLE)
            searchView.setVisibility(View.GONE);
        else
            searchView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
        homePresenter.getCartBadge();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void updateBadge(CartBadge cartBadge) {
        this.mNotifCount = cartBadge.getCount();
        showCartBadge(mNotifCount);
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void updateProduct(FavProduct favProduct) {
        Timber.d("updateProduct");

        if (mostProducts.contains(favProduct.getFav())) {
            Timber.d("mostProducts");
            int itemIndex = mostProducts.indexOf(favProduct.getFav());
            if (itemIndex != -1) {
                mostProducts.set(itemIndex, favProduct.getFav());
                mostAdapter.notifyDataSetChanged();
            }
        }

        if (lastProducts.contains(favProduct.getFav())) {
            Timber.d("lastProducts");
            int itemIndex = lastProducts.indexOf(favProduct.getFav());
            if (itemIndex != -1) {
                lastProducts.set(itemIndex, favProduct.getFav());
                laseAdapter.notifyDataSetChanged();
            }
        }


        if (sliderProducts != null && sliderProducts.contains(favProduct.getFav())) {
            Timber.d("sliderProduct");
            int itemIndex = sliderProducts.indexOf(favProduct.getFav());
            if (itemIndex != -1) {
                Timber.d("sliderProduct index");
                sliderProducts.set(itemIndex, favProduct.getFav());
            }
        }
        for (MainCategory mainCategory :
                categoryList) {
            for (MainCategory category :
                    mainCategory.getSubCategories()) {
                if (category.getProducts() != null && category.getProducts().size() > 0) {
                    if (category.getProducts().contains(favProduct.getFav())) {
                        int itemIndex = category.getProducts().indexOf(favProduct.getFav());
                        if (itemIndex != -1) {
                            Timber.d("category index");
                            category.getProducts().set(itemIndex, favProduct.getFav());
                            categriesAdapter.notifyDataSetChanged();
                            found = true;
                            break;
                        }
                        if (found) break;
                    }
                }
                if (found) break;
            }
        }
    }

    @Override
    public void isSuccess(Boolean status) {
        super.isSuccess(status);
//        if (status) {
//            updateProduct(new FavProduct(favedProduct));
//        }
    }

    @Override
    public void showCartBadge(Integer i) {
        Timber.d("i %s", i);
        mNotifCount = i;
        if (cartCount != null)
            if (i > 0) {
                Timber.d("i %s", i);
                cartCount.setVisibility(View.VISIBLE);
                cartCount.setText(i + "");
            } else {
                cartCount.setVisibility(View.INVISIBLE);
            }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                Intent intent = new Intent(getContext(), ShoppingBasketActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.showall_label, R.id.showallnew_label, R.id.showallthemost_label, R.id.fav_toolbar, R.id.bag_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.showall_label:
                ((MainActivity) getActivity()).goToSpecificFragment(1);

                break;
            case R.id.showallnew_label:
                Intent intent_all = new Intent(getContext(), ProductListActivity.class);
                intent_all.putExtra(Const.Details, (ArrayList) lastProducts);
                intent_all.putExtra(Const.Name, getString(R.string.arrived_label));
                startActivity(intent_all);
                break;
            case R.id.showallthemost_label:
                Intent intent_btt = new Intent(getContext(), ProductListActivity.class);
                intent_btt.putExtra(Const.Details, (ArrayList) mostProducts);
                intent_btt.putExtra(Const.Name, getString(R.string.mosteorder_label));
                startActivity(intent_btt);
                break;

            case R.id.fav_toolbar:
                if (isUserLogged()) {
                    startActivity(new Intent(getContext(), FavoritesActivity.class));
                } else
                    DialogFactory.showAlertDialog(getActivity(), getString(R.string.signin_confrimation));
                break;
            case R.id.bag_iv:
                startActivity(new Intent(getContext(), ShoppingBasketActivity.class));
                break;
        }
    }

    @Override
    public void showSliderProducts(List<Product> productList) {
        this.sliderProducts = productList;
        mDemoSlider.removeAllSliders();
        setSliderViews(productList);

    }

    @Override
    public void showLastProducts(final List<Product> lastProducts) {
        this.lastProducts = lastProducts;
        //  newcollectiomHomeList.setNestedScrollingEnabled(false);
        spannedGridLayoutManager = new SpannedGridLayoutManager2(
                new SpannedGridLayoutManager2.GridSpanLookup() {
                    @Override
                    public SpannedGridLayoutManager2.SpanInfo getSpanInfo(int position) {
                        // Conditions for 2x2 items
                        if (position % 4 == 0) {
                            return new SpannedGridLayoutManager2.SpanInfo(1, 2);
                        } else if (position % 2 == 1) {
                            return new SpannedGridLayoutManager2.SpanInfo(2, 1);
                        }
                        return new SpannedGridLayoutManager2.SpanInfo(1, 1);
                    }
                },
                2, // number of columns
                1f // how big is default item
        );
        StaggeredGridLayoutManager lm =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        // newcollectiomHomeList.setLayoutManager(lm);
        newcollectiomHomeList.setHasFixedSize(true);
        newcollectiomHomeList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        laseAdapter = new ProductRecyclerViewAdapter(lastProducts, getContext(), new ProductRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onproductSelected(Product product, int position) {
                Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                intent.putExtra(Const.Details, product);
                getContext().startActivity(intent);

            }

            @Override
            public void onFavProduct(Product product, int pos) {
                favedProduct = product;
                homePresenter.addToFav(product, getContext());
            }
        });
        laseAdapter.setLayoutWidth(true);
        laseAdapter.setLayout(ProductListActivity.GRID);
        laseAdapter.setCurrency(getCurrency());
        newcollectiomHomeList.setAdapter(laseAdapter);
    }

    @Override
    public void showMostPopular(final List<Product> mostPopular) {
        this.mostProducts = mostPopular;
        themostorderList.setNestedScrollingEnabled(false);
        mostAdapter = new ProductRecyclerViewAdapter(mostProducts, getContext(), new ProductRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onproductSelected(Product product, int position) {
                Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                intent.putExtra(Const.Details, product);
                startActivity(intent);
            }

            @Override
            public void onFavProduct(Product product, int pos) {
                favedProduct = product;
                homePresenter.addToFav(product, getContext());

            }
        });
        mostAdapter.setLayoutWidth(true);
        mostAdapter.setLayout(ProductListActivity.GRID);
        mostAdapter.setCurrency(getCurrency());
        themostorderList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        themostorderList.setAdapter(mostAdapter);

    }

    @Override
    public void showCategories(List<MainCategory> categoryList) {
        this.categoryList = categoryList;
        categoriesHomeList.setNestedScrollingEnabled(false);
        categoriesHomeList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        categriesAdapter = new CategoriesRecyclerViewAdapter(categoryList, getContext(), this);
        categoriesHomeList.setAdapter(categriesAdapter);
    }

    @Override
    public void onCategorySelected(MainCategory category, int position) {

        ((MainActivity) getActivity()).goToCategoriesFragment(position, category);
    }

    @Override
    public void showStores(List<Store> stores) {

    }

    @Override
    public void showSearchResult(List<Product> products) {
        Intent intent = new Intent(getContext(), ProductListActivity.class);
        intent.putExtra(Const.Details, (ArrayList) products);
        intent.putExtra(Const.Name, getString(R.string.search_result));
        startActivity(intent);
    }


    private void setSliderViews(List<Product> images) {
        for (Product image : images) {
            SlideAdapter defaultSliderView = new SlideAdapter(getContext());
            // initialize a SliderLayout
            // imagesUriForFullScreen.add(SelselaService.IMAGE_URL+image.getImage());
            defaultSliderView.setData(image, getCurrency());
            defaultSliderView
                    .image(SelselaService.IMAGE_URL + image.getImage())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            //add your extra information
            defaultSliderView.bundle(new Bundle());
            defaultSliderView.getBundle()
                    .putString("extra", image.getImage());
            mDemoSlider.addSlider(defaultSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setCustomIndicator(custom_indicator);
        mDemoSlider.stopAutoCycle();
        // mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(null);
        mDemoSlider.setDuration(5000);
        mDemoSlider.addOnPageChangeListener(this);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        if (sliderProducts.get(mDemoSlider.getCurrentPosition()).getSlideType().equals(Product.productSlider))
            startActivity(new Intent(getContext(), ProductDetailsActivity.class).putExtra(Const.Details, sliderProducts.get(mDemoSlider.getCurrentPosition())));
        else {
            String url = sliderProducts.get(mDemoSlider.getCurrentPosition()).getUrl();
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
        // new ImageViewer.Builder(OrderDetailsActivity.this, imagesUriForFullScreen).setStartPosition(mDemoSlider.getCurrentPosition()).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        int currentPos = position + 1;
        imagecount.setText(currentPos + "/" + sliderProducts.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        homePresenter.search(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void showEmpty() {
        super.showEmpty();
        DialogFactory.showAlertDialog(getActivity(), getString(R.string.no_result_search));
    }

}
