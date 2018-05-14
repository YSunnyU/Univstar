package com.sunny.univstar.model.service;


import com.sunny.univstar.view.myself.jobs.GoodOnClickBean;
import com.sunny.univstar.view.myself.jobs.TreaSureBean;
import com.sunny.univstar.view.myself.jobs.TreaSureLunBoTuBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 陈伟霆 on 2018/5/7.
 */

public interface TreaSureService {
    @FormUrlEncoded
    @POST("/v1/m/artcircle/home")
    Observable<TreaSureBean> loadTreaSure(@FieldMap Map<String, Integer> map);
    @POST("/v1/m/artcircle/slideshow")
    Observable<TreaSureLunBoTuBean> loadTreaSureLunBoTu();


    //点赞
    @FormUrlEncoded
    @POST("/v1/m/user/praise")
    Observable<GoodOnClickBean> loadGoodBean(@FieldMap Map<String, String> map);
    //取消赞
    @FormUrlEncoded
    @POST("/v1/m/user/praise/cancel")
    Observable<GoodOnClickBean> CancelthePraise(@FieldMap Map<String, String> map);
    //收藏
    @FormUrlEncoded
    @POST("/v1/m/user/favorite")
    Observable<GoodOnClickBean> Collection(@FieldMap Map<String, String> map);
    //取消收藏
    @FormUrlEncoded
    @POST("/v1/m/user/favorite/cancel")
    Observable<GoodOnClickBean>   CancelTheCollection(@FieldMap Map<String, String> map);



}
