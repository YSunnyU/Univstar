package com.sunny.univstar.view.message;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class AboveMessageActivity extends BaseActivity {
    @Bind(R.id.aboveMessage_back)
    ImageView aboveMessageBack;
    @Bind(R.id.aboveMessage_recyView)
    RecyclerView aboveMessageRecyView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_above_message;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }




    @OnClick(R.id.aboveMessage_back)
    public void onViewClicked() {
        finish();
    }
}
