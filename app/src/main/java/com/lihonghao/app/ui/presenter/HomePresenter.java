package com.lihonghao.app.ui.presenter;

import android.util.Log;

import com.lihonghao.app.data.DataManager;
import com.lihonghao.app.data.entity.AdsEntity;
import com.lihonghao.app.data.entity.AtsEntity;
import com.lihonghao.app.mvp.BasePresenter;
import com.lihonghao.app.ui.view.HomeView;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class HomePresenter extends BasePresenter<HomeView> {

    private final DataManager mDataManager;
    private final CompositeSubscription mSubscription;

    @Inject
    public HomePresenter(DataManager dataManager) {
        mDataManager = dataManager;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        super.detachView();
        mSubscription.unsubscribe();
    }

    public void loadAdv() {
        mSubscription.add(mDataManager.ads()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AdsEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AdsEntity adsEntity) {
                        if (adsEntity.getResult().getStatus() == 1) {
                            getMvpView().showAdv(adsEntity.getData());
                        }
                    }
                }));
    }

    public void loadList() {
        mSubscription.add(mDataManager.ats("1", "20")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AtsEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AtsEntity atsEntity) {
                        if (atsEntity.getResult().getStatus() == 1) {
                            getMvpView().showList(atsEntity.getData());
                        }
                    }
                }));
    }
}
