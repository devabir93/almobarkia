package com.selsela.example.ui.shoppingbasket;

import com.google.gson.Gson;
import com.selsela.example.data.CartManager;
import com.selsela.example.data.DataManager;
import com.selsela.example.data.local.UserSession;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.order.OrderBody;
import com.selsela.example.data.model.order.OrderData;
import com.selsela.example.data.model.send_order.ProductOrderBody;
import com.selsela.example.ui.base.BasePresenter;
import com.selsela.example.util.CartBadge;
import com.selsela.example.util.RetrofitException;
import com.selsela.example.util.RxUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import timber.log.Timber;


public class PaymentPresenter extends BasePresenter<PaymentMvpView> {

    private final DataManager mDataManager;
    private final CartManager mCartManager;
    @Inject
    UserSession userSession;
    private Disposable mDisposable;

    @Inject
    public PaymentPresenter(DataManager dataManager, CartManager cartManager) {
        mDataManager = dataManager;
        mCartManager = cartManager;
    }

    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
    }

    @Override
    public void attachView(PaymentMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void sendOrder(OrderBody addressBody) {
        Timber.d("sendOrder %s", addressBody);
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        getMvpView().showProgressView(true);
        mCartManager.sendOrder(addressBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<OrderData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse<OrderData> response) {
                        Timber.d("response %s", response);
                        if (response.getStatus() &&
                                response.getData() != null &&
                                response.getData().getOrder().getKnetUrl() != null && !response.getData().getOrder().getKnetUrl().isEmpty() &&
                                response.getData().getOrder().getKnetToken() != null && !response.getData().getOrder().getKnetToken().isEmpty()) {
                            getMvpView().doPayment(response.getData().getOrder());
                        } else if (response.getStatus()) {
                            getMvpView().showSuccess(response.getResponseMessage());
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while sendOrder");
                        getMvpView().onRequestEnd();
                        try {
                            RetrofitException error = (RetrofitException) e;
                            com.selsela.almobarakia.data.model.ErrorResponse response = error.getErrorBodyAs(com.selsela.almobarakia.data.model.ErrorResponse.class);
                            Timber.d("response %s", response);
                            if (response != null) {

                                getMvpView().showMessageDialog(response.getResponseMessage());

                            }

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (HttpException e1) {
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().onRequestEnd();
                        //getMvpView().showProgresBar(false);
                    }
                });
    }

    public void getSavedOrders() {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mCartManager.getSavedOrders()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<ProductOrderBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<ProductOrderBody> productOrderList) {
                        Timber.d("productOrderList %s", productOrderList);
                        if (productOrderList != null && productOrderList.size() > 0) {
                            getMvpView().showSavedOrders(productOrderList);
                        } else {
                            getMvpView().showEmpty();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while getSavedOrders");
                        //getMvpView().showError();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public void deleteOrder(ProductOrderBody checkedProductOrders) {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mCartManager.deleteOrder(checkedProductOrders)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<ProductOrderBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<ProductOrderBody> productOrderList) {
                        Timber.d("productOrderList %s", productOrderList);
                        if (productOrderList != null && productOrderList.size() > 0) {
                            getMvpView().showSavedOrders(productOrderList);
                            EventBus.getDefault().postSticky(new CartBadge(productOrderList.size()));
                        } else {
                            EventBus.getDefault().postSticky(new CartBadge(0));
                            getMvpView().showEmpty();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while getSearchResult");

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
