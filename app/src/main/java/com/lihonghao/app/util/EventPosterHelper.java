package com.lihonghao.app.util;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

import javax.inject.Inject;

public class EventPosterHelper {
    private final Bus mBus;

    @Inject
    public EventPosterHelper(Bus bus) {
        mBus = bus;
    }

    /**
     * Helper method to post an event from a different thread to the main one.
     */
    public void postEvent(final Object event) {
        new Handler(Looper.getMainLooper()).post(() -> mBus.post(event));
    }
}
