package com.sunny.univstar.view.message;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.abovemessage.AboveMessageContract;
import com.sunny.univstar.presenter.abovemessage.AboveMessagePresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class AboveMessageActivity extends BaseActivity implements AboveMessageContract.AboveMessageInView {
    @Bind(R.id.aboveMessage_back)
    ImageView aboveMessageBack;
    @Bind(R.id.aboveMessage_recyView)
    RecyclerView aboveMessageRecyView;
    public SharedPreferences sharedPreferences;
    public AboveMessageContract.AboveMessageInPresenter aboveMessageInPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_above_message;
    }

    @Override
    protected void init() {
        aboveMessageInPresenter = new AboveMessagePresenter(this);
        sharedPreferences = getSharedPreferences("userState", 0);
        int loginUserId = sharedPreferences.getInt("loginUserId", 0);
        aboveMessageInPresenter.sendAboveMessageData(loginUserId);

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.aboveMessage_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showAboveMessageData(AboveMessageBean aboveMessageBean) {
        final List<AboveMessageBean.DataBean> data = aboveMessageBean.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        aboveMessageRecyView.setLayoutManager(linearLayoutManager);
        AboveMessageAdapter aboveMessageAdapter = new AboveMessageAdapter(data, this);
        aboveMessageRecyView.setAdapter(aboveMessageAdapter);
        aboveMessageAdapter.OnShortListener(new AboveMessageAdapter.OnShortListener() {
            @Override
            public void setOnShortListener(View view, int position) {
                String type = data.get(position).getType();
                if ("MSG_ORDER".equals(type)) {
                    startActivity(new Intent(AboveMessageActivity.this, OrderActivity.class));
                }
                if ("MSG_PRAISE".equals(type)) {
                    startActivity(new Intent(AboveMessageActivity.this, MessagePariseActivity.class));
                }
                if ("MSG_COMMENTS".equals(type)) {
                    startActivity(new Intent(AboveMessageActivity.this, MessageCommentActivity.class));
                }
                if ("MSG_HOMEWOK".equals(type)) {
                    startActivity(new Intent(AboveMessageActivity.this, MessageHomeworkActivity.class));
                }
                if ("MSG_UNIVSTAR".equals(type)) {
                    startActivity(new Intent(AboveMessageActivity.this, MessageUnivstarActivity.class));
                }
                if ("MSG_ATTENTION".equals(type)) {
                    startActivity(new Intent(AboveMessageActivity.this, MessageAttentionActivity.class));
                }
            }
        });
    }
}
