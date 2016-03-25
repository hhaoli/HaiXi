package com.lihonghao.app.mvp;

public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
