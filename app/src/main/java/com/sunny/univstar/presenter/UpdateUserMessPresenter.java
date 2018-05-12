package com.sunny.univstar.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.sunny.univstar.contract.UpdateUserMessContract;
import com.sunny.univstar.model.entity.UpLoadImgModel;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.UpdateUserBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

import static android.content.ContentValues.TAG;

/**
 * Created by 张玗 on 2018/5/10.
 */

public class UpdateUserMessPresenter implements UpdateUserMessContract.UpdateUserDataInPresenter {
    public UpdateUserMessContract.UpdateUserDataInView updateUserDataInView;
    private static Integer idInteger;

    public UpdateUserMessPresenter(UpdateUserMessContract.UpdateUserDataInView updateUserDataInView){
        this.updateUserDataInView = updateUserDataInView;
    }
//    ,String realname,String photo,String images,String intro,String details
    @Override
    public void sendUPdateUserData(Map<String,String> map) {
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

        RetrofitUtils.getInstance()
                .getUpdateUserMessService()
//                ,realname,photo,images,intro,details
                .postUserInfo(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdateUserBean>() {
                    @Override
                    public void accept(UpdateUserBean updateUserBean) throws Exception {
                        updateUserDataInView.showUpdateUserData(updateUserBean);
                    }
                });
    }

    @Override
    public void sendUpDataUserImg(List<MultipartBody.Part> list) {
        RetrofitUtils.getInstance()
                .getUpdateUserMessService()
                .upUserImg(list)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpLoadImgModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UpLoadImgModel upLoadImgModel) {
                        updateUserDataInView.showUpDataUserImg(upLoadImgModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: "+ e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static Integer getUserId(Context context){

        if(context==null){
            return 0 ;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("xyxy_cookiesp", Context.MODE_PRIVATE);

        String id = sharedPreferences.getString("xyxy_user_id", "");
        if(TextUtils.isEmpty(id)){
            return 0;
        }

        idInteger = Integer.parseInt(id);

        //TODU测试id
        return idInteger;

    }
}
