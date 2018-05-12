package com.sunny.univstar.presenter.mycoollect;

import com.sunny.univstar.contract.mycollect.CollectExperienceContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.CollectExperienceBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/11.
 */

public class CollectExperiencePresenter implements CollectExperienceContract.ExperienceInPresenter {
    public CollectExperienceContract.ExperienceInView experienceInView;

    public CollectExperiencePresenter(CollectExperienceContract.ExperienceInView experienceInView) {
        this.experienceInView = experienceInView;
    }

    @Override
    public void sendExperience(int userId, int type) {
        RetrofitUtils.getInstance()
                .getCollectExperienceService()
                .getExperience(userId,type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<CollectExperienceBean>() {
                    @Override
                    public void accept(CollectExperienceBean collectExperienceBean) throws Exception {
                        experienceInView.showExperienceData(collectExperienceBean);
                    }
                });
    }
}
