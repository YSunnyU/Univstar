package com.sunny.univstar.model.service;

import com.sunny.univstar.model.entity.LiveCourseDetailedEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by DELL on 2018/5/8.
 */

public interface LiveCourseDetailedService {
    @FormUrlEncoded
    @POST("/v1/m/liveCourse/detail")
    Observable<LiveCourseDetailedEntity> getLiveCourseDetailed(@FieldMap Map<String,String> map);
}
