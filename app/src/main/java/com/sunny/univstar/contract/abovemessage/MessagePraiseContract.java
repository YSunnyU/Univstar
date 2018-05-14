package com.sunny.univstar.contract.abovemessage;

import com.sunny.univstar.view.message.bean.MessagePraiseBean;

/**
 * Created by 张玗 on 2018/5/12.
 */

public interface MessagePraiseContract {
    interface MessagePraiseInView{
        void showMessagePraiseData(MessagePraiseBean messagePraiseBean);
    }
    interface MessagePraiseInPresenter{
        void sendMessagePraiseData(int userId);
    }
}
