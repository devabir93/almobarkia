package net.selsela.almobarakeya.ui.shoppingbasket;

import com.google.gson.Gson;
import net.selsela.almobarakeya.data.CartManager;
import net.selsela.almobarakeya.data.DataManager;
import net.selsela.almobarakeya.data.local.UserSession;
import net.selsela.almobarakeya.data.model.BaseResponse;
import net.selsela.almobarakeya.data.model.coupon.CheckCoponData;
import net.selsela.almobarakeya.data.model.order.OrderBody;
import net.selsela.almobarakeya.data.model.order.OrderData;
import net.selsela.almobarakeya.data.model.send_order.ProductOrderBody;
import net.selsela.almobarakeya.ui.base.BasePresenter;
import net.selsela.almobarakeya.util.CartBadge;
import net.selsela.almobarakeya.util.RetrofitException;
import net.selsela.almobarakeya.util.RxUtil;

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
//                        if (response.getStatus() &&
//                                response.getData() != null &&
//                                response.getData().getOrder().getKnetUrl() != null && !response.getData().getOrder().getKnetUrl().isEmpty() &&
//                                response.getData().getOrder().getKnetToken() != null && !response.getData().getOrder().getKnetToken().isEmpty()) {
//                            getMvpView().doPayment(response.getData().getOrder());
//                        } else

                        if (response.getStatus()) {
                            getMvpView().showSuccess(response.getResponseMessage());
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while sendOrder");
                        getMvpView().onRequestEnd();
                        try {
                            RetrofitException error = (RetrofitException) e;
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            Timber.d("response %s", response);
                            if (response != null) {

                                getMvpView().showMessageDialog(response);

                            }

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (HttpException e1) {
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
                        getMvpView().isSuccess(true);
                        if (productOrderList != null && productOrderList.size() > 0) {
                            // getMvpView().showSavedOrders(productOrderList);
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

    public void checkCopon(String code) {
        checkViewAttached();
        getMvpView().showProgressView(true);
        Timber.d("checkCopon");
        RxUtil.dispose(mDisposable);
        mDataManager.checkCopon(code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<CheckCoponData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<CheckCoponData> generalResponse) {
                        if (generalResponse.getStatus())
                            getMvpView().showCoupone(generalResponse.getData().getCopone());
                        else {
                            getMvpView().showMessageDialog(generalResponse.getResponseMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while checkCopon");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            if (response != null) {
                                getMvpView().showMessageDialog(response);
                            }
                        } catch (IOException e1) {
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
