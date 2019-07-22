package net.selsela.almobarakeya.ui.productlist;

import net.selsela.almobarakeya.data.model.home.Color;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.model.home.Size;
import net.selsela.almobarakeya.ui.base.MvpView;
import java.util.List;

public interface FilterMvpView extends MvpView {
    void showSearchResult(List<Product> products);
}
