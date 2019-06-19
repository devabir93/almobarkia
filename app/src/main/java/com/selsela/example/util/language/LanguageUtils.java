package com.selsela.example.util.language;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

import com.selsela.example.data.local.PreferencesHelper;
import com.selsela.example.injection.ApplicationContext;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

@Singleton
public class LanguageUtils {

    @Inject PreferencesHelper mPreferencesHelper;
    private Context context;

    @Inject
    public LanguageUtils(@ApplicationContext Context context, PreferencesHelper mPreferencesHelper) {
        this.context = context;
        this.mPreferencesHelper = mPreferencesHelper;
    }


    public void setEnglishLocale() {
        setLocale(Language.ENGLISH());
    }

    public void setArabicLocale() {
        setLocale(Language.ARABIC());
    }

    public boolean isArabic() {
        if (getCurrentLang().equals("" + Language.ARABIC())) return true;
        return false;
    }

    //Locale.ENGLISH
    public Context setLocale(Context context, String localStr) {
        Timber.d("lang init to  %s", localStr);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Timber.d("updateResources");
            return updateResources(localStr, context);
        } else {
            Timber.d("updateResourcesLegacy");
            return updateResourcesLegacy(localStr, context);
        }
    }

    //Locale.ENGLISH
    public Context setLocale(String localStr) {
        Timber.d("lang init to  %s", localStr);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Timber.d("updateResources");
            return updateResources(localStr, context);
        } else {
            Timber.d("updateResourcesLegacy");
            return updateResourcesLegacy(localStr, context);
        }
    }


    // For android device versions above Nougat (7.0)
    // updates application default locale configurations and
    // returns new Context object for the current Context but whose resources are adjusted to match the given Configuration
    @TargetApi(Build.VERSION_CODES.N)
    private Context updateResources(String language, Context context) {


        Resources res = context.getResources();
        Configuration configuration = res.getConfiguration();
        Locale newLocale = new Locale(language);

        configuration.setLocale(newLocale);
        LocaleList localeList = new LocaleList(newLocale);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
//        context = context.createConfigurationContext(configuration);

        mPreferencesHelper.addWithKey(Language.KEY(), language);
        return context.createConfigurationContext(configuration);
    }

    // For android device versions below Nougat (7.0)
    // updates application default locale configurations and
    // returns new Context object for the current Context but whose resources are adjusted to match the given Configuration
    @SuppressWarnings("deprecation")
    private Context updateResourcesLegacy(String localStr, Context context) {
        Locale localeLang = new Locale(localStr);
        Locale.setDefault(localeLang);
        Configuration config = context.getResources().getConfiguration();
        config.locale = localeLang;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        mPreferencesHelper.addWithKey(Language.KEY(), localStr);
        return context;
    }

    public void initLocal() {
        if (getCurrentLang().equals("")) {
            Locale locale = context.getResources().getConfiguration().locale;
            Timber.d("lang init %s", locale.toString());

            String currentLocal = locale.toString().substring(0, 2);
            setLocale(currentLocal);
        } else if (getCurrentLang().equals(Language.ARABIC())) {
            setLocale(Language.ARABIC());
        } else if (getCurrentLang().equals(Language.ENGLISH())) {
            setLocale(Language.ENGLISH());
        }
    }


    public String getCurrentLang() {
        return mPreferencesHelper.getWithKey(Language.KEY());
    }
}
