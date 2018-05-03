package com.sunny.univstar.model.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {


    private Context context;

    public AddCookiesInterceptor(Context context) {
        super();
        this.context = context;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {


        final Request.Builder builder = chain.request().newBuilder();
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("77777", Context.MODE_PRIVATE);
        builder.addHeader("apptoken", sharedPreferences.getString("xyxy_apptoken", ""));
        builder.addHeader("user-agent", sharedPreferences.getString("xyxy_user_agent", ""));
//        builder.addHeader("Authorization",sharedPreferences.getString("xyxy_cookiename",""));
        builder.addHeader("did",sharedPreferences.getString("xyxy_solename",""));
        builder.addHeader("cid", sharedPreferences.getString("xyxy_cidname", ""));
        return chain.proceed(builder.build());
    }
}
