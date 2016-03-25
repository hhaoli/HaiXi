package com.lihonghao.app.injection.module;

import android.app.Application;
import android.content.Context;

import com.lihonghao.app.data.remote.APIService;
import com.lihonghao.app.injection.ApplicationContext;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies. Mainly singleton object that can be injected from
 * anywhere in the app.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context providesContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Bus providesEventBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    APIService providesAPIService() {
        return APIService.Factory.makeAPIService(mApplication);
    }

}
