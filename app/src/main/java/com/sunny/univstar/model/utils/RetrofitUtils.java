package com.sunny.univstar.model.utils;

import android.Manifest;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sunny.univstar.app.App;
import com.sunny.univstar.model.service.BaseUrl;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 张玗 on 2018/5/3.
 */

public class RetrofitUtils {
    public static RetrofitUtils retrofitUtils;
    public Retrofit retrofit;
    public RetrofitUtils() {
        retrofit = new Retrofit.Builder()
//                .client(client)
                .baseUrl(BaseUrl.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(App.context, mPermissionList, 123);
        }


    }
    public static RetrofitUtils getInstance(RetrofitUtils retrofitUtils){
        if (retrofitUtils==null){
            retrofitUtils=new RetrofitUtils();
        }
        return retrofitUtils;
    }


}
