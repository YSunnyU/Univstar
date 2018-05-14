package com.sunny.univstar.view.message;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.abovemessage.MessageOrderContract;
import com.sunny.univstar.presenter.abovemessage.MessageOrderPresenter;
import com.sunny.univstar.view.message.bean.MessageOrderBean;

import butterknife.Bind;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity implements MessageOrderContract.MessageOrderInView {

    public MessageOrderContract.MessageOrderInPresenter messageOrderInPresenter;
    public SharedPreferences sharedPreferences;
    @Bind(R.id.messageOrder_back)
    ImageView messageOrderBack;
    @Bind(R.id.messageOrder_recyView)
    RecyclerView messageOrderRecyView;
    @Bind(R.id.messageOrder_empty)
    ImageView messageOrderEmpty;
    @Bind(R.id.messageOrder_emptyContent)
    RelativeLayout messageOrderEmptyContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void init() {
        messageOrderInPresenter = new MessageOrderPresenter(this);
        sharedPreferences = getSharedPreferences("userState", 0);
        int loginUserId = sharedPreferences.getInt("loginUserId", 0);
        messageOrderInPresenter.sendMessageOrderData(loginUserId);
    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.messageOrder_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showMessageOrderData(MessageOrderBean messageOrderBean) {
        if (messageOrderBean.getData().getList().size() == 0) {
            messageOrderEmptyContent.setVisibility(View.VISIBLE);
            messageOrderRecyView.setVisibility(View.GONE);
        } else {
            messageOrderEmptyContent.setVisibility(View.GONE);
            messageOrderRecyView.setVisibility(View.VISIBLE);
        }
    }

}
