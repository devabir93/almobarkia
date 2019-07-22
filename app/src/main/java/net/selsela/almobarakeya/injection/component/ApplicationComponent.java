package net.selsela.almobarakeya.injection.component;

import android.app.Application;
import android.content.Context;

import net.selsela.almobarakeya.data.CartManager;
import net.selsela.almobarakeya.data.DataManager;
import net.selsela.almobarakeya.data.SyncService;
import net.selsela.almobarakeya.data.local.PreferencesHelper;
import net.selsela.almobarakeya.data.local.UserSession;
import net.selsela.almobarakeya.data.remote.SelselaService;
import net.selsela.almobarakeya.injection.ApplicationContext;
import net.selsela.almobarakeya.injection.module.ApplicationModule;
import net.selsela.almobarakeya.util.RxEventBus;
import net.selsela.almobarakeya.util.language.LanguageUtils;

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

    CartManager cartManager();

    RxEventBus eventBus();

    UserSession userSession();

    LanguageUtils languageUtils();

}
