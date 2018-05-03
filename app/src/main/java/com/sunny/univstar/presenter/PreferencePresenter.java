package com.sunny.univstar.presenter;

import com.sunny.univstar.contract.PreferenceContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.preference.PreferenceAboveBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/3.
 */

public class PreferencePresenter implements PreferenceContract.PreferenceInPresenter {
    public PreferenceContract.PreferenceInView preferenceInView;

    public PreferencePresenter(PreferenceContract.PreferenceInView preferenceInView) {
        this.preferenceInView = preferenceInView;
    }

    @Override
    public void sendPreferenceData() {
        RetrofitUtils.getInstance()
                .getPreferenceService()
                .getPreferenceMess("v1/m/user/preference")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PreferenceAboveBean>() {
                    @Override
                    public void accept(PreferenceAboveBean preferenceAboveBean) throws Exception {
                        preferenceInView.showPreferenceData(preferenceAboveBean);
                    }
                });

    }
}
