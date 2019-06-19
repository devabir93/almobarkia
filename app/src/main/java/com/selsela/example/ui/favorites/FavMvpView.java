package com.selsela.example.ui.favorites;


import com.selsela.example.data.model.home.MainCategory;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.ui.base.MvpView;

import java.util.List;

public interface FavMvpView extends MvpView {

    void showFav(List<Product> favorites);



}
