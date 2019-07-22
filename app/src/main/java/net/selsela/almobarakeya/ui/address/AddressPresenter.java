package net.selsela.almobarakeya.ui.address;


import net.selsela.almobarakeya.data.DataManager;
import net.selsela.almobarakeya.data.model.BaseResponse;
import net.selsela.almobarakeya.data.model.address.AddressData;
import net.selsela.almobarakeya.data.model.address.Gov;
import net.selsela.almobarakeya.ui.base.BasePresenter;
import net.selsela.almobarakeya.util.RetrofitException;
import net.selsela.almobarakeya.util.RxUtil;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import timber.log.Timber;


public class AddressPresenter extends BasePresenter<AddressMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public AddressPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(AddressMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void getAddress() {
        Timber.d("getAddress");
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        getMvpView().onRequestStart();
        mDataManager.get_address()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<AddressData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse<AddressData> response) {
                        Timber.d("response %s", response);
                        if (response.getData().getAddresses() != null && response.getData().getAddresses().size() > 0
                        ) {
                            getMvpView().showAddress(response.getData().getAddresses());
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e, "There was an error while getAddress");
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
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().onRequestEnd();
                        //getMvpView().showProgresBar(false);
                    }
                });
    }

    public void getGov() {
        getMvpView().onRequestStart();
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getGov()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Gov>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Gov> response) {
                        if (response != null && response.size() > 0)
                            getMvpView().showGov(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while getGov");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            if (response != null )
                                getMvpView().showMessageDialog(response);
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
