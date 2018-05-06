package com.sunny.univstar.contract;

import com.sunny.univstar.model.entity.HomeNoticeEntity;

import java.util.Map;

/**
 * Created by DELL on 2018/5/4.
 */

public interface HomeNoticeContract {
    interface HomeNoticeView{
        void getHomeNoticeData(HomeNoticeEntity homeNoticeEntity);
    }
    interface HomeNoticePresenter{
        void sendHomeNotice(Map<String,String> map);
    }
}
