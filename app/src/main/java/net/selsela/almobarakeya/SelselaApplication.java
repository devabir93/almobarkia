package net.selsela.almobarakeya;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.crashlytics.android.Crashlytics;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.orm.SugarContext;
import net.selsela.almobarakeya.data.local.PreferencesHelper;
import net.selsela.almobarakeya.injection.component.ApplicationComponent;
import net.selsela.almobarakeya.injection.component.DaggerApplicationComponent;
import net.selsela.almobarakeya.injection.module.ApplicationModule;
import net.selsela.almobarakeya.util.language.Language;
import net.selsela.almobarakeya.util.language.LanguageUtils;

import com.orm.SugarDb;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;

import java.util.Locale;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class SelselaApplication extends Application {

    ApplicationComponent mApplicationComponent;
    private PreferencesHelper sharedPreferences;
    private LanguageUtils mLanguageUtils;
    private Locale locale = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        if (net.selsela.almobarakeya.BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
//            Fabric.with(this, new Crashlytics());
        }

        sharedPreferences = new PreferencesHelper(this);
        mLanguageUtils = new LanguageUtils(this, sharedPreferences);
        Stetho.initializeWithDefaults(this);
        InternetAvailabilityChecker.init(this);
        initInjection();
        mLanguageUtils.initLocal();
        Fresco.initialize(this);
        SugarContext.init(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
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
