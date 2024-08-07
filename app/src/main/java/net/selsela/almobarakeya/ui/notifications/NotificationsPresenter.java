package net.selsela.almobarakeya.ui.notifications;


import net.selsela.almobarakeya.data.DataManager;
import net.selsela.almobarakeya.data.model.BaseResponse;
import net.selsela.almobarakeya.data.model.notifications.Notificationsdata;
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


public class NotificationsPresenter extends BasePresenter<NotificationsMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public NotificationsPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override

    public void attachView(NotificationsMvpView mvpView) {
        super.attachView(mvpView);
    }


    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void get_notifications() {
        checkViewAttached();
        getMvpView().onRequestStart();
        RxUtil.dispose(mDisposable);
        mDataManager.get_notifications()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<Notificationsdata>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<Notificationsdata> notificatonsData) {
                        if (notificatonsData.getData() != null && notificatonsData.getData().getNotifications() != null && notificatonsData.getData().getNotifications().size() > 0)
                            getMvpView().showNotifications(notificatonsData.getData().getNotifications());
                        else
                            getMvpView().showEmpty();


                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while getNotifications");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            Timber.d("getNotifications %s", response);
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


