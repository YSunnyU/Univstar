package com.sunny.univstar.contract;

import com.sunny.univstar.view.personal.activity.login.bean.successview.recharge.RechargeBean;

/**
 * Created by 张玗 on 2018/5/9.
 */

public interface MyRechargeContract  {
    interface RechargeDataInView{
        void showRechargeData(RechargeBean rechargeBean);

    }
    interface RechargeDataInPresenter{
        void sendRechargeData(int userId);
    }
}
