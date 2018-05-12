package com.sunny.univstar.presenter.abovemessage;

import com.sunny.univstar.contract.abovemessage.AboveMessageContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.message.AboveMessageBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/12.
 */

public class AboveMessagePresenter implements AboveMessageContract.AboveMessageInPresenter {
    public AboveMessageContract.AboveMessageInView aboveMessageInView;

    public AboveMessagePresenter(AboveMessageContract.AboveMessageInView aboveMessageInView) {
        this.aboveMessageInView = aboveMessageInView;
    }

    @Override
    public void sendAboveMessageData(int userId) {
        RetrofitUtils.getInstance()
                .getAboveMessageService()
                .getAboveMessage(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<AboveMessageBean>() {
                    @Override
                    public void accept(AboveMessageBean aboveMessageBean) throws Exception {
                        aboveMessageInView.showAboveMessageData(aboveMessageBean);
                    }
                });
    }
}
