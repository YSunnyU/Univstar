package com.sunny.univstar.presenter.abovemessage;

import com.sunny.univstar.contract.abovemessage.MessageCommentContract;
import com.sunny.univstar.model.utils.RetrofitUtils;
import com.sunny.univstar.view.message.bean.MessageCommentBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/5/13.
 */

public class MeesageCommentPresenter implements MessageCommentContract.MessageCommentInPresenter{
    public MessageCommentContract.MessageCommentInView messageCommentInView;
    public MeesageCommentPresenter(MessageCommentContract.MessageCommentInView messageCommentInView){
       this.messageCommentInView =messageCommentInView;
    }
    @Override
    public void sendMessageCommentData(int userId) {
        RetrofitUtils.getInstance()
                .getMessageCommentService()
                .getMessageComment(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<MessageCommentBean>() {
                    @Override
                    public void accept(MessageCommentBean messageCommentBean) throws Exception {
                        messageCommentInView.showMessageCommentData(messageCommentBean);
                    }
                });
    }
}
