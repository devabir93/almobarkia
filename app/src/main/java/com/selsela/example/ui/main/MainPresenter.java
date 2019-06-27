package com.selsela.example.ui.main;

import android.content.Context;

import com.selsela.example.data.DataManager;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.about.AboutData;
import com.selsela.example.data.model.config.ConfigData;
import com.selsela.example.data.model.error.ErrorResponse;
import com.selsela.example.ui.about.AboutMvpView;
import com.selsela.example.ui.base.BasePresenter;
import com.selsela.example.util.RetrofitException;
import com.selsela.example.util.RxUtil;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void getSettingData() {
        Timber.d("getSettingData");
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getSettingData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<ConfigData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<ConfigData> settingData) {
                        getMvpView().showSettingData(settingData.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while register");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            ErrorResponse response = error.getErrorBodyAs(ErrorResponse.class);
                            if (response != null && response.getResponseMessage() != null)
                                getMvpView().showMessageDialog(response.getResponseMessage());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (RetrofitException e1) {
                            e1.printStackTrace();
                        }

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}


