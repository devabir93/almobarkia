package net.selsela.almobarakeya.ui.categories;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Color;
import net.selsela.almobarakeya.data.model.home.MainCategory;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.model.home.Size;
import net.selsela.almobarakeya.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import klogi.com.RtlViewPager;
import timber.log.Timber;

public
class CategoriesActivity extends BaseFragment implements CategoryMvpView {

    @Inject
    CategoryPresenter categoryPresenter;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.view_pager)
    RtlViewPager viewPager;
    private List<MainCategory> categoriesList;
    int selectedPos = 0;
    MainCategory mainCategory;

    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
    }

    public void setMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    public static CategoriesActivity newInstance(int selectedPos) {

        CategoriesActivity fragment = new CategoriesActivity();
        fragment.setSelectedPos(selectedPos);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //showToolbar();
        View view = inflater.inflate(R.layout.activity_category_product, container, false);
        ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        categoryPresenter.attachView(this);
        categoryPresenter.getCategories();
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categoryPresenter.getCategories();
            }
        });
        return view;
    }


    void initTab() {
        Timber.d("selectedpos %s", selectedPos);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(), getActivity().getSupportFragmentManager(), categoriesList);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
        viewPager.postDelayed(new Runnable() {

            @Override
            public void run() {
                viewPager.setOffscreenPageLimit(0);
                viewPager.setCurrentItem(selectedPos, true);
            }
        }, 100);
        // viewPager.setCurrentItem(selectedPos, true);
        // tabs.setScrollPosition(selectedPos, 0f, true);
        //tabs.getTabAt(selectedPos).select();
        for (int i = 0; i < tabs.getTabCount(); i++) {
            TabLayout.Tab tab = tabs.getTabAt(i);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(getContext()).inflate(R.layout.tab_layout, tabs, false);

            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
            tabTextView.setText(tab.getText());
            tab.setCustomView(relativeLayout);
            tab.select();
        }
    }

    @Override
    public void showCategories(List<MainCategory> categoryList) {
        this.categoriesList = categoryList;
        initTab();

    }

    @Override
    public void showSize(List<Size> sizes) {

    }

    @Override
    public void showColor(List<Color> colors) {

    }

    @Override
    public void showEmptyFilterResult() {

    }

    @Override
    public void showProducts(List<Product> products) {

    }

    @Override
    public void showSearchResult(List<Product> products) {

    }

    @Override
    public void showFilterCategory(MainCategory category) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
