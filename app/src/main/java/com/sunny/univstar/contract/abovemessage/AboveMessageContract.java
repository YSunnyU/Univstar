package com.sunny.univstar.contract.abovemessage;

import com.sunny.univstar.view.message.AboveMessageBean;

/**
 * Created by 张玗 on 2018/5/12.
 */

public interface AboveMessageContract {
    interface AboveMessageInView{
        void showAboveMessageData(AboveMessageBean aboveMessageBean);
    }
    interface AboveMessageInPresenter{
        void sendAboveMessageData(int userId);
    }
}
