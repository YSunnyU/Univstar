package com.sunny.univstar.contract;

import com.sunny.univstar.model.entity.LiveCourseEntity;

import java.util.Map;

/**
 * Created by DELL on 2018/5/7.
 */

public interface LiveCourseContract {
    interface LiveCourseView{
        void getLiveCourseData(LiveCourseEntity liveCourseEntity);
    }
    interface LiveCoursePresenter{
        void sendLiveCourse(Map<String,String> map);
    }
}
