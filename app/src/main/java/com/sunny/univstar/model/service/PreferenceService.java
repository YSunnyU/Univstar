package com.sunny.univstar.model.service;

import com.sunny.univstar.view.preference.AboveBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 张玗 on 2018/5/3.
 */

public interface PreferenceService {
    //https://www.univstar.com/v1/m/user/preference
    @GET("preference")
    Observable<AboveBean> getPreferenceMess();
}
