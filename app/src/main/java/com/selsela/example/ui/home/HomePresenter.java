package com.selsela.example.ui.home;



import com.selsela.example.data.DataManager;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.home.HomeData;
import com.selsela.example.ui.base.BasePresenter;
import com.selsela.example.util.RetrofitException;
import com.selsela.example.util.RxUtil;
import java.io.IOException;


import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;



public class HomePresenter extends BasePresenter<HomeMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public HomePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override

    public void attachView(HomeMvpView mvpView) {
        super.attachView(mvpView);
    }


    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void getHome() {
        checkViewAttached();
        getMvpView().onRequestStart();
        RxUtil.dispose(mDisposable);
        mDataManager.get_home()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<HomeData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<HomeData> homeData) {
                        getMvpView().showSliderProducts(homeData.getData().getSlider());
                        getMvpView().showMostPopular(homeData.getData().getMostPopular());
                        getMvpView().showLastProducts(homeData.getData().getLastProducts());
                        getMvpView().showCategories(homeData.getData().getMainCategories());



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




}


