package com.selsela.example.data;

import android.content.Context;

import com.selsela.example.data.local.PreferencesHelper;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.about.AboutData;
import com.selsela.example.data.model.category.CategoriesData;
import com.selsela.example.data.model.config.ConfigData;
import com.selsela.example.data.model.country.CountryData;
import com.selsela.example.data.model.filter.Filterdata;
import com.selsela.example.data.model.home.HomeData;
import com.selsela.example.data.model.home.MainCategory;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.data.model.notifications.Notificationsdata;
import com.selsela.example.data.model.order.OrderData;
import com.selsela.example.data.model.user.LoginData;
import com.selsela.example.data.model.user.UserBody;
import com.selsela.example.data.model.user.UserData;
import com.selsela.example.data.model.user_fav.favData;
import com.selsela.example.data.remote.SelselaService;
import com.selsela.example.util.Const;
import com.selsela.example.util.language.LanguageUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import timber.log.Timber;

@Singleton
public class DataManager {

    private final SelselaService mSelselaService;
    private final PreferencesHelper mPreferencesHelper;
    private final LanguageUtils languageUtils;

    @Inject
    public DataManager(SelselaService selselaService, PreferencesHelper preferencesHelper,
                       LanguageUtils languageUtils) {
        mSelselaService = selselaService;
        mPreferencesHelper = preferencesHelper;
        this.languageUtils = languageUtils;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public LanguageUtils getLanguageUtils() {
        return languageUtils;
    }

    public UserData getUserSession() {
        return getPreferencesHelper().getCurrentUser();
    }

    int getCountryID() {
        return getPreferencesHelper().getCountry().getId();
    }

    int getUserId() {
        return getUserSession() != null ? getUserSession().getId() : 0;
    }

    //    @android.support.annotation.NonNull
    public Observable<BaseResponse<LoginData>> makeLogin(final UserBody userData) {

        return mSelselaService.login(userData)
                .concatMap(new Function<BaseResponse<LoginData>, ObservableSource<? extends BaseResponse<LoginData>>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse<LoginData>> apply(final BaseResponse<LoginData> loginResponse) {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse<LoginData>>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse<LoginData>> e) {
                                try {
                                    UserData user = loginResponse.getData().getUserData();
                                    getPreferencesHelper().addUserSession(user);
                                    if (!languageUtils.isArabic())
                                        loginResponse.setResponseMessage(loginResponse.getResponseMessageEn());
                                    e.onNext(loginResponse);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();


                            }
                        });
                    }
                });
    }


    @android.support.annotation.NonNull
    public Observable<BaseResponse<LoginData>> makeRegister(final UserBody userData) {

        return mSelselaService.register(userData)
                .concatMap(new Function<BaseResponse<LoginData>, ObservableSource<? extends BaseResponse<LoginData>>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse<LoginData>> apply(final BaseResponse<LoginData> loginResponse) {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse<LoginData>>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse<LoginData>> e) {
                                try {
                                    UserData user = loginResponse.getData().getUserData();
                                    getPreferencesHelper().addUserSession(user);
                                    e.onNext(loginResponse);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();


                            }
                        });
                    }
                });
    }
    @android.support.annotation.NonNull
    public Observable<BaseResponse> contact(final UserBody userData) {

        return mSelselaService.contact_us(userData)
                .concatMap(new Function<BaseResponse, ObservableSource<? extends BaseResponse>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse> apply(final BaseResponse loginResponse) {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse> e) {
                                try {
                                    e.onNext(loginResponse);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();


                            }
                        });
                    }
                });
    }


    @android.support.annotation.NonNull
    public Observable<BaseResponse> guest_log_reg(final UserBody userData) {

        return mSelselaService.guest_log_reg(userData)
                .concatMap(new Function<BaseResponse, ObservableSource<? extends BaseResponse>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse> apply(final BaseResponse loginResponse) {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse> e) {
                                try {
                                    e.onNext(loginResponse);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();


                            }
                        });
                    }
                });
    }








    @android.support.annotation.NonNull
    public Observable<BaseResponse> update (final UserBody userData) {

        return mSelselaService.update_profile(userData)
                .concatMap(new Function<BaseResponse, ObservableSource<? extends BaseResponse>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse> apply(final BaseResponse loginResponse) {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse> e) {
                                try {
                                    e.onNext(loginResponse);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();


                            }
                        });
                    }
                });
    }









        @android.support.annotation.NonNull
        public Observable<BaseResponse> change_pass (final UserBody userData) {

            return mSelselaService.change_password(userData)
                    .concatMap(new Function<BaseResponse, ObservableSource<? extends BaseResponse>>() {
                        @Override
                        public ObservableSource<? extends BaseResponse> apply(final BaseResponse loginResponse) {
                            return Observable.create(new ObservableOnSubscribe<BaseResponse>() {
                                @Override
                                public void subscribe(ObservableEmitter<BaseResponse> e) {
                                    try {
                                        e.onNext(loginResponse);
                                    } catch (Exception e1) {
                                        e.onError(e1);
                                    }
                                    e.onComplete();


                                }
                            });
                        }
                    });
    }

   @android.support.annotation.NonNull
    public Observable<BaseResponse> forget_password (final UserBody userData) {

        return mSelselaService.change_password(userData)
                .concatMap(new Function<BaseResponse, ObservableSource<? extends BaseResponse>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse> apply(final BaseResponse loginResponse) {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse> e) {
                                try {
                                    e.onNext(loginResponse);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();


                            }
                        });
                    }
                });
    }



    @android.support.annotation.NonNull
    public Observable<BaseResponse> rate_product (final UserBody userData) {

        return mSelselaService.add_rate(userData)
                .concatMap(new Function<BaseResponse, ObservableSource<? extends BaseResponse>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse> apply(final BaseResponse loginResponse) {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse> e) {
                                try {
                                    e.onNext(loginResponse);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();


                            }
                        });
                    }
                });
    }




    @android.support.annotation.NonNull
    public Observable<BaseResponse> specialOrder (final UserBody userData) {

        return mSelselaService.specialOrder(userData)
                .concatMap(new Function<BaseResponse, ObservableSource<? extends BaseResponse>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse> apply(final BaseResponse loginResponse) {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse> e) {
                                try {
                                    e.onNext(loginResponse);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();


                            }
                        });
                    }
                });
    }

    public Observable<BaseResponse<AboutData>> getAbout(String about) {
        Observable<BaseResponse<AboutData>> observable = mSelselaService.get_about_page();
        if (about.equals(Const.Rules)) {
            observable = mSelselaService.get_rules_page();
        } else if (about.equals(Const.Policy)) {
            observable = mSelselaService.get_policies_page();
        } else if (about.equals(Const.Usage)) {
            observable = mSelselaService.get_safty_page();
        }
        return observable
                .concatMap(new Function<BaseResponse<AboutData>, ObservableSource<? extends BaseResponse<AboutData>>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse<AboutData>> apply(final BaseResponse<AboutData> aboutResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse<AboutData>>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse<AboutData>> e) throws Exception {
                                try {
                                    Timber.d("aboutResponse %s", aboutResponse);
                                    e.onNext(aboutResponse);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();
                            }
                        });
                    }
                })
                ;
    }

    public Observable<BaseResponse<CountryData>> get_countries() {
        return mSelselaService.get_countries()
                .concatMap(new Function<BaseResponse<CountryData>, ObservableSource<? extends BaseResponse<CountryData>>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse<CountryData>> apply(final BaseResponse<CountryData> response) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse<CountryData>>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse<CountryData>> e) throws Exception {
                                try {
                                    Timber.d("response %s", response);
                                    if (response.getStatus()) {
                                        e.onNext(response);
                                    } else e.onNext(null);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();
                            }
                        });
                    }
                });
    }


    public Observable<BaseResponse<Filterdata>> get_filter_const() {
        return mSelselaService.get_filter_const()
                .concatMap(new Function<BaseResponse<Filterdata>, ObservableSource<? extends BaseResponse<Filterdata>>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse<Filterdata>> apply(final BaseResponse<Filterdata> response) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse<Filterdata>>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse<Filterdata>> e) throws Exception {
                                try {
                                    Timber.d("response %s", response);
                                    if (response.getStatus()) {
                                        e.onNext(response);
                                    } else e.onNext(null);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();
                            }
                        });
                    }
                });
    }

    public Observable<BaseResponse<MainCategory>> filter(int colorId, int sizeId
            , int categoryId, int priceFrom, int priceTo) {
        return mSelselaService.filter(getCountryID(),colorId, sizeId, categoryId, priceFrom, priceTo)
                .concatMap(new Function<BaseResponse<MainCategory>, ObservableSource<? extends BaseResponse<MainCategory>>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse<MainCategory>> apply(final BaseResponse<MainCategory> response) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse<MainCategory>>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse<MainCategory>> e) throws Exception {
                                try {
                                    Timber.d("response %s", response);
                                    if (response.getStatus()) {
                                        e.onNext(response);
                                    } else e.onNext(null);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();
                            }
                        });
                    }
                });
    }

    public Observable<BaseResponse<Notificationsdata>> get_notifications() {
        return mSelselaService.get_notifications(getUserId(),getUserSession().getToken())
                .concatMap(new Function<BaseResponse<Notificationsdata>, ObservableSource<? extends BaseResponse<Notificationsdata>>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse<Notificationsdata>> apply(final BaseResponse<Notificationsdata> response) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse<Notificationsdata>>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse<Notificationsdata>> e) throws Exception {
                                try {
                                    Timber.d("response %s", response);
                                    if (response.getStatus()) {
                                        e.onNext(response);
                                    } else e.onNext(null);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();
                            }
                        });
                    }
                });
    }


    public Observable<List<Product>> getSearchResult(final String key) {

        return mSelselaService.search(key)
                .concatMap(new Function<BaseResponse, ObservableSource<? extends List<Product>>>() {
                    @Override
                    public ObservableSource<? extends List<Product>> apply(final BaseResponse response) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<List<Product>>() {
                            @Override
                            public void subscribe(ObservableEmitter<List<Product>> e) throws Exception {
                                try {
                                    Timber.d("productsResponse %s", response);
                                   // e.onNext(response.);
                                    e.onComplete();
                                } catch (Exception ex) {
                                    e.onError(ex);
                                }
                            }
                        });
                    }
                });
    }


    public Observable<BaseResponse<ConfigData>> getSettingData() {

        return mSelselaService.get_config()
                .concatMap(new Function<BaseResponse<ConfigData>, ObservableSource<? extends BaseResponse<ConfigData>>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse<ConfigData>> apply(final BaseResponse<ConfigData> settingResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse<ConfigData>>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse<ConfigData>> e) throws Exception {

                                try {
                                    getPreferencesHelper().addUserSetting(settingResponse.getData());
                                    e.onNext(settingResponse);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();
                            }
                        });
                    }
                })
                ;
    }


    public Observable<BaseResponse<HomeData>> get_home() {
        return mSelselaService.get_home(mPreferencesHelper.getCountry().getId(), getUserId())
                .concatMap(new Function<BaseResponse<HomeData>, ObservableSource<? extends BaseResponse<HomeData>>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse<HomeData>> apply(final BaseResponse<HomeData> response) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse<HomeData>>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse<HomeData>> e) throws Exception {
                                try {
                                    if (response.getStatus()) {
                                        e.onNext(response);
                                    } else e.onNext(null);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();
                            }
                        });
                    }
                });
    }

    public Observable<BaseResponse<CategoriesData>> get_categories() {
        return mSelselaService.get_categories(mPreferencesHelper.getCountry().getId(), getUserId())
                .concatMap(new Function<BaseResponse<CategoriesData>, ObservableSource<? extends BaseResponse<CategoriesData>>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse<CategoriesData>> apply(final BaseResponse<CategoriesData> response) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse<CategoriesData>>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse<CategoriesData>> e) throws Exception {
                                try {
                                    if (response.getStatus()) {
                                        e.onNext(response);
                                    }
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();
                            }
                        });
                    }
                });
    }

    public Observable<BaseResponse<OrderData>> get_orders() {
        return mSelselaService.get_orders(getUserId(), getUserSession().getToken())
                .concatMap(new Function<BaseResponse<OrderData>, ObservableSource<? extends BaseResponse<OrderData>>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse<OrderData>> apply(final BaseResponse<OrderData> response) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse<OrderData>>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse<OrderData>> e) throws Exception {
                                try {
                                    if (response.getStatus()) {
                                        e.onNext(response);
                                    }
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();
                            }
                        });
                    }
                });
    }
    public Observable<BaseResponse<favData>> get_user_favorites() {
        return mSelselaService.get_user_favorites( getUserSession().getId(),getUserSession().getToken())
                .concatMap(new Function<BaseResponse<favData>, ObservableSource<? extends BaseResponse<favData>>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse<favData>> apply(final BaseResponse<favData> response) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse<favData>>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse<favData>> e) throws Exception {
                                try {
                                    if (response.getStatus()) {
                                        e.onNext(response);
                                    }
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();
                            }
                        });
                    }
                });
    }

}
