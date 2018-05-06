package com.sunny.univstar.model;

import com.sunny.univstar.model.service.HomeNoticeService;
import com.sunny.univstar.model.service.IHomeWork;
import com.sunny.univstar.model.service.PersonalRegisterService;

/**
 * Created by DELL on 2018/5/3.
 */

public interface IHomeModel {
    IHomeWork getWorkData();
    HomeNoticeService getHomeNotice();
    PersonalRegisterService getPersonalRegister();
}
