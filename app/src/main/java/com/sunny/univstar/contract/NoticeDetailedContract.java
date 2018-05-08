package com.sunny.univstar.contract;

import com.sunny.univstar.model.entity.NoticeDetailedEntity;

import java.util.Map;

/**
 * Created by DELL on 2018/5/8.
 */

public interface NoticeDetailedContract {
    interface NoticeDetailedView{
        void getNoticeDetailedData(NoticeDetailedEntity noticeDetailedEntity);
    }
    interface NoticeDetailedPresenter{
        void sendNoticeDetailed(Map<String,String> map);
    }
}
