package com.sunny.univstar.model.service;

import com.sunny.univstar.model.entity.LiveCourseEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by DELL on 2018/5/10.
 */

public interface TeacherLiveService {
    @FormUrlEncoded
    @POST("/v1/m/user/teacher/live")
    Observable<LiveCourseEntity> getTeacherLive(@FieldMap Map<String,String> map);
}
