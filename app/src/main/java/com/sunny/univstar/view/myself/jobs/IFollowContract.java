package com.sunny.univstar.view.myself.jobs;


import java.util.List;

/**
 * Created by Lenovo on 2018/5/9.
 */

public interface IFollowContract {

    interface IFollowView {
        void showFollowBean(List<FollowBean.DataBean.ListBean> listBeans);

    }

    interface IFollowPresenter {
        void loadFollowBean(int studentId);
    }

}
