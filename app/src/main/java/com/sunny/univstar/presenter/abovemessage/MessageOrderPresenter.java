package com.sunny.univstar.presenter.abovemessage;

import com.sunny.univstar.contract.abovemessage.MessageOrderContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.message.bean.MessageOrderBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/12.
 */

public class MessageOrderPresenter implements MessageOrderContract.MessageOrderInPresenter {
    public MessageOrderContract.MessageOrderInView messageOrderInView;
    public MessageOrderPresenter(MessageOrderContract.MessageOrderInView messageOrderInView){
        this.messageOrderInView=messageOrderInView;
    }
    @Override
    public void sendMessageOrderData(int uesrId) {
        RetrofitUtils.getInstance()
                .getMessageOrderService()
                .getAboveMessage(uesrId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<MessageOrderBean>() {
                    @Override
                    public void accept(MessageOrderBean messageOrderBean) throws Exception {
                        messageOrderInView.showMessageOrderData(messageOrderBean);
                    }
                });
    }
}
