package com.sunny.univstar.contract;

import com.sunny.univstar.model.entity.FindTeacherEntity;

import java.util.Map;

/**
 * Created by DELL on 2018/5/7.
 */

public interface FindTeacherContract {
    interface FindTeacherView{
        void getFindTeacherData(FindTeacherEntity findTeacherEntity);
    }
    interface FindTeacherPresneter{
        void sendFindTeacher(Map<String,String> map);
    }
}
