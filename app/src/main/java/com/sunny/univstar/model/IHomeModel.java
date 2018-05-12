package com.sunny.univstar.model;

import com.sunny.univstar.model.service.FindTeacherService;
import com.sunny.univstar.model.service.HomeNoticeService;
import com.sunny.univstar.model.service.IHomeWork;
import com.sunny.univstar.model.service.LiveCourseDetailedService;
import com.sunny.univstar.model.service.LiveCourseService;
import com.sunny.univstar.model.service.MasterDetailService;
import com.sunny.univstar.model.service.MyFollowPraiseService;
import com.sunny.univstar.model.service.NoticeDetailedService;
import com.sunny.univstar.model.service.PersonalRegisterService;
import com.sunny.univstar.model.service.ReplyListService;
import com.sunny.univstar.model.service.TeacherFanService;
import com.sunny.univstar.model.service.TeacherLiveService;
import com.sunny.univstar.model.service.ValuableDetailedService;
import com.sunny.univstar.model.service.WorkDetailedService;

/**
 * Created by DELL on 2018/5/3.
 */

public interface IHomeModel {
    IHomeWork getWorkData();
    HomeNoticeService getHomeNotice();
    PersonalRegisterService getPersonalRegister();
    FindTeacherService getFindTeacher();
    LiveCourseService getLiveCourse();
    MasterDetailService getMasterDetail();
    LiveCourseDetailedService getLiveCourseDetailed();
    NoticeDetailedService getNoticeDetailedService();
    MyFollowPraiseService getMyFollowPraiseService();
    WorkDetailedService getWorkDetailedService();
    ValuableDetailedService getValuableDetailedService();
    TeacherFanService getTeacherFanService();
    TeacherLiveService getTeacherLiveService();
    ReplyListService getReplyListService();
}
