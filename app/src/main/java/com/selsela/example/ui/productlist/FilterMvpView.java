package com.selsela.example.ui.productlist;

import com.selsela.example.data.model.filter.Color;
import com.selsela.example.data.model.filter.Size;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.ui.base.MvpView;
import java.util.List;

public interface FilterMvpView extends MvpView {
    void showSize(List<Size> sizeList);
    void showColor(List<Color> colorList);
    void showProducts(List<Product> products);
    void isSuccess(boolean isSuccess);

    void showEmptyFilterResult();
}
