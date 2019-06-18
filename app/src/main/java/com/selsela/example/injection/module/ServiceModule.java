package com.selsela.example.injection.module;

import android.app.Service;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import com.selsela.example.injection.ActivityContext;

@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    public Service provideService() {
        return mService;
    }


    @Provides
    @ActivityContext
    Context providesContext() {
        return mService;
    }
}