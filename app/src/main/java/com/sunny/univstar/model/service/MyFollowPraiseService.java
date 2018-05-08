package com.sunny.univstar.model.service;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by DELL on 2018/5/8.
 */

public interface MyFollowPraiseService {
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> getFollowPraiseService(@Url String url,@FieldMap Map<String,String> map);

}
