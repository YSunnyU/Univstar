package com.sunny.univstar.model.service;

import com.sunny.univstar.model.entity.FindTeacherEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by DELL on 2018/5/7.
 */

public interface FindTeacherService {
    @FormUrlEncoded
    @POST("v1/m/user/teacher/list")
    Observable<FindTeacherEntity> getFindTeacherData(@FieldMap Map<String,String> map);

}
