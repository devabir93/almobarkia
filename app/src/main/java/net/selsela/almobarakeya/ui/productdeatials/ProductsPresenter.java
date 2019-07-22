package net.selsela.almobarakeya.ui.productdeatials;


import net.selsela.almobarakeya.data.CartManager;
import net.selsela.almobarakeya.data.DataManager;
import net.selsela.almobarakeya.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


public class ProductsPresenter extends BasePresenter<ProductsMvp> {

    private final DataManager mDataManager;
    private final CartManager mCartManager;
    private Disposable mDisposable;

    @Inject
    public ProductsPresenter(DataManager dataManager, CartManager cartManager) {
        mDataManager = dataManager;
        mCartManager = cartManager;
    }

    @Override
    public void attachView(ProductsMvp mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

}
