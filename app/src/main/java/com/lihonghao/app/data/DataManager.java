package com.lihonghao.app.data;

import com.lihonghao.app.data.entity.AdsEntity;
import com.lihonghao.app.data.entity.AtsEntity;
import com.lihonghao.app.util.EventPosterHelper;
import com.lihonghao.app.data.local.DatabaseHelper;
import com.lihonghao.app.data.local.PreferencesHelper;
import com.lihonghao.app.data.remote.APIService;
import com.lihonghao.app.data.entity.LoginEntity;


import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action1;


@Singleton
public class DataManager {

    private final APIService mAPIService;
    private final PreferencesHelper mPreferencesHelper;
    private final DatabaseHelper mDatabaseHelper;
    private final EventPosterHelper mEventPosterHelper;

    @Inject
    public DataManager(APIService APIService, PreferencesHelper preferencesHelper, DatabaseHelper databaseHelper, EventPosterHelper eventPosterHelper) {
        mAPIService = APIService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mEventPosterHelper = eventPosterHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public APIService getAPIService() {
        return mAPIService;
    }

    public Observable<AdsEntity> ads() {
        return mAPIService.ads("1");
    }

    public Observable<AtsEntity> ats(String page, String size) {
        return mAPIService.ats(page, size);
    }

    public Observable<LoginEntity> login(String username, String password) {
        return mAPIService.login(username, password, "android").doOnNext(new Action1<LoginEntity>() {
            @Override
            public void call(LoginEntity loginEntity) {

            }
        });
    }
}
