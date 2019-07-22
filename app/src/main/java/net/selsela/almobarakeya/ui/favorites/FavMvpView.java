package net.selsela.almobarakeya.ui.favorites;


import net.selsela.almobarakeya.data.model.user_fav.favProduct;
import net.selsela.almobarakeya.ui.base.MvpView;

import java.util.List;

public interface FavMvpView extends MvpView {

    void showFav(List<favProduct> favorites);
    void showEmpty();
    void isSuccessAll(Boolean isSucess);
    void isSuccess(    Boolean status);



}
