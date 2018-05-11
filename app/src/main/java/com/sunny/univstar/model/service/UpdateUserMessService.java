package com.sunny.univstar.model.service;

import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.UpdateUserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 张玗 on 2018/5/10.
 */

public interface UpdateUserMessService {
    @FormUrlEncoded
    @POST("/v1/m/user/info/edit")
    Observable<UpdateUserBean> postUserInfo(@Field("id") Integer id,
                                            @Field("nickname") String nickname,
//                                            @Field("realname") String realname,
//                                            @Field("photo") String photo,
//                                            @Field("images") String images,
//                                            @Field("intro") String intro,
//                                            @Field("details") String details,
                                            @Field("sex") int sex,
                                            @Field("birthday") String birthday,
                                            @Field("address") String address);
}
