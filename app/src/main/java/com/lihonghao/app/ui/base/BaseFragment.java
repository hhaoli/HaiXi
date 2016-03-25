package com.lihonghao.app.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    protected BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (BaseActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
