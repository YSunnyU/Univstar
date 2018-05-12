package com.sunny.univstar.contract;

import com.sunny.univstar.model.entity.ReplyListEntity;
import com.sunny.univstar.model.entity.ReplyListSendEntity;

import java.util.Map;

/**
 * Created by DELL on 2018/5/11.
 */

public interface ReplyListContract {
    interface ReplyListView{
        void getReplyListData(ReplyListEntity replyListEntity);
        void getReplyListSendData(ReplyListSendEntity replyListSendEntity);
    }
    interface ReplyListPresenter{
        void sendReplyList(Map<String,String> map);
        void sendReplyListSend(Map<String,String> map);
    }
}
