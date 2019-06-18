package com.selsela.example.ui.home;


import com.selsela.example.data.model.home.MainCategory;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.data.model.home.Store;
import com.selsela.example.ui.base.MvpView;

import java.util.List;

public interface HomeMvpView extends MvpView {

    void showSliderProducts(List<Product> productList);
    void showLastProducts(List<Product> lastProducts);
    void showMostPopular(List<Product> mostPopular);
    void showCategories(List<MainCategory> categoryList);
    void showStores(List<Store> stores);


}
