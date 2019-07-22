package net.selsela.almobarakeya.ui.categories;


import net.selsela.almobarakeya.data.model.home.Color;
import net.selsela.almobarakeya.data.model.home.MainCategory;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.model.home.Size;
import net.selsela.almobarakeya.ui.base.MvpView;

import java.util.List;

public interface CategoryMvpView extends MvpView {

    void showCategories(List<MainCategory> categoryList);

    void showSize(List<Size> sizes);

    void showColor(List<Color> colors);

    void showEmptyFilterResult();

    void showProducts(List<Product> products);

    void showSearchResult(List<Product> products);

    void showFilterCategory(MainCategory category);
}
