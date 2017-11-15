package com.coder.catclaws.commons;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dengshaomin on 2017/11/9.
 */

public class BasePreference {

    private Context context;

    private SharedPreferences sp;

    private SharedPreferences.Editor editor;

    private String FILE_NAME = "userinfo";

    protected BasePreference(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return sp.getString(key, null);
    }

    public void setBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public void setInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return sp.getInt(key, 0);
    }
}