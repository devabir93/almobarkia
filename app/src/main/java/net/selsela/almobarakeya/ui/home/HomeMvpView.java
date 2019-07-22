package net.selsela.almobarakeya.ui.home;


import net.selsela.almobarakeya.data.model.home.MainCategory;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.model.home.Store;
import net.selsela.almobarakeya.ui.base.MvpView;

import java.util.List;

public interface HomeMvpView extends MvpView {

    void showSliderProducts(List<Product> productList);
    void showLastProducts(List<Product> lastProducts);
    void showMostPopular(List<Product> mostPopular);
    void showCategories(List<MainCategory> categoryList);
    void showStores(List<Store> stores);
    void showSearchResult(List<Product> products);



}
