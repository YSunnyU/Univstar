package com.sunny.univstar.model.service.mycollect;

import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.CollectPostsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 张玗 on 2018/5/12.
 */

public interface CollectPostsService {
    @FormUrlEncoded
    @POST("/v1/m/user/my/favorites")
    Observable<CollectPostsBean> getPosts(@Field("loginUserId") int UserId, @Field("type") int type);
}
