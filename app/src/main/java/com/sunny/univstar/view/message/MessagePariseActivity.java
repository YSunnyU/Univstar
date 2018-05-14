package com.sunny.univstar.view.message;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.abovemessage.MessagePraiseContract;
import com.sunny.univstar.presenter.abovemessage.MessagePraisePresenter;
import com.sunny.univstar.view.message.adapter.MessagePariseAdapter;
import com.sunny.univstar.view.message.bean.MessagePraiseBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


public class MessagePariseActivity extends BaseActivity implements MessagePraiseContract.MessagePraiseInView {

    public MessagePraiseContract.MessagePraiseInPresenter messagePraiseInPresenter;
    public SharedPreferences sharedPreferences;
    @Bind(R.id.messagePraise_back)
    ImageView messagePraiseBack;
    @Bind(R.id.messagePraise_recyView)
    RecyclerView messagePraiseRecyView;
    @Bind(R.id.messagePraise_empty)
    ImageView messagePraiseEmpty;
    @Bind(R.id.messagePraise_emptyContent)
    RelativeLayout messagePraiseEmptyContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_parise;
    }

    @Override
    protected void init() {
        messagePraiseInPresenter = new MessagePraisePresenter(this);
        sharedPreferences = getSharedPreferences("userState", 0);
        int loginUserId = sharedPreferences.getInt("loginUserId", 0);
        messagePraiseInPresenter.sendMessagePraiseData(loginUserId);
    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.messagePraise_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showMessagePraiseData(MessagePraiseBean messagePraiseBean) {
        List<MessagePraiseBean.DataBean.ListBean> list = messagePraiseBean.getData().getList();
        if (list.size() == 0) {
            messagePraiseEmptyContent.setVisibility(View.VISIBLE);
            messagePraiseRecyView.setVisibility(View.GONE);
        } else {
            messagePraiseEmptyContent.setVisibility(View.GONE);
            messagePraiseRecyView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            messagePraiseRecyView.setLayoutManager(linearLayoutManager);
            List<Object> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {

                list1.add(list.get(i));
            }
            MessagePariseAdapter messagePariseAdapter = new MessagePariseAdapter(list1, this);
            messagePraiseRecyView.setAdapter(messagePariseAdapter);
        }
    }


}
