package com.sunny.univstar.contract;

import android.content.Context;

import com.sunny.univstar.model.entity.HomeWokListModel;

/**
 * Created by DELL on 2018/5/3.
 */

public interface HomeWorkContract {
    interface HomeWorkView{
        void getWorkData(HomeWokListModel homeWokListModel);
    }
    interface HomeWorkPreference{
        void sendWorkData(Context context, int page, int sortord);
    }
}
