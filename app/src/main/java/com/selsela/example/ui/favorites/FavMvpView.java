package com.selsela.example.ui.favorites;


import com.selsela.example.data.model.user_fav.favProduct;
import com.selsela.example.ui.base.MvpView;

import java.util.List;

public interface FavMvpView extends MvpView {

    void showFav(List<favProduct> favorites);
    void showEmpty();
    void isSuccessAll(Boolean isSucess);
    void isSuccess(    Boolean status);



}
