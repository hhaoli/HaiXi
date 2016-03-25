package com.lihonghao.app.mvp;

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        this.mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new AttachedException();
    }

    public static class AttachedException extends RuntimeException {
        public AttachedException() {
            super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
        }
    }
}
