package com.sunny.univstar.presenter.abovemessage;

import com.sunny.univstar.contract.abovemessage.MessageHomeworkContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.message.bean.MessageHomeworkBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/13.
 */

public class MessageHomeworkPresenter implements MessageHomeworkContract.MessageHomeworkInPresenter {
    public MessageHomeworkContract.MessageHomeworkInView messageHomeworkInView;

    public MessageHomeworkPresenter(MessageHomeworkContract.MessageHomeworkInView messageHomeworkInView) {
        this.messageHomeworkInView = messageHomeworkInView;
    }

    @Override
    public void sendMessageHomeworkData(int userId) {
        RetrofitUtils.getInstance()
                .getMessageHomeworkService()
                .getMessageHomework(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MessageHomeworkBean>() {
                    @Override
                    public void accept(MessageHomeworkBean messageHomeworkBean) throws Exception {
                        messageHomeworkInView.showMessageHomeworkData(messageHomeworkBean);
                    }
                });
    }
}
