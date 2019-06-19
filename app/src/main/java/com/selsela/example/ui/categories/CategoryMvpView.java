package com.selsela.example.ui.categories;


import com.selsela.example.data.model.home.MainCategory;
import com.selsela.example.ui.base.MvpView;

import java.util.List;

public interface CategoryMvpView extends MvpView {

    void showCategories(List<MainCategory> categoryList);



}
