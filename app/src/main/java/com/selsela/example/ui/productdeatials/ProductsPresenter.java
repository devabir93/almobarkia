package com.selsela.example.ui.productdeatials;


import com.selsela.example.data.CartManager;
import com.selsela.example.data.DataManager;
import com.selsela.example.ui.base.BasePresenter;
import com.selsela.example.util.RxUtil;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import timber.log.Timber;


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
