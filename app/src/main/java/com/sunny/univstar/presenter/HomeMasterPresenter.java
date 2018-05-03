package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.HomeMasterContract;

/**
 * Created by 张玗 on 2018/5/3.
 */

public class HomeMasterPresenter implements HomeMasterContract.homeMasterInPresenter{
    public HomeMasterContract.homeMasterInView homeMasterInView;
    public HomeMasterPresenter(HomeMasterContract.homeMasterInView homeMasterInView){
        this.homeMasterInView=homeMasterInView;
    }
    @Override
    public void sendHomeMaster() {
//            获取cookie值

//        RetrofitUtils.getInstance()
//                .getHomeMasterService()
//                .getHomeMasterData()
    }
}
