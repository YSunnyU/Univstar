package com.sunny.univstar.model.service;

import com.sunny.univstar.model.entity.UpLoadImgModel;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.UpdateUserBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by 张玗 on 2018/5/10.
 */

public interface UpdateUserMessService {
    @FormUrlEncoded
    @POST("/v1/m/user/info/edit")
    Observable<UpdateUserBean> postUserInfo(@FieldMap Map<String,String> map);

    @Multipart
    @POST("/v1/m/qiniu/qiniuUpload")
    Observable<UpLoadImgModel> upUserImg(@Part List<MultipartBody.Part> file);
}
