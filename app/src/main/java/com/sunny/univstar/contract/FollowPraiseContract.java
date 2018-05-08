package com.sunny.univstar.contract;

import java.util.Map;

/**
 * Created by DELL on 2018/5/8.
 */

public interface FollowPraiseContract {
    interface FollowPraiseView{
        void getFollowPraise(String msg);
    }
    interface FollowPraisePresenter{
        void sendFollowPraise(String url, Map<String,String> map);
    }
}
