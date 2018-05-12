package com.sunny.univstar.contract;

import com.sunny.univstar.model.entity.UpLoadImgModel;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.UpdateUserBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;

/**
 * Created by 张玗 on 2018/5/10.
 */

public interface UpdateUserMessContract {
    interface UpdateUserDataInView{
        void showUpdateUserData(UpdateUserBean updateUserBean);
        void showUpDataUserImg(UpLoadImgModel upLoadImgModel);
    }
    interface UpdateUserDataInPresenter{
        /*@Field("id") Integer id,
                                         @Field("nickname") String nickname,
                                            @Field("realname") String realname,
                                            @Field("photo") String photo,
                                            @Field("images") String images,
                                            @Field("intro") String intro,
                                            @Field("details") String details,
                                            @Field("sex") int sex,
                                            @Field("birthday") String birthday,
                                            @Field("address") String address*/
//        ,String realname,String photo,String images,String intro,String details
        void sendUPdateUserData(Map<String,String> map);
        void sendUpDataUserImg(List<MultipartBody.Part> list);
    }
}
