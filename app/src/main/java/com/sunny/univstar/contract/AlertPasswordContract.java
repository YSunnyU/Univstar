package com.sunny.univstar.contract;

import com.sunny.univstar.view.personal.activity.login.bean.AlertPswBean;

/**
 * Created by 张玗 on 2018/5/8.
 */

public interface AlertPasswordContract  {
    interface AlertPasswordInView{
        void getValidateCodeDataData(AlertPswBean alertPswBean);
//        void getPasswordData(AlertPswBean alertPswBean);
        void getIsValidateCodeDataTrueData(AlertPswBean alertPswBean);
    }
    interface AlertPasswordInPresenter{
//        void sendPasswordData(int loginUserId,String newPassword);
        void sendValidateCodeData(String mobile);
        void sendIsValidateCodeDataTrueData(String mobile,String authCode);
    }
}
