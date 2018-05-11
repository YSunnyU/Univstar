package com.sunny.univstar.contract;

import com.sunny.univstar.model.entity.LiveCourseEntity;

import java.util.Map;

/**
 * Created by DELL on 2018/5/10.
 */

public interface TeacherLiveContract {
    interface TeacherLiveView{
        void getTeacherLiveData(LiveCourseEntity liveCourseEntity);
    }
    interface TeacherLivePresenter{
        void sendTeacherLive(Map<String,String> map);
    }
}
