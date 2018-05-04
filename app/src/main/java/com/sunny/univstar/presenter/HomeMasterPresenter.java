package com.sunny.univstar.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.sunny.univstar.contract.HomeMasterContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.master.fragment.HomeMasterBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/3.
 */

public class HomeMasterPresenter implements HomeMasterContract.homeMasterInPresenter {
    public HomeMasterContract.homeMasterInView homeMasterInView;

    public HomeMasterPresenter(HomeMasterContract.homeMasterInView homeMasterInView) {
        this.homeMasterInView = homeMasterInView;
    }

    @Override
    public void sendHomeMaster(Context context) {
//            获取cookie值
        RetrofitUtils.getInstance()
                .getHomeMasterService()
                .getHomeMasterData(getUserId(context))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeMasterBean>() {
                    @Override
                    public void accept(HomeMasterBean homeMasterBean) throws Exception {
                        homeMasterInView.showHomeMasterData(homeMasterBean);
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

        Integer idInteger= Integer.parseInt(id);

        //TODU测试id
        return idInteger;

    }
}
