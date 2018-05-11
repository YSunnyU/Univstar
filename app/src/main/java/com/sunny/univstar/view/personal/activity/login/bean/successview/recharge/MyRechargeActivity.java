package com.sunny.univstar.view.personal.activity.login.bean.successview.recharge;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.MyRechargeContract;
import com.sunny.univstar.presenter.MyRechargePresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MyRechargeActivity extends BaseActivity implements MyRechargeContract.RechargeDataInView {
    public MyRechargeContract.RechargeDataInPresenter rechargeDataInPresenter;
    @Bind(R.id.myRecharge_back)
    ImageView myRechargeBack;
    @Bind(R.id.myRecharge_phoneNum)
    TextView myRechargePhoneNum;
    @Bind(R.id.myRecharge_residualNum)
    TextView myRechargeResidualNum;
    @Bind(R.id.myRecharge_moneyRecyView)
    RecyclerView myRechargeMoneyRecyView;
    public static SharedPreferences sharedPreferences;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_recharge;
    }

    @Override
    protected void init() {
        rechargeDataInPresenter = new MyRechargePresenter(this);
        sharedPreferences = getSharedPreferences("userState", 0);
        int loginUserId = sharedPreferences.getInt("loginUserId", 0);
        Log.d("MyRechargeActivity", "loginUserId:" + loginUserId);
        rechargeDataInPresenter.sendRechargeData(885);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showRechargeData(RechargeBean rechargeBean) {
        RechargeBean.DataBean data = rechargeBean.getData();

        String mobile = data.getMobile();
        myRechargePhoneNum.setText(mobile);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myRechargeMoneyRecyView.setLayoutManager(linearLayoutManager);
        List<RechargeBean.DataBean.ListBean> list = data.getList();
        Log.d("MyRechargeActivity", "list.size():" + list.size());
        MyRechargeAdapter myRechargeAdapter = new MyRechargeAdapter(list);
        myRechargeMoneyRecyView.setAdapter(myRechargeAdapter);
    }



    @OnClick(R.id.myRecharge_back)
    public void onViewClicked() {
        finish();
    }
}
