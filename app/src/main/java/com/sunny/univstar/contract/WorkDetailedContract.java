package com.sunny.univstar.contract;

import com.sunny.univstar.model.entity.SendWorkDetailedCommentsEntity;
import com.sunny.univstar.model.entity.WorkDetailedCommentsEntity;
import com.sunny.univstar.model.entity.WorkDetailedEntity;

import java.util.Map;

/**
 * Created by DELL on 2018/5/9.
 */

public interface WorkDetailedContract {
    interface WorkDetailedView{
        void getWorkDetailedData(WorkDetailedEntity workDetailedEntity);
        void getWorkDetailedComments(WorkDetailedCommentsEntity workDetailedCommentsEntity);
        void getSendWorkDetailedComments(SendWorkDetailedCommentsEntity sendWorkDetailedCommentsEntity);
    }
    interface WorkDetailedPresenter{
        void sendWorkDetailed(String url,Map<String,String> map);
        void sendWorkDetailedComments(String url,Map<String,String> map);
        void sendWorkDetailedCommentsList(String url,Map<String,String> map);
    }
}
