package com.sunny.univstar.contract.mycollect;

import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.CollectExperienceBean;

/**
 * Created by 张玗 on 2018/5/11.
 */

public interface CollectExperienceContract {
    interface ExperienceInView{
        void showExperienceData(CollectExperienceBean collectExperienceBean);
    }
    interface ExperienceInPresenter{
        void sendExperience(int userId,int type);
    }
}
