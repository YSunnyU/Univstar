package com.sunny.univstar.contract;

import com.sunny.univstar.view.valuable.fragment.HomeValuableBean;
import com.sunny.univstar.view.valuable.fragment.ValueFlaybannerBean;

/**
 * Created by 张玗 on 2018/5/5.
 */

public interface HomeValuableContract {
    interface HomeValuableInView {
//        所有数据
        void showHomeValuableData(HomeValuableBean homeValuableBean);
        void showValueFlyBannerData(ValueFlaybannerBean valueFlaybannerBean);
    }

    interface HomeValuableInPresenter {
        void sendHomeValuableData(String page, String rows, String loginUserId, String sortord);
        void sendFlyBannerData();
    }
}
