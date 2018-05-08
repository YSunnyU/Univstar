package com.sunny.univstar.model.service;

import com.sunny.univstar.model.entity.LiveCourseEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by DELL on 2018/5/7.
 */

public interface LiveCourseService {
    @FormUrlEncoded
    @POST("/v1/m/liveCourse/list")
    Observable<LiveCourseEntity> getLiveCourseData(@FieldMap Map<String,String> map);
}
