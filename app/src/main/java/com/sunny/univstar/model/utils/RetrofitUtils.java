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
import com.sunny.univstar.model.service.IAppToken;
import com.sunny.univstar.model.service.IHomeWork;
import com.sunny.univstar.model.service.PreferenceService;

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
                .connectTimeout(15, TimeUnit.SECONDS)//链接超时
                .readTimeout(20,TimeUnit.SECONDS)//读取
                .writeTimeout(20,TimeUnit.SECONDS)//写
                .retryOnConnectionFailure(false)//目前关闭重复请求
                .addInterceptor(new ReceivedCookiesInterceptor(context))
                .addInterceptor(new AddCookiesInterceptor(context))
                .build();

        if(TextUtils.isEmpty(getAppToken(context))){
            loadApptoken(context);
        }

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://www.univstar.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(context, mPermissionList, 123);
        }


    }
    public static String getAppToken(Context context){

        if(context==null){
            return "" ;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("77777", Context.MODE_PRIVATE);

        String apptoken = sharedPreferences.getString("xyxy_apptoken", "");
        if(TextUtils.isEmpty(apptoken)){
            return "";
        }

        //TODU测试id
        return apptoken;

    }
    public void loadApptoken(final Context context){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .addInterceptor(new ReceivedCookiesInterceptor(context))
                .addInterceptor(new AddCookiesInterceptor(context))
                .build();

        Retrofit retrofit= new Retrofit.Builder()
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

                        if(value==null||value.getData()==null){
                            return;
                        }

                        String apptoken = value.getData().getApptoken();

                        long time = System.currentTimeMillis();

                        try {
                            String desApptoken= EncryptUtil.decrypt(apptoken);

                            String headerApptoken=EncryptUtil.encrypt(time + desApptoken).replaceAll("\\n","").toUpperCase();

                            saveAppToken(context,headerApptoken,time);

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
    private void saveAppToken(Context context,String token ,long time){
        if(context==null){
            return ;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("77777", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("xyxy_apptoken",token+"."+time);
        editor.commit();

    }
    public static RetrofitUtils getInstance(){
        if (retrofitUtils==null){
            retrofitUtils=new RetrofitUtils();
        }
        return retrofitUtils;
    }
    public PreferenceService getPreferenceService(){
        return retrofit.create(PreferenceService.class);
    }
    public IHomeWork gethomewokData(){
        return retrofit.create(IHomeWork.class);
    }

}
