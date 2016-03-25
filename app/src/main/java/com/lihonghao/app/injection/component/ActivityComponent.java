package com.lihonghao.app.injection.component;

import com.lihonghao.app.injection.PerActivity;
import com.lihonghao.app.injection.module.ActivityModule;
import com.lihonghao.app.ui.MainActivity;
import com.lihonghao.app.ui.SplashActivity;
import com.lihonghao.app.ui.base.BaseActivity;
import com.lihonghao.app.ui.base.BaseFragment;
import com.lihonghao.app.ui.fragment.HomeFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity splashActivity);

    void inject(MainActivity mainActivity);

    void inject(HomeFragment homeFragment);
}
