package com.lihonghao.app.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lihonghao.app.Config;
import com.lihonghao.app.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHelper {
    private final SharedPreferences mPreferences;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPreferences = context.getSharedPreferences(Config.PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void putString(String key, String value) {
        mPreferences.edit().putString(key, value).apply();
    }

    @Nullable
    public String getString(String key) {
        return mPreferences.getString(key, null);
    }

    public void putInt(String key, int value) {
        mPreferences.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return mPreferences.getInt(key, 0);
    }

    public void putBoolean(String key, boolean value) {
        mPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }

    public void putLong(String key, long value) {
        mPreferences.edit().putLong(key, value).apply();
    }

    public long getLong(String key) {
        return mPreferences.getLong(key, 0);
    }

    /**
     * 移除一项
     */
    public boolean remove(String key) {
        return mPreferences.edit().remove(key).commit();
    }

    /**
     * 清除文件内容
     */
    public void clear() {
        mPreferences.edit().clear().apply();
    }

    /**
     * 某一项是否存在
     */
    public boolean contatins(String key) {
        return mPreferences.contains(key);
    }

}
