package com.sunny.univstar.contract;

import com.sunny.univstar.view.preference.PreferenceAboveBean;

/**
 * Created by 张玗 on 2018/5/3.
 */

public interface PreferenceContract {
    interface PreferenceInView{
        void showPreferenceData(PreferenceAboveBean preferenceAboveBean);
    }
    interface PreferenceInPresenter{
        void sendPreferenceData();
    }
}
