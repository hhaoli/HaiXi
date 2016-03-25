package com.lihonghao.app.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;

public class MultiSwipeRefreshLayout extends SwipeRefreshLayout {

    private View[] mSwipeChildren;

    public MultiSwipeRefreshLayout(Context context) {
        super(context);
    }

    public MultiSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSwipeChildren(final int... ids) {
        assert ids != null;
        mSwipeChildren = new View[ids.length];
        for (int i = 0; i < ids.length; i++) {
            mSwipeChildren[i] = findViewById(ids[i]);
        }
    }

    @Override
    public boolean canChildScrollUp() {
        if (mSwipeChildren != null && mSwipeChildren.length > 0) {
            for (View view : mSwipeChildren) {
                if (view != null && view.isShown() && !canViewScrollUp(view)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canViewScrollUp(View view) {
        if (android.os.Build.VERSION.SDK_INT >= 14) {
            return ViewCompat.canScrollVertically(view, -1);
        } else {
            if (view instanceof AbsListView) {
                final AbsListView listView = (AbsListView) view;
                return listView.getChildCount() > 0 &&
                        (listView.getFirstVisiblePosition() > 0
                                || listView.getChildAt(0).getTop()
                                < listView.getPaddingTop());
            } else {
                return view.getScrollY() > 0;
            }
        }
    }
}
