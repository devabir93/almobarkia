package com.selsela.example.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.selsela.example.R;
import com.selsela.example.data.model.home.MainCategory;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.data.model.home.Store;
import com.selsela.example.data.remote.SelselaService;
import com.selsela.example.ui.base.BaseFragment;
import com.selsela.example.ui.categories.CategoriesActivity;
import com.selsela.example.ui.categories.CategoriesRecyclerViewAdapter;
import com.selsela.example.ui.favorites.FavoriteRecyclerViewAdapter;
import com.selsela.example.ui.productdeatials.ProductDetailsActivity;
import com.selsela.example.ui.productlist.ProductListActivity;
import com.selsela.example.ui.shoppingbasket.ShoppingBasketActivity;
import com.selsela.example.util.Const;
import com.selsela.example.util.SpannedGridLayoutManager2;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment implements HomeMvpView, BaseSliderView.OnSliderClickListener
        , ViewPagerEx.OnPageChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Inject
    HomePresenter homePresenter;
    @BindView(R.id.logo_imageView)
    ImageView logoImageView;
    @BindView(R.id.searchabout_textView)
    TextView searchaboutTextView;
    @BindView(R.id.search_label)
    TextView searchLabel;
    @BindView(R.id.innerLine)
    View innerLine;
    @BindView(R.id.search_editText)
    TextInputEditText searchEditText;
    @BindView(R.id.search_action)
    TextView searchAction;
    @BindView(R.id.imagecount)
    TextView imagecount;
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
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private FavoriteRecyclerViewAdapter adapter;
    private List<Product> sliderProducts;
    private SpannedGridLayoutManager2 spannedGridLayoutManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        homePresenter.attachView(this);
        homePresenter.getHome();

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.getHome();
            }
        });


        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                Intent intent = new Intent(getContext(), ShoppingBasketActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.showall_label, R.id.showallnew_label, R.id.showallthemost_label})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.showall_label:
                Intent intent = new Intent(getContext(), CategoriesActivity.class);
                startActivity(intent);
                break;
            case R.id.showallnew_label:
                Intent intent_all = new Intent(getContext(), ProductListActivity.class);
                startActivity(intent_all);
                break;
            case R.id.showallthemost_label:
                Intent intent_btt = new Intent(getContext(), ProductListActivity.class);
                startActivity(intent_btt);
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
    public void showLastProducts(List<Product> lastProducts) {
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
        newcollectiomHomeList.setLayoutManager(lm);
        newcollectiomHomeList.setHasFixedSize(true);
        // newcollectiomHomeList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        newcollectiomHomeList.setAdapter(new ImageListRecyclerViewAdapter(lastProducts, getContext(), new ImageListRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onproductSelected(Product product, int position) {
                Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                intent.putExtra(Const.Details, product);
                getContext().startActivity(intent);

            }
        }));
    }

    @Override
    public void showMostPopular(List<Product> mostPopular) {
        themostorderList.setNestedScrollingEnabled(false);
        adapter = new FavoriteRecyclerViewAdapter(mostPopular, getContext(), new FavoriteRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onproductSelected(Product product, int position) {
                Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                intent.putExtra(Const.Details, product);
                getContext().startActivity(intent);
            }
        });
        adapter.setLayout(ProductListActivity.GRID);
        themostorderList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter.setCurrency(getCurrency());
        themostorderList.setAdapter(adapter);

    }

    @Override
    public void showCategories(List<MainCategory> categoryList) {
        categoriesHomeList.setNestedScrollingEnabled(false);
        categoriesHomeList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        categoriesHomeList.setAdapter(new CategoriesRecyclerViewAdapter(categoryList, getContext(), new CategoriesRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onCategorySelected(MainCategory category, int position) {
                Intent intent = new Intent(getContext(), ProductListActivity.class);
                startActivity(intent);
            }
        }));


    }

    @Override
    public void showStores(List<Store> stores) {

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
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setCustomIndicator(custom_indicator);
        // mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(5000);
        mDemoSlider.addOnPageChangeListener(this);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
