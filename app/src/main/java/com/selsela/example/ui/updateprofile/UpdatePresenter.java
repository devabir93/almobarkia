package com.selsela.example.ui.updateprofile;

import android.content.Context;

import com.selsela.example.data.DataManager;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.user.LoginData;
import com.selsela.example.data.model.user.UserBody;
import com.selsela.example.ui.auoth.LoginMvpView;
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


public class UpdatePresenter extends BasePresenter<UpdateMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public UpdatePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(UpdateMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void update_profile(final Context context, final UserBody userBody) {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.update(userBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse loginResponse) {
                        getMvpView().showMessageDialog(loginResponse.getResponseMessage());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while register");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            com.selsela.almobarakia.data.model.ErrorResponse response = error.getErrorBodyAs(com.selsela.almobarakia.data.model.ErrorResponse.class);
                            if (response != null && response.getResponseMessage() != null)
                                getMvpView().showMessageDialog(response.getResponseMessage());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (RetrofitException e1) {
                            e1.printStackTrace();
                        }  //     getMvpView().showProgressView(false);


                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void change_password(final Context context, final UserBody userBody) {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.change_pass(userBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse loginResponse) {
                        getMvpView().isSuccess(loginResponse.getStatus());
                        getMvpView().showMessageDialog(loginResponse.getResponseMessage());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while change_password");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            com.selsela.almobarakia.data.model.ErrorResponse response = error.getErrorBodyAs(com.selsela.almobarakia.data.model.ErrorResponse.class);
                            if (response != null && response.getResponseMessage() != null)
                                getMvpView().showMessageDialog(response.getResponseMessage());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (RetrofitException e1) {
                            e1.printStackTrace();
                        }  //     getMvpView().showProgressView(false);


                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}


