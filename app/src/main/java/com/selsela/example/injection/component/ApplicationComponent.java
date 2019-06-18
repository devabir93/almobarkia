package com.selsela.example.injection.component;

import android.app.Application;
import android.content.Context;

import com.selsela.example.data.DataManager;
import com.selsela.example.data.SyncService;
import com.selsela.example.data.local.PreferencesHelper;
import com.selsela.example.data.local.UserSession;
import com.selsela.example.data.remote.SelselaService;
import com.selsela.example.injection.ApplicationContext;
import com.selsela.example.injection.module.ApplicationModule;
import com.selsela.example.util.RxEventBus;
import com.selsela.example.util.language.LanguageUtils;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(SyncService syncService);

    @ApplicationContext
    Context context();

    Application application();

    SelselaService ribotsService();

    PreferencesHelper preferencesHelper();

    DataManager dataManager();

    RxEventBus eventBus();

    UserSession userSession();

    LanguageUtils languageUtils();

}
