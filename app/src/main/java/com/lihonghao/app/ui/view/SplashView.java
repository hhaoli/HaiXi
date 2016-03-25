package com.lihonghao.app.ui.view;

import com.lihonghao.app.mvp.MvpView;

public interface SplashView extends MvpView {

    boolean checkNet();

    void settingNet();

    boolean isFirst();

    void updateVersion();

    void toMain();

    void toGuide();
}
