package com.sunny.univstar.model.utils;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sunny.univstar.model.entity.AppTokenModel;
import com.sunny.univstar.model.interceptor.AddCookiesInterceptor;
import com.sunny.univstar.model.interceptor.ReceivedCookiesInterceptor;
import com.sunny.univstar.model.service.HomeMasterService;
import com.sunny.univstar.model.service.IAppToken;
import com.sunny.univstar.model.service.IHomeWork;
import com.sunny.univstar.model.service.PreferenceService;
import com.sunny.univstar.model.service.HomeValuableService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sunny.univstar.app.App.context;

/**
 * Created by 张玗 on 2018/5/3.
 */

public class RetrofitUtils {
    public static RetrofitUtils retrofitUtils;
    public Retrofit retrofit;

    public RetrofitUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ReceivedCookiesInterceptor(context))
                .addInterceptor(new AddCookiesInterceptor(context))
                .build();

        if (TextUtils.isEmpty(getAppToken(context))) {
            loadApptoken(context);
        }

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://www.univstar.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
//        手机权限问题
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(context, mPermissionList, 123);
        }


    }

    public static String getAppToken(Context context) {

        if (context == null) {
            return "";
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("77777", Context.MODE_PRIVATE);

        String apptoken = sharedPreferences.getString("xyxy_apptoken", "");
        if (TextUtils.isEmpty(apptoken)) {
            return "";
        }

        //TODU测试id
        return apptoken;

    }

    public void loadApptoken(final Context context) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .addInterceptor(new ReceivedCookiesInterceptor(context))
                .addInterceptor(new AddCookiesInterceptor(context))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://www.univstar.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(IAppToken.class)
                .getAppToken("/v1/m/security/apptoken")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppTokenModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AppTokenModel value) {

                        if (value == null || value.getData() == null) {
                            return;
                        }

                        String apptoken = value.getData().getApptoken();

                        long time = System.currentTimeMillis();

                        try {
                            String desApptoken = EncryptUtil.decrypt(apptoken);//解码后的数据
                            //解码后的数据拼接上当前系统时间 再编码 去掉换行 把所有字母转成大写
                            String headerApptoken = EncryptUtil.encrypt(time + desApptoken).replaceAll("\\n", "").toUpperCase();

                            saveAppToken(context, headerApptoken, time);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void saveAppToken(Context context, String token, long time) {
        if (context == null) {
            return;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("77777", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //存储的时候 转成大写后的token + "."  + 加上当前系统时间 组成最中的token
        editor.putString("xyxy_apptoken", token + "." + time);
        editor.commit();

    }

    //    单例模式创建
    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            retrofitUtils = new RetrofitUtils();
        }
        return retrofitUtils;
    }

    //    设置偏好
    public PreferenceService getPreferenceService() {
        return retrofit.create(PreferenceService.class);
    }

    //    作业
    public IHomeWork gethomewokData() {
        return retrofit.create(IHomeWork.class);
    }

    //    名师
    public HomeMasterService getHomeMasterService() {
        return retrofit.create(HomeMasterService.class);
    }
//    宝典
    public HomeValuableService getHomeValuableService(){
        return retrofit.create(HomeValuableService.class);
    }

}
