package com.lihonghao.app.data.remote;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.lihonghao.app.MyApplication;
import com.lihonghao.app.data.BusEvent;

import com.squareup.otto.Bus;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

public class UnauthorisedInterceptor implements Interceptor {
    @Inject
    Bus mBus;

    public UnauthorisedInterceptor(Context context) {
        MyApplication.get(context).getComponent().inject(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (response.code() == 401) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    mBus.post(new BusEvent.Error());
                }
            });
        }
        return response;
    }
}
