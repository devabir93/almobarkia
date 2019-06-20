package com.selsela.example.ui.favorites;

import com.selsela.example.data.DataManager;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.user_fav.favData;
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


public class FavouritesPresenter extends BasePresenter<FavMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public FavouritesPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override

    public void attachView(FavMvpView mvpView) {
        super.attachView(mvpView);
    }


    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void get_user_favorites() {
        checkViewAttached();
        getMvpView().onRequestStart();
        RxUtil.dispose(mDisposable);
        mDataManager.get_user_favorites()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<favData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BaseResponse<favData> fav_Data) {
                        if (fav_Data.getData().get_user_favorites() != null && fav_Data.getData().get_user_favorites().size() > 0)
                            getMvpView().showFav(fav_Data.getData().get_user_favorites());
                        else
                            getMvpView().showEmpty();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while get_user_favorites");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            Timber.d("get_user_favorites %s", response);
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


