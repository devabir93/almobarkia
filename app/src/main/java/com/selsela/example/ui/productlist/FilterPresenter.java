package com.selsela.example.ui.productlist;

import com.selsela.example.data.DataManager;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.filter.FilterData;
import com.selsela.example.data.model.filter.Filterdata;
import com.selsela.example.data.model.home.MainCategory;
import com.selsela.example.ui.base.BasePresenter;
import com.selsela.example.util.RetrofitException;
import com.selsela.example.util.RxUtil;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class FilterPresenter extends BasePresenter<FilterMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public FilterPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override

    public void attachView(FilterMvpView mvpView) {
        super.attachView(mvpView);
    }


    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void get_filter_const() {
        checkViewAttached();
        //getMvpView().showProgressView(true);

        getMvpView().onRequestStart();
        RxUtil.dispose(mDisposable);
        mDataManager.get_filter_const()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<FilterData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<FilterData> filterData) {
                        getMvpView().showSize(filterData.getData().getSizes());
                        getMvpView().showColor(filterData.getData().getColors());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while getFilter");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            Timber.d("getFilter %s", response);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        getMvpView().showProgressView(false);

                        getMvpView().onRequestEnd();

                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgressView(false);

                        getMvpView().onRequestEnd();
                    }
                });
    }


    public void filter(int colorId, int sizeId
            , int categoryId, int priceFrom, int priceTo) {
        checkViewAttached();
        getMvpView().showProgressView(true);

        getMvpView().onRequestStart();
        RxUtil.dispose(mDisposable);
        mDataManager.filter(colorId, sizeId, categoryId, priceFrom, priceTo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<MainCategory>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<MainCategory> mainCategory) {
                        if (mainCategory != null && mainCategory.getData().getProducts() != null && mainCategory.getData().getProducts().size() > 0) {
                            getMvpView().showProducts(mainCategory.getData().getProducts());
                        }else
                            getMvpView().showEmptyFilterResult();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while getFilter");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            Timber.d("getFilter %s", response);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        getMvpView().showProgressView(false);

                        getMvpView().onRequestEnd();

                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgressView(false);

                        getMvpView().onRequestEnd();
                    }
                });
    }


}


