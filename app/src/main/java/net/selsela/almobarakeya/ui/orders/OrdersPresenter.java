package net.selsela.almobarakeya.ui.orders;


import android.content.Context;

import net.selsela.almobarakeya.data.DataManager;
import net.selsela.almobarakeya.data.model.BaseResponse;
import net.selsela.almobarakeya.data.model.order.OrderData;
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
import okhttp3.MultipartBody;
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


    public void rate_product(final Context context, final UserBody userBody) {
        checkViewAttached();
        getMvpView().showProgressView(true);
        RxUtil.dispose(mDisposable);
        mDataManager.rate_product(userBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse loginResponse) {
                        //getMvpView().isSuccess(loginResponse.getStatus());
                        getMvpView().showMessageDialog(loginResponse.getResponseMessage());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while rate_product");
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


    public void specialOrder(final Context context, final MultipartBody userBody) {
        checkViewAttached();
        getMvpView().showProgressView(true);
        RxUtil.dispose(mDisposable);
        mDataManager.specialOrder(userBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse response) {
                        getMvpView().isSuccess(response.getStatus());
                        getMvpView().showMessageDialog(response.getResponseMessage());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while specialOrder");
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


