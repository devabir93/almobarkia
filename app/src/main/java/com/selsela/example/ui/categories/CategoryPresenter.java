package com.selsela.example.ui.categories;


import com.selsela.example.data.DataManager;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.category.CategoriesData;
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


public class CategoryPresenter extends BasePresenter<CategoryMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public CategoryPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override

    public void attachView(CategoryMvpView mvpView) {
        super.attachView(mvpView);
    }


    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void getCategories() {
        checkViewAttached();
        getMvpView().onRequestStart();
        RxUtil.dispose(mDisposable);
        mDataManager.get_categories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<CategoriesData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<CategoriesData> categoryData) {
                        getMvpView().showCategories(categoryData.getData().getMainCategories());



                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while getCategories");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            Timber.d("getCategories %s", response);
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


