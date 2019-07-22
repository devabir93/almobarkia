package net.selsela.almobarakeya.injection.component;

import dagger.Component;
import net.selsela.almobarakeya.data.SyncService;
import net.selsela.almobarakeya.injection.PerService;
import net.selsela.almobarakeya.injection.module.ServiceModule;
import net.selsela.almobarakeya.util.notification.MyFirebaseMessagingService;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);

    void inject(MyFirebaseMessagingService myFirebaseMessagingService);

}