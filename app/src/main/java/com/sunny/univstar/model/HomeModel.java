package com.sunny.univstar.model;

import com.sunny.univstar.model.service.IHomeWork;
import com.sunny.univstar.model.utils.RetrofitUtils;

/**
 * Created by DELL on 2018/5/3.
 */

public class HomeModel implements IHomeModel{

    @Override
    public IHomeWork getWorkData() {
        return RetrofitUtils.getInstance().gethomewokData();
    }
}
