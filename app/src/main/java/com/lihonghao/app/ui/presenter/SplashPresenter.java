package com.lihonghao.app.ui.presenter;

import android.os.Handler;

import com.lihonghao.app.data.DataManager;
import com.lihonghao.app.data.entity.LoginEntity;
import com.lihonghao.app.mvp.BasePresenter;
import com.lihonghao.app.ui.view.SplashView;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SplashPresenter extends BasePresenter<SplashView> {


    private final DataManager mDataManager;
    private final CompositeSubscription mSubscription;

    @Inject
    public SplashPresenter(DataManager dataManager) {
        mDataManager = dataManager;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        super.detachView();
        mSubscription.unsubscribe();
    }

    public void init() {
        if (!getMvpView().checkNet()) {//没有网络
            getMvpView().settingNet();
            return;
        }

//        if (checkVersion()) {//有新版本
//            getMvpView().updateVersion();
//            return;
//        }

        if (!getMvpView().isFirst()) {//第一次使用
            getMvpView().toGuide();
            return;
        }

        new Handler().postDelayed(() -> getMvpView().toMain(), 2000L);
    }

    private boolean checkVersion() {

        mSubscription.add(mDataManager.login("demo", "p@ssw0rd")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginEntity entity) {

                    }
                })
        );

        return false;
    }
}
