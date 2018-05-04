package com.sunny.univstar.contract;

import android.content.Context;

import com.sunny.univstar.view.master.fragment.HomeMasterBean;

/**
 * Created by 张玗 on 2018/5/3.
 */

public interface HomeMasterContract {
    interface homeMasterInView{
        void showHomeMasterData(HomeMasterBean homeMasterBean);
    }
    interface homeMasterInPresenter{
        void sendHomeMaster(Context context);
    }
}
