package com.sunny.univstar.contract.mycollect;

import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.CollectionLivingBean;

/**
 * Created by 张玗 on 2018/5/11.
 */

public interface CollectLivingContract {
    interface MyCollectionInView{
        void showMyCollectionData(CollectionLivingBean collectionLivingBean);
    }
    interface MyCollectionInPresenter{
        void sendMyCollectionData(int userId,int type);
    }
}
