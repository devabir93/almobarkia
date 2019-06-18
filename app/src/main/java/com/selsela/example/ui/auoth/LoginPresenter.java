package com.selsela.example.ui.auoth;

import android.content.Context;

import com.selsela.example.data.DataManager;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.user.LoginData;
import com.selsela.example.data.model.user.UserBody;
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


public class LoginPresenter extends BasePresenter<LoginMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(LoginMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void login(final Context context, final UserBody userBody) {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.makeLogin(userBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<LoginData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse<LoginData> loginResponse) {

                        getMvpView().isSuccess(true);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while login");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            com.selsela.almobarakia.data.model.ErrorResponse response = error.getErrorBodyAs(com.selsela.almobarakia.data.model.ErrorResponse.class);
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

    public void register(final Context context, final UserBody userBody) {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.makeRegister(userBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<LoginData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse<LoginData> loginResponse) {
                        getMvpView().isSuccess(loginResponse.getStatus());
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
                        }

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}


