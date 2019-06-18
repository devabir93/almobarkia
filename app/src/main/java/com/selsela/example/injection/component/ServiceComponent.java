package com.selsela.example.injection.component;

import dagger.Component;
import com.selsela.example.data.SyncService;
import com.selsela.example.injection.PerService;
import com.selsela.example.injection.module.ServiceModule;
import com.selsela.example.util.notification.MyFirebaseMessagingService;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);

    void inject(MyFirebaseMessagingService myFirebaseMessagingService);

}