package com.lihonghao.app;

import android.app.Application;
import android.content.Context;

import com.lihonghao.app.injection.component.ApplicationComponent;
import com.lihonghao.app.injection.component.DaggerApplicationComponent;
import com.lihonghao.app.injection.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.otto.Bus;

import javax.inject.Inject;

public class MyApplication extends Application {
    ApplicationComponent mApplicationComponent;
    @Inject
    Bus mBus;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
        mBus.register(this);
        LeakCanary.install(this);
    }

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
