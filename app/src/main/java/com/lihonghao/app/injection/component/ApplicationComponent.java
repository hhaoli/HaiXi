package com.lihonghao.app.injection.component;

import android.app.Application;
import android.content.Context;

import com.lihonghao.app.MyApplication;
import com.lihonghao.app.data.DataManager;
import com.lihonghao.app.data.local.PreferencesHelper;
import com.lihonghao.app.data.remote.APIService;
import com.lihonghao.app.data.remote.UnauthorisedInterceptor;
import com.lihonghao.app.injection.ApplicationContext;
import com.lihonghao.app.injection.module.ApplicationModule;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MyApplication myApplication);

    void inject(UnauthorisedInterceptor unauthorisedInterceptor);

    @ApplicationContext
    Context content();

    Application application();

    APIService apiService();

    PreferencesHelper preferencesHelper();

    DataManager dataManager();

    Bus eventBus();
}
