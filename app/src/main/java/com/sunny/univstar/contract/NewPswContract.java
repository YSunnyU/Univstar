package com.sunny.univstar.contract;

import com.sunny.univstar.view.personal.activity.login.bean.LoginSucessBean;

/**
 * Created by 张玗 on 2018/5/8.
 */

public interface NewPswContract {
    interface NewPasswordInView{
        void getPasswordData(LoginSucessBean loginSucessBean);
    }
    interface NewPasswordInPresenter{
       void sendPasswordData(String mobile,String newPassword);

    }
}
