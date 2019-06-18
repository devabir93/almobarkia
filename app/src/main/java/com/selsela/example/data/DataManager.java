package com.selsela.example.data;

import com.selsela.example.data.local.PreferencesHelper;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.about.AboutData;
import com.selsela.example.data.model.config.ConfigData;
import com.selsela.example.data.model.country.CountryData;
import com.selsela.example.data.model.home.HomeData;
import com.selsela.example.data.model.user.LoginData;
import com.selsela.example.data.model.user.UserBody;
import com.selsela.example.data.model.user.UserData;
import com.selsela.example.data.remote.SelselaService;
import com.selsela.example.util.Const;
import com.selsela.example.util.language.LanguageUtils;

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

   // int getUserId() {
       // return getUserSession() != null ? Integer.parseInt(getAuthnticate().getUserId()) : 0;
//    }
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
                .concatMap(new Function<BaseResponse< CountryData >, ObservableSource<? extends BaseResponse<CountryData>>>() {
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
        return mSelselaService.get_home(mPreferencesHelper.getCountry().getId(),getUserSession().getId())
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

}
