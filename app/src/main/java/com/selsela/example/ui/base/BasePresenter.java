package com.selsela.example.ui.base;

import com.selsela.example.data.CartManager;
import com.selsela.example.data.local.UserSession;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.boxes.Box;
import com.selsela.example.data.model.send_order.ProductOrderBody;
import com.selsela.example.util.RetrofitException;
import com.selsela.example.util.language.LanguageUtils;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;
    @Inject
    public UserSession userSession;
    @Inject
    public LanguageUtils languageUtils;

    @Inject
    CartManager dataManager;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

    public void checkMaxWeight(ProductOrderBody productOrder) {
        checkViewAttached();
        dataManager.checkExceedWight(productOrder)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
/*                        if (userSession.getSelectedCountry().getPrefix().equals(Const.KUWAIT))
                            aBoolean = false;*/
                        mMvpView.isExceedWeight(aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getShippingBoxes() {
        getMvpView().showProgressView(true);
        checkViewAttached();
//        RxUtil.dispose(mDisposable);
        dataManager.getShippingBoxes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Box>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        //mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Box> getGovs) {
                        Timber.d("getShippingBoxes %s", getGovs);
                        if (getGovs != null && getGovs.size() > 0) {
                            //getMvpView().showBoxes(getGovs);
                        } else {
                            getMvpView().showEmpty();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while getShippingBoxes");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            if (response != null) {

                                Timber.d("response %s", response);
                                getMvpView().showMessageDialog(response.getResponseMessage());
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        //getMvpView().showError();
                        getMvpView().showProgressView(false);
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgressView(false);
                    }
                });
    }

    public boolean isArabic() {
        return languageUtils.isArabic();
    }

    public void getCartPrice() {
        checkViewAttached();
        dataManager.getCartPrice()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Double>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Double price) {
                        Timber.d("updateCart %s", price);
                        getMvpView().showCartPrice(price);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Timber.e(e, "There was an error while getCartPrice");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public void getCartBadge() {
        checkViewAttached();
        dataManager.getCartCount()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Timber.d("getCartBadge %s", integer);
                        getMvpView().showCartBadge(integer);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Timber.e(e, "There was an error while login");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void getProductById(final int productId, final int colorId, final int imageID,int sizeId) {
        checkViewAttached();
        dataManager.getProductById(productId, colorId, imageID,sizeId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ProductOrderBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull ProductOrderBody productOrder) {
                        Timber.d("productOrder %s", productOrder);
                        getMvpView().showSavedProductOrder(productOrder);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Timber.e(e, "There was an error while getProductById");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void saveProduct(ProductOrderBody productOrder) {
        dataManager.addTobag(productOrder)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer aBoolean) {
                        Timber.d("saveProduct %s", aBoolean);
                        getMvpView().showCartBadge(aBoolean);
                        getCartBadge();
                        getCartPrice();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while addToBag");

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}

