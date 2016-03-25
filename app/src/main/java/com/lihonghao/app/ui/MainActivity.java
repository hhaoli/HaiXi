package com.lihonghao.app.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.lihonghao.app.R;
import com.lihonghao.app.ui.base.BaseActivity;
import com.lihonghao.app.ui.fragment.CarFragment;
import com.lihonghao.app.ui.fragment.ContrastFragment;
import com.lihonghao.app.ui.fragment.HomeFragment;
import com.lihonghao.app.ui.fragment.MyFragment;
import com.lihonghao.app.ui.fragment.NewsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.main_tab)
    RadioGroup mMainTab;
    private HomeFragment mHomeFragment;
    private CarFragment mCarFragment;
    private ContrastFragment mContrastFragment;
    private NewsFragment mNewsFragment;
    private MyFragment mMyFragment;
    private FragmentManager mFragmentManager;
    private int index = 0;
    private static final String INDEX = "index";
    private long firstTime = 0;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activityComponent().inject(this);
        mFragmentManager = getSupportFragmentManager();
        mMainTab.setOnCheckedChangeListener(this);
        onTabSelected(0);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int position = intent.getIntExtra(INDEX, 0);
        onTabSelected(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_home:
                onTabSelected(0);
                break;
            case R.id.main_car:
                onTabSelected(1);
                break;
            case R.id.main_contrast:
                onTabSelected(2);
                break;
            case R.id.main_news:
                onTabSelected(3);
                break;
            case R.id.main_my:
                onTabSelected(4);
                break;
        }
    }

    private void onTabSelected(int position) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (null == mHomeFragment) {
                    mHomeFragment = HomeFragment.newInstance();
                    transaction.add(R.id.main_container, mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
            case 1:
                if (null == mCarFragment) {
                    mCarFragment = CarFragment.newInstance();
                    transaction.add(R.id.main_container, mCarFragment);
                } else {
                    transaction.show(mCarFragment);
                }
                break;
            case 2:
                if (null == mContrastFragment) {
                    mContrastFragment = ContrastFragment.newInstance();
                    transaction.add(R.id.main_container, mContrastFragment);
                } else {
                    transaction.show(mContrastFragment);
                }
                break;
            case 3:
                if (null == mNewsFragment) {
                    mNewsFragment = NewsFragment.newInstance();
                    transaction.add(R.id.main_container, mNewsFragment);
                } else {
                    transaction.show(mNewsFragment);
                }
                break;
            case 4:
                if (null == mMyFragment) {
                    mMyFragment = MyFragment.newInstance();
                    transaction.add(R.id.main_container, mMyFragment);
                } else {
                    transaction.show(mMyFragment);
                }
                break;
        }
        index = position;
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) transaction.hide(mHomeFragment);
        if (mCarFragment != null) transaction.hide(mCarFragment);
        if (mContrastFragment != null) transaction.hide(mContrastFragment);
        if (mNewsFragment != null) transaction.hide(mNewsFragment);
        if (mMyFragment != null) transaction.hide(mMyFragment);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INDEX, index);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        index = savedInstanceState.getInt(INDEX);
    }

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 1000L) {
            Snackbar snackbar = Snackbar.make(mMainTab, "再按一次退出客户端", Snackbar.LENGTH_SHORT);
            snackbar.setAction("知道了", v -> {
                snackbar.dismiss();
            });
            snackbar.show();
            firstTime = secondTime;//更新firstTime
        } else {
            finish();
        }
    }
}
