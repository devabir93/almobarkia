package com.selsela.example.ui.orders;


import com.selsela.example.data.DataManager;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.home.HomeData;
import com.selsela.example.data.model.order.OrderData;
import com.selsela.example.ui.base.BasePresenter;
import com.selsela.example.ui.home.HomeMvpView;
import com.selsela.example.util.RetrofitException;
import com.selsela.example.util.RxUtil;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class OrdersPresenter extends BasePresenter<OrdresMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public OrdersPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override

    public void attachView(OrdresMvpView mvpView) {
        super.attachView(mvpView);
    }


    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void get_orders() {
        checkViewAttached();
        getMvpView().onRequestStart();
        RxUtil.dispose(mDisposable);
        mDataManager.get_orders()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<OrderData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<OrderData> orderData) {
                        if (orderData.getData().getOrders() != null && orderData.getData().getOrders().size() > 0)
                             getMvpView().showOrders(orderData.getData().getOrders());

                        else
                            getMvpView().showEmpty();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while getCountries");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            Timber.d("getCountries %s", response);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        getMvpView().onRequestEnd();
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().onRequestEnd();
                    }
                });
    }




}


