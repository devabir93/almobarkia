package net.selsela.almobarakeya.ui.updateprofile;

import android.content.Context;

import net.selsela.almobarakeya.data.DataManager;
import net.selsela.almobarakeya.data.model.BaseResponse;
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
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            if (response != null )
                                getMvpView().showMessageDialog(response);
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
        getMvpView().showProgressView(true);
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
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            if (response != null )
                                getMvpView().showMessageDialog(response);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (RetrofitException e1) {
                            e1.printStackTrace();
                        }      getMvpView().showProgressView(false);


                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgressView(false);
                    }
                });
    }

    public void create_guest_password(final Context context, final UserBody userBody) {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        getMvpView().showProgressView(true);
        mDataManager.create_guest_password(userBody)
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
                        Timber.e(e, "There was an error while create_guest_password");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            if (response != null )
                                getMvpView().showMessageDialog(response);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (RetrofitException e1) {
                            e1.printStackTrace();
                        }      getMvpView().showProgressView(false);


                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgressView(false);
                    }
                });
    }


}


