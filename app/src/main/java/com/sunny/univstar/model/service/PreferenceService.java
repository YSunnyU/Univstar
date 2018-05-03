package com.sunny.univstar.model.service;

import com.sunny.univstar.view.preference.PreferenceAboveBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by 张玗 on 2018/5/3.
 */

public interface PreferenceService {
    //https://www.univstar.com/
    @GET()
    Observable<PreferenceAboveBean> getPreferenceMess(@Url String url);
}
