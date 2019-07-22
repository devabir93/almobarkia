package net.selsela.almobarakeya.ui.categories;


import net.selsela.almobarakeya.data.DataManager;
import net.selsela.almobarakeya.data.model.BaseResponse;
import net.selsela.almobarakeya.data.model.category.CategoriesData;
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


public class CategoryPresenter extends BasePresenter<CategoryMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public CategoryPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override

    public void attachView(CategoryMvpView mvpView) {
        super.attachView(mvpView);
    }


    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void getCategories() {
        checkViewAttached();
        getMvpView().onRequestStart();
        RxUtil.dispose(mDisposable);
        mDataManager.get_categories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<CategoriesData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<CategoriesData> categoryData) {
                        getMvpView().showCategories(categoryData.getData().getMainCategories());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while getCategories");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            Timber.d("getCategories %s", response);
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

    public void get_category_products(int catID) {
        checkViewAttached();
        getMvpView().onRequestStart();
        RxUtil.dispose(mDisposable);
        mDataManager.get_category_products(catID, 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<MainCategory>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<MainCategory> categoryData) {
                        getMvpView().showCategories(categoryData.getData().getCategory().getSubCategories());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while get_category_products");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            Timber.d("get_category_products %s", response);
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

    public void get_filter_const() {
        checkViewAttached();
        getMvpView().showProgressView(true);

        // getMvpView().onRequestStart();
        RxUtil.dispose(mDisposable);
        mDataManager.get_filter_const()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<FilterData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<FilterData> filterData) {
                        getMvpView().showSize(filterData.getData().getSizes());
                        getMvpView().showColor(filterData.getData().getColors());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while getFilter");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            Timber.d("getFilter %s", response);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        getMvpView().showProgressView(false);

                        // getMvpView().onRequestEnd();

                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgressView(false);

                        //getMvpView().onRequestEnd();
                    }
                });
    }


    public void filter(int colorId, int sizeId
            , int categoryId, int priceFrom, int priceTo) {
        checkViewAttached();
        getMvpView().showProgressView(true);

        // getMvpView().onRequestStart();
        RxUtil.dispose(mDisposable);
        mDataManager.filter(colorId, sizeId, categoryId, priceFrom, priceTo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<MainCategory>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<MainCategory> mainCategory) {
                        if (mainCategory != null && mainCategory.getData().getCategory() != null) {
                            getMvpView().showFilterCategory(mainCategory.getData().getCategory());
                        } else
                            getMvpView().showEmptyFilterResult();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while getFilter");
                        RetrofitException error = (RetrofitException) e;
                        try {
                            BaseResponse response = error.getErrorBodyAs(BaseResponse.class);
                            Timber.d("getFilter %s", response);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        getMvpView().showProgressView(false);

                        // getMvpView().onRequestEnd();

                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgressView(false);

                        //getMvpView().onRequestEnd();
                    }
                });
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


