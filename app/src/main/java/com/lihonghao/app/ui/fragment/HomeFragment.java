package com.lihonghao.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.lihonghao.app.R;
import com.lihonghao.app.data.entity.AdsEntity;
import com.lihonghao.app.data.entity.AtsEntity;
import com.lihonghao.app.ui.adapter.HomeAdapter;
import com.lihonghao.app.ui.base.BaseActivity;
import com.lihonghao.app.ui.base.BaseFragment;
import com.lihonghao.app.ui.presenter.HomePresenter;
import com.lihonghao.app.ui.view.HomeView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements HomeView, BaseSliderView.OnSliderClickListener {

    @Inject
    HomePresenter mPresenter;
    @Bind(R.id.home_recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.home_slider)
    SliderLayout mSlider;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        mPresenter.attachView(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.loadAdv();
        mPresenter.loadList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showAdv(ArrayList<AdsEntity.DataEntity> data) {
        Log.e("tag", "size=" + data.size());
        for (AdsEntity.DataEntity entity : data) {
            DefaultSliderView view = new DefaultSliderView(mActivity);
            view.setScaleType(BaseSliderView.ScaleType.Fit).image(entity.getImage()).setOnSliderClickListener(this);
            mSlider.addSlider(view);
        }
        mSlider.setDuration(4000L);
    }

    @Override
    public void showList(ArrayList<AtsEntity.DataEntity> data) {
        HomeAdapter adapter = new HomeAdapter(mActivity, data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(mActivity, "==" + slider.toString(), Toast.LENGTH_SHORT).show();
    }
}
