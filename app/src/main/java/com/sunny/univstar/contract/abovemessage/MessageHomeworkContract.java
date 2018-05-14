package com.sunny.univstar.contract.abovemessage;

import com.sunny.univstar.view.message.bean.MessageHomeworkBean;

/**
 * Created by 张玗 on 2018/5/13.
 */

public interface MessageHomeworkContract {
    interface MessageHomeworkInView{
        void showMessageHomeworkData(MessageHomeworkBean messageHomeworkBean);
    }
    interface MessageHomeworkInPresenter{
        void sendMessageHomeworkData(int userId);
    }
}
