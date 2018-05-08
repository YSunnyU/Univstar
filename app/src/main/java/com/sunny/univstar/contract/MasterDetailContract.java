package com.sunny.univstar.contract;

import com.sunny.univstar.model.entity.MasterDetailEntity;

import java.util.Map;

/**
 * Created by DELL on 2018/5/7.
 */

public interface MasterDetailContract {
    interface MasterDetailView{
        void getMasterDetailData(MasterDetailEntity masterDetailEntity);
    }
    interface MasterDetailPresenter{
        void sendMasterDetail(Map<String,String> map);
    }
}
