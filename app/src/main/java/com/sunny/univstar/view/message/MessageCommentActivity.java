package com.sunny.univstar.view.message;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.abovemessage.MessageCommentContract;
import com.sunny.univstar.presenter.abovemessage.MeesageCommentPresenter;
import com.sunny.univstar.view.message.adapter.MessagePariseAdapter;
import com.sunny.univstar.view.message.bean.MessageCommentBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MessageCommentActivity extends BaseActivity implements MessageCommentContract.MessageCommentInView{
    @Bind(R.id.messageComment_back)
    ImageView messageCommentBack;
    @Bind(R.id.messageComment_recyView)
    RecyclerView messageCommentRecyView;
    @Bind(R.id.messageComment_emptyContent)
    RelativeLayout messageCommentEmptyContent;
    public SharedPreferences sharedPreferences;

    public MessageCommentContract.MessageCommentInPresenter messageCommentInPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_comment;
    }

    @Override
    protected void init() {
        messageCommentInPresenter=new MeesageCommentPresenter(this);
        sharedPreferences = getSharedPreferences("userState", 0);
        int loginUserId = sharedPreferences.getInt("loginUserId", 0);
        messageCommentInPresenter.sendMessageCommentData(loginUserId);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.messageComment_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showMessageCommentData(MessageCommentBean messageCommentBean) {
        List<MessageCommentBean.DataBean.ListBean> list = messageCommentBean.getData().getList();

        if (list.size()==0){
            messageCommentEmptyContent.setVisibility(View.VISIBLE);
            messageCommentRecyView.setVisibility(View.GONE);
        }else {
            messageCommentEmptyContent.setVisibility(View.GONE);
            messageCommentRecyView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            messageCommentRecyView.setLayoutManager(linearLayoutManager);
            List<Object> lis=new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {

            lis.add(list.get(i));
            }
            MessagePariseAdapter messagePariseAdapter = new MessagePariseAdapter(lis, this);
            messageCommentRecyView.setAdapter(messagePariseAdapter);
        }
    }
}
