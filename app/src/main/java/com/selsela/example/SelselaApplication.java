package com.selsela.example;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.facebook.stetho.Stetho;
import com.selsela.example.data.local.PreferencesHelper;
import com.selsela.example.injection.component.ApplicationComponent;
import com.selsela.example.injection.component.DaggerApplicationComponent;
import com.selsela.example.injection.module.ApplicationModule;
import com.selsela.example.util.language.Language;
import com.selsela.example.util.language.LanguageUtils;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;

import java.util.Locale;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class SelselaApplication extends Application {

    ApplicationComponent mApplicationComponent;
    private PreferencesHelper sharedPreferences;
    private LanguageUtils mLanguageUtils;
    private Locale locale = null;

    @Override
    public void onCreate() {
        super.onCreate();

        if (com.selsela.example.BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
//            Fabric.with(this, new Crashlytics());
        }
// configure font for all app
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/din_next_arabic_regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        sharedPreferences = new PreferencesHelper(this);
        mLanguageUtils = new LanguageUtils(this, sharedPreferences);
        Stetho.initializeWithDefaults(this);
        InternetAvailabilityChecker.init(this);
        initInjection();
        mLanguageUtils.initLocal();

    }

    private Context setLang() {
        String currnetLang = sharedPreferences.getWithKey(Language.KEY());
        if (currnetLang.equals("")) {
//            to get device lange
            locale = getResources().getConfiguration().locale;
            currnetLang = locale.toString().substring(0, 2);
        }

        return mLanguageUtils.setLocale(currnetLang);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mLanguageUtils.initLocal();
        //setLang();
    }

    public static SelselaApplication get(Context context) {
        return (SelselaApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            initInjection();
        }
        return mApplicationComponent;
    }

    private void initInjection() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
