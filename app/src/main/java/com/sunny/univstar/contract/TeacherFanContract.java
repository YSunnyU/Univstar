package com.sunny.univstar.contract;

import com.sunny.univstar.model.entity.TeacherFanEntity;

import java.util.Map;

/**
 * Created by DELL on 2018/5/10.
 */

public interface TeacherFanContract {
    interface TeacherFanView{
        void getTeacherFanData(TeacherFanEntity teacherFanEntity);
    }
    interface TeacherFanPersenter{
        void sendTeacherFan(Map<String,String> map);
    }
}
