package com.sunny.univstar.contract;

import com.sunny.univstar.model.entity.SendValuableDetailedCommentsEntity;
import com.sunny.univstar.model.entity.ValuableDetailedCommentsEntity;
import com.sunny.univstar.model.entity.ValuableDetailedEntity;

import java.util.Map;

/**
 * Created by DELL on 2018/5/9.
 */

public interface ValuableDetailedContract {
    interface ValuableDetailedView{
        void getValuableDetailedData(ValuableDetailedEntity workDetailedEntity);
        void getValuableDetailedComments(ValuableDetailedCommentsEntity workDetailedCommentsEntity);
        void getSendValuableDetailedComments(SendValuableDetailedCommentsEntity sendWorkDetailedCommentsEntity);
    }
    interface ValuableDetailedPresenter{
        void sendValuableDetailed(String url,Map<String,String> map);
        void sendValuableDetailedComments(String url,Map<String,String> map);
        void sendValuableDetailedCommentsList(String url,Map<String,String> map);
    }
}
