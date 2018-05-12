package com.sunny.univstar.contract.mycollect;

import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.CollectEavesdropBean;

/**
 * Created by 张玗 on 2018/5/12.
 */

public interface CollectEavesdropContract {
    interface CollectEavesdropInView{
        void showCollectEavesdropData(CollectEavesdropBean collectEavesdropBean);
    }
    interface CollectEavesdropInPresenter{
        void sendCollectEavesdropData(int userId,int type);
    }
}
