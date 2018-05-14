package com.sunny.univstar.contract.abovemessage;

import com.sunny.univstar.view.message.bean.MessageCommentBean;

/**
 * Created by 张玗 on 2018/5/13.
 */

public interface MessageCommentContract {
    interface MessageCommentInView{
        void showMessageCommentData(MessageCommentBean messageCommentBean);
    }
    interface MessageCommentInPresenter{
        void sendMessageCommentData(int userId);
    }
}
