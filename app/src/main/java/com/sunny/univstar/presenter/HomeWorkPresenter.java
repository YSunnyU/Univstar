package com.sunny.univstar.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.sunny.univstar.contract.HomeWorkContract;
import com.sunny.univstar.model.HomeModel;
import com.sunny.univstar.model.IHomeModel;
import com.sunny.univstar.model.entity.HomeWokListModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DELL on 2018/5/3.
 */

public class HomeWorkPresenter implements HomeWorkContract.HomeWorkPreference{
    private IHomeModel iHomeModel;
    private HomeWorkContract.HomeWorkView homeWorkView;

    public HomeWorkPresenter(HomeWorkContract.HomeWorkView homeWorkView) {
        this.homeWorkView = homeWorkView;
        iHomeModel = new HomeModel();
    }

    @Override
    public void sendWorkData(Context context, int page, int sortord) {
        iHomeModel.getWorkData().gethomewokData(getUserId(context),page,sortord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeWokListModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HomeWokListModel homeWokListModel) {
                        homeWorkView.getWorkData(homeWokListModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static Integer getUserId(Context context){

        if(context==null){
            return 0;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("CookieSp", Context.MODE_PRIVATE);

        String id = sharedPreferences.getString("xyxy_user_id", "");
        if(TextUtils.isEmpty(id)){
            return 0;
        }

        Integer idInteger= Integer.parseInt(id);
        Log.e("aaaaaaa",idInteger+"");
        //TODU测试id
        return idInteger;

    }
}
