package com.lihonghao.app.ui.view;

import com.lihonghao.app.data.entity.AdsEntity;
import com.lihonghao.app.data.entity.AtsEntity;
import com.lihonghao.app.mvp.MvpView;

import java.util.ArrayList;
import java.util.List;

public interface HomeView extends MvpView {
    void showAdv(ArrayList<AdsEntity.DataEntity> data);

    void showList(ArrayList<AtsEntity.DataEntity> data);
}
