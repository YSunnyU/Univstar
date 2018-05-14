package com.sunny.univstar.contract.abovemessage;

import com.sunny.univstar.view.message.bean.MessageOrderBean;

/**
 * Created by 张玗 on 2018/5/12.
 */

public interface MessageOrderContract {
    interface MessageOrderInView{
        void showMessageOrderData(MessageOrderBean messageOrderBean);
    }
    interface MessageOrderInPresenter{
        void sendMessageOrderData(int uesrId);
    }
}
