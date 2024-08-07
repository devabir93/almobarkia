package net.selsela.almobarakeya.ui.countries;

import net.selsela.almobarakeya.data.DataManager;
import net.selsela.almobarakeya.data.model.BaseResponse;
import net.selsela.almobarakeya.data.model.country.CountryData;
import net.selsela.almobarakeya.ui.base.BasePresenter;
import net.selsela.almobarakeya.util.RetrofitException;
import net.selsela.almobarakeya.util.RxUtil;
import java.io.IOException;

import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class CountryPresenter extends BasePresenter<CountryMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public CountryPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(CountryMvpView mvpView) {
        super.attachView(mvpView);
    }


    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void getCountries() {
        Timber.d("getCountries");
        checkViewAttached();
        getMvpView().onRequestStart();
        RxUtil.dispose(mDisposable);
        mDataManager.get_countries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<CountryData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<CountryData> countries) {
                        Timber.d("countries %s", countries);
                        getMvpView().showCountries(countries.getData().getCountry());
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


