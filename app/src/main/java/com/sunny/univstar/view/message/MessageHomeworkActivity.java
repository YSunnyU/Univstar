package com.sunny.univstar.view.message;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.abovemessage.MessageHomeworkContract;
import com.sunny.univstar.view.message.bean.MessageHomeworkBean;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MessageHomeworkActivity extends BaseActivity implements MessageHomeworkContract.MessageHomeworkInView{
    @Bind(R.id.messageHomework_back)
    ImageView messageHomeworkBack;
    @Bind(R.id.messageHomework_recyView)
    RecyclerView messageHomeworkRecyView;
    @Bind(R.id.messageHomework_emptyContent)
    RelativeLayout messageHomeworkEmptyContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_homework;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }




    @OnClick(R.id.messageHomework_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showMessageHomeworkData(MessageHomeworkBean messageHomeworkBean) {
        List<?> list = messageHomeworkBean.getData().getList();
        if (list.size()==0){
            messageHomeworkRecyView.setVisibility(View.GONE);
            messageHomeworkEmptyContent.setVisibility(View.VISIBLE);
        }else {
            messageHomeworkRecyView.setVisibility(View.VISIBLE);
            messageHomeworkEmptyContent.setVisibility(View.GONE);
        }
    }
}
