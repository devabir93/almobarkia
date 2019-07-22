package net.selsela.almobarakeya.ui.productlist;

import net.selsela.almobarakeya.data.DataManager;
import net.selsela.almobarakeya.data.model.BaseResponse;
import net.selsela.almobarakeya.data.model.filter.FilterData;
import net.selsela.almobarakeya.data.model.home.MainCategory;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.ui.base.BasePresenter;
import net.selsela.almobarakeya.util.RetrofitException;
import net.selsela.almobarakeya.util.RxUtil;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class FilterPresenter extends BasePresenter<FilterMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public FilterPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override

    public void attachView(FilterMvpView mvpView) {
        super.attachView(mvpView);
    }


    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }
    public void search(String key) {
        checkViewAttached();
        getMvpView().onRequestStart();
        RxUtil.dispose(mDisposable);
        mDataManager.getSearchResult(key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Product> products) {
                        if (products != null && products.size() > 0)
                            getMvpView().showSearchResult(products);
                        else
                            getMvpView().showEmpty();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while search");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            Timber.d("search %s", response);
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


