package com.sunny.univstar.contract;

import com.sunny.univstar.view.personal.activity.login.bean.LoginBean;
import com.sunny.univstar.view.personal.activity.login.bean.LoginSucessBean;

/**
 * Created by 张玗 on 2018/5/7.
 */

public interface LoginContract {
    interface LoginContractInView {
        void showLoginData(LoginBean loginBean);
        void showLoginSuccessData(LoginSucessBean loginSucessBean);
    }

    interface LoginContractInPresenter {
        void sendLoginData(String phoneNum, String password);
        void sendUserLoginData(int loginUserId);
    }
}
