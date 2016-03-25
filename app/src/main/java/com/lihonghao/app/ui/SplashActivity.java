package com.lihonghao.app.ui;


import android.os.Bundle;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.lihonghao.app.Config;
import com.lihonghao.app.R;
import com.lihonghao.app.data.DataManager;
import com.lihonghao.app.ui.base.BaseActivity;
import com.lihonghao.app.ui.presenter.SplashPresenter;
import com.lihonghao.app.ui.view.SplashView;
import com.lihonghao.app.util.NetUtils;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    DataManager mDataManager;
    @Inject
    SplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.init();
    }


    @Override
    public boolean checkNet() {
        return NetUtils.isConnected(this);
    }

    @Override
    public void settingNet() {
        new AlertDialogWrapper.Builder(this).setTitle("提示").setMessage("是否开启网络?")
                .setPositiveButton("是", (dialog, which) -> {
                    NetUtils.openNetSetting(SplashActivity.this);
                    dialog.dismiss();
                    finish();
                })
                .setNegativeButton("否", (dialog, which) -> {
                    toMain();
                    dialog.dismiss();
                    finish();
                }).create().show();
    }

    @Override
    public boolean isFirst() {
        return mDataManager.getPreferencesHelper().getBoolean(Config.PREF_KEY_FIRST);
    }

    @Override
    public void updateVersion() {

    }

    @Override
    public void toMain() {
        MainActivity.launch(this);
        finish();
    }

    @Override
    public void toGuide() {
        mDataManager.getPreferencesHelper().putBoolean(Config.PREF_KEY_FIRST, true);
    }


    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
