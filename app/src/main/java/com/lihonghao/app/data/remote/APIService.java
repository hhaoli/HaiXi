package com.lihonghao.app.data.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lihonghao.app.BuildConfig;
import com.lihonghao.app.Config;
import com.lihonghao.app.data.entity.AdsEntity;
import com.lihonghao.app.data.entity.AtsEntity;
import com.lihonghao.app.data.entity.LoginEntity;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface APIService {
    /**************
     * POST Field *
     * GET  Query *
     *************/

    @FormUrlEncoded
    @POST(Config.API_ADVERT)
    Observable<AdsEntity> ads(@Field("position_id") String id);

    @FormUrlEncoded
    @POST(Config.API_ATS)
    Observable<AtsEntity> ats(@Field("page") String page, @Field("size") String size);

    @FormUrlEncoded
    @POST(Config.API_LOGIN)
    Observable<LoginEntity> login(@Field("username") String username, @Field("password") String password, @Field("client") String client);

    /**
     * Factory class that sets up a new api services
     */
    class Factory {
        public static APIService makeAPIService(Context context) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(15, TimeUnit.SECONDS);
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.writeTimeout(10, TimeUnit.SECONDS);
            builder.interceptors().add(new UnauthorisedInterceptor(context));

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
            builder.addInterceptor(logging);

            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(context.getCacheDir(), cacheSize);
            builder.cache(cache);

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd 'T' HH:mm:ss.SSSz")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(Config.ENDPOINT)
                    .build();
            return retrofit.create(APIService.class);
        }
    }
}
