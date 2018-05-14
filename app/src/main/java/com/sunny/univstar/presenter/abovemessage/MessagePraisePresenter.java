package com.sunny.univstar.presenter.abovemessage;

import com.sunny.univstar.contract.abovemessage.MessagePraiseContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.message.bean.MessagePraiseBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/12.
 */

public class MessagePraisePresenter implements MessagePraiseContract.MessagePraiseInPresenter {
    public MessagePraiseContract.MessagePraiseInView messagePraiseInView;

    public MessagePraisePresenter(MessagePraiseContract.MessagePraiseInView messagePraiseInView) {
        this.messagePraiseInView = messagePraiseInView;
    }

    @Override
    public void sendMessagePraiseData(int userId) {
        RetrofitUtils.getInstance()
                .getMessagePraiseService()
                .getMessagePraise(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<MessagePraiseBean>() {
                    @Override
                    public void accept(MessagePraiseBean messagePraiseBean) throws Exception {
                        messagePraiseInView.showMessagePraiseData(messagePraiseBean);
                    }
                });
    }
}