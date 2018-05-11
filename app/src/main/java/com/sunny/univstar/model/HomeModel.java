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
import com.sunny.univstar.model.service.TeacherFanService;
import com.sunny.univstar.model.service.TeacherLiveService;
import com.sunny.univstar.model.service.ValuableDetailedService;
import com.sunny.univstar.model.service.WorkDetailedService;
import com.sunny.univstar.model.utils.RetrofitUtils;

/**
 * Created by DELL on 2018/5/3.
 */

public class HomeModel implements IHomeModel{

    @Override
    public IHomeWork getWorkData() {
        return RetrofitUtils.getInstance().gethomewokData();
    }

    @Override
    public HomeNoticeService getHomeNotice() {
        return RetrofitUtils.getInstance().getHomeNoticeService();
    }

    @Override
    public PersonalRegisterService getPersonalRegister() {
        return RetrofitUtils.getInstance().getPersonalRegisterService();
    }

    @Override
    public FindTeacherService getFindTeacher() {
        return RetrofitUtils.getInstance().getFindTeacherService();
    }

    @Override
    public LiveCourseService getLiveCourse() {
        return RetrofitUtils.getInstance().getLiveCourseService();
    }

    @Override
    public MasterDetailService getMasterDetail() {
        return RetrofitUtils.getInstance().getMasterDatailService();
    }

    @Override
    public LiveCourseDetailedService getLiveCourseDetailed() {
        return RetrofitUtils.getInstance().getLiveCourseDetailedService();
    }

    @Override
    public NoticeDetailedService getNoticeDetailedService() {
        return RetrofitUtils.getInstance().getNoticeDetailedService();
    }

    @Override
    public MyFollowPraiseService getMyFollowPraiseService() {
        return RetrofitUtils.getInstance().getMyFollowPraiseService();
    }

    @Override
    public WorkDetailedService getWorkDetailedService() {
        return RetrofitUtils.getInstance().getWorkDetailedService();
    }

    @Override
    public ValuableDetailedService getValuableDetailedService() {
        return RetrofitUtils.getInstance().getValuableDetailedService();
    }

    @Override
    public TeacherFanService getTeacherFanService() {
        return RetrofitUtils.getInstance().getTeacherFan();
    }

    @Override
    public TeacherLiveService getTeacherLiveService() {
        return RetrofitUtils.getInstance().getTeacherLive();
    }
}
