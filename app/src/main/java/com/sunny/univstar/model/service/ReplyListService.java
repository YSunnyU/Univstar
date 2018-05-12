package com.sunny.univstar.model.service;

import com.sunny.univstar.model.entity.ReplyListEntity;
import com.sunny.univstar.model.entity.ReplyListSendEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by DELL on 2018/5/11.
 */

public interface ReplyListService {
    @FormUrlEncoded
    @POST("/v1/m/homewok/reply")
    Observable<ReplyListEntity> getReplyListData(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/v1/m/homewok/comments/save")
    Observable<ReplyListSendEntity> getReplyListSendData(@FieldMap Map<String,String> map);
}
