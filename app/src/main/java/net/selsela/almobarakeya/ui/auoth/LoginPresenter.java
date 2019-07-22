package net.selsela.almobarakeya.ui.auoth;

import android.content.Context;

import net.selsela.almobarakeya.data.DataManager;
import net.selsela.almobarakeya.data.model.BaseResponse;
import net.selsela.almobarakeya.data.model.user.LoginData;
import net.selsela.almobarakeya.data.model.user.UserBody;
import net.selsela.almobarakeya.ui.base.BasePresenter;
import net.selsela.almobarakeya.util.RetrofitException;
import net.selsela.almobarakeya.util.RxUtil;

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
        getMvpView().showProgressView(true);

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
                        getMvpView().showSuccessDialog(loginResponse.getMessage());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while login");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            if (response != null)
                                getMvpView().showMessageDialog(response);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (RetrofitException e1) {
                            e1.printStackTrace();
                        }
                        getMvpView().showProgressView(false);


                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgressView(false);

                    }
                });
    }

    public void register(final Context context, final UserBody userBody) {
        checkViewAttached();
        getMvpView().showProgressView(true);

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
                        getMvpView().showSuccessDialog(loginResponse.getMessage());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while register");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            if (response != null)
                                getMvpView().showMessageDialog(response);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (RetrofitException e1) {
                            e1.printStackTrace();
                        }
                        getMvpView().showProgressView(false);


                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgressView(false);

                    }
                });
    }

    public void contact_us(final Context context, final UserBody userBody) {
        checkViewAttached();
        getMvpView().showProgressView(true);
        RxUtil.dispose(mDisposable);
        mDataManager.contact(userBody)
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
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            if (response != null)
                                getMvpView().showMessageDialog(response);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (RetrofitException e1) {
                            e1.printStackTrace();
                        }
                        getMvpView().showProgressView(false);


                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgressView(false);

                    }
                });
    }

    public void guest_log_reg(final Context context, final UserBody userBody) {
        checkViewAttached();
        getMvpView().showProgressView(true);
        RxUtil.dispose(mDisposable);
        mDataManager.guest_log_reg(userBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<LoginData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse<LoginData> loginResponse) {
                        Timber.d("onNext");
                        getMvpView().isSuccess(loginResponse.getStatus());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while guet_login");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            if (response != null)
                                getMvpView().showMessageDialog(response);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (RetrofitException e1) {
                            e1.printStackTrace();
                        }
                        getMvpView().showProgressView(false);


                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgressView(false);

                    }
                });
    }

    public void forget_password(final Context context, final UserBody userBody) {
        checkViewAttached();
        getMvpView().showProgressView(true);
        RxUtil.dispose(mDisposable);
        mDataManager.forget_password(userBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse loginResponse) {
                        getMvpView().isSuccess(true);
                        getMvpView().showMessageDialog(loginResponse.getResponseMessage());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while register");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            if (response != null)
                                getMvpView().showMessageDialog(response);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (RetrofitException e1) {
                            e1.printStackTrace();
                        }
                        getMvpView().showProgressView(false);


                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgressView(false);

                    }
                });
    }


}



