package com.sunny.univstar.contract;

import com.sunny.univstar.model.entity.LiveCourseDetailedEntity;

import java.util.Map;

/**
 * Created by DELL on 2018/5/8.
 */

public interface LiveCourseDetailedContract {
    interface LiveCourseDetailedView{
        void getLiveCourseDetailedData(LiveCourseDetailedEntity liveCourseDetailedEntity);
    }
    interface LiveCourseDetailedPresenter{
        void sendLiveCourseDetailed(Map<String,String> map);
    }
}
