package com.sunny.univstar.contract.mycollect;

import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.CollectPostsBean;

/**
 * Created by 张玗 on 2018/5/12.
 */

public interface CollectPostsContract {
    interface  CollectPostsInView{
        void showCollectPostsData(CollectPostsBean collectPostsBean);
    }
    interface CollectPostsInPresenter{
        void sendCollectPostsData(int userId,int type);
    }
}
