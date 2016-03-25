package com.lihonghao.app.mvp;

public interface MvpView {
    /**
     * 发生错误
     *
     * @param e e
     */
    void onFailure(Throwable e);
}
