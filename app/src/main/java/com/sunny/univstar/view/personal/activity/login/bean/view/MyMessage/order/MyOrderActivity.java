package com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.order;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.MyOrderContract;
import com.sunny.univstar.presenter.MyOrderPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MyOrderActivity extends BaseActivity implements MyOrderContract.MyOrderInView {
    @Bind(R.id.myOrder_back)
    ImageView myOrderBack;
    @Bind(R.id.myOrder_aboveText)
    TextView myOrderAboveText;
    @Bind(R.id.myOrder_aboveLine)
    TextView myOrderAboveLine;
    @Bind(R.id.myOrder_above)
    RelativeLayout myOrderAbove;
    @Bind(R.id.myOrder_payText)
    TextView myOrderPayText;
    @Bind(R.id.myOrder_payLine)
    TextView myOrderPayLine;
    @Bind(R.id.myOrder_pay)
    RelativeLayout myOrderPay;
    @Bind(R.id.myOrder_useText)
    TextView myOrderUseText;
    @Bind(R.id.myOrder_useLine)
    TextView myOrderUseLine;
    @Bind(R.id.myOrder_use)
    RelativeLayout myOrderUse;
    @Bind(R.id.myOrder_reverseText)
    TextView myOrderReverseText;
    @Bind(R.id.myOrder_reverseLine)
    TextView myOrderReverseLine;
    @Bind(R.id.myOrder_reverse)
    RelativeLayout myOrderReverse;

    public MyOrderContract.MyOrderInPresenter OrderInPresenter;
    @Bind(R.id.myOrder_recyView)
    RecyclerView myOrderRecyView;
    @Bind(R.id.myOrder_emptyContent)
    RelativeLayout myOrderEmptyContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void init() {
        OrderInPresenter = new MyOrderPresenter(this);
        Intent intent = getIntent();
        String order = intent.getStringExtra("order");
//        代付款
        if ("one".equals(order)) {
//            代付款
            myOrderPayText.setTextSize(16);
            myOrderPayLine.setVisibility(View.VISIBLE);
//          我的订单
            myOrderAboveText.setTextSize(14);
            myOrderAboveLine.setVisibility(View.GONE);
//            待退货
            myOrderReverseText.setTextSize(14);
            myOrderReverseLine.setVisibility(View.GONE);
//           待使用
            myOrderUseText.setTextSize(14);
            myOrderUseLine.setVisibility(View.GONE);
            OrderInPresenter.sendMyOrderData(885, 0);
        }
//        待使用
        if ("two".equals(order)) {
//            代付款
            myOrderPayText.setTextSize(14);
            myOrderPayLine.setVisibility(View.GONE);
//          我的订单
            myOrderAboveText.setTextSize(14);
            myOrderAboveLine.setVisibility(View.GONE);
//            待退货
            myOrderReverseText.setTextSize(14);
            myOrderReverseLine.setVisibility(View.GONE);
//           待使用
            myOrderUseText.setTextSize(16);
            myOrderUseLine.setVisibility(View.VISIBLE);
            OrderInPresenter.sendMyOrderData(885, 1);
        }
        //            待退货
        if ("three".equals(order)) {
//            代付款
            myOrderPayText.setTextSize(14);
            myOrderPayLine.setVisibility(View.GONE);
//          我的订单
            myOrderAboveText.setTextSize(14);
            myOrderAboveLine.setVisibility(View.GONE);
//            待退货
            myOrderReverseText.setTextSize(16);
            myOrderReverseLine.setVisibility(View.VISIBLE);
//           待使用
            myOrderUseText.setTextSize(14);
            myOrderUseLine.setVisibility(View.GONE);
            OrderInPresenter.sendMyOrderData(885, 4);
        }
//        我的订单
        if ("four".equals(order)) {
//            代付款
            myOrderPayText.setTextSize(14);
            myOrderPayLine.setVisibility(View.GONE);
//          我的订单
            myOrderAboveText.setTextSize(16);
            myOrderAboveLine.setVisibility(View.VISIBLE);
//            待退货
            myOrderReverseText.setTextSize(14);
            myOrderReverseLine.setVisibility(View.GONE);
//           待使用
            myOrderUseText.setTextSize(14);
            myOrderUseLine.setVisibility(View.GONE);
            OrderInPresenter.sendMyOrderData(885, -1);
        }


    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.myOrder_back, R.id.myOrder_above, R.id.myOrder_pay, R.id.myOrder_use, R.id.myOrder_reverse})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myOrder_back:
                finish();
                break;
            case R.id.myOrder_above:
//                replce(orderAboveFragment);
                //            代付款
                myOrderPayText.setTextSize(14);
                myOrderPayLine.setVisibility(View.GONE);
//          我的订单
                myOrderAboveText.setTextSize(16);
                myOrderAboveLine.setVisibility(View.VISIBLE);
//            待退货
                myOrderReverseText.setTextSize(14);
                myOrderReverseLine.setVisibility(View.GONE);
//           待使用
                myOrderUseText.setTextSize(14);
                myOrderUseLine.setVisibility(View.GONE);
                OrderInPresenter.sendMyOrderData(885, -1);
                break;
            case R.id.myOrder_pay:
                //            代付款
                myOrderPayText.setTextSize(16);
                myOrderPayLine.setVisibility(View.VISIBLE);
//          我的订单
                myOrderAboveText.setTextSize(14);
                myOrderAboveLine.setVisibility(View.GONE);
//            待退货
                myOrderReverseText.setTextSize(14);
                myOrderReverseLine.setVisibility(View.GONE);
//           待使用
                myOrderUseText.setTextSize(14);
                myOrderUseLine.setVisibility(View.GONE);
                OrderInPresenter.sendMyOrderData(885, 0);
                break;
            case R.id.myOrder_use:
                //            代付款
                myOrderPayText.setTextSize(14);
                myOrderPayLine.setVisibility(View.GONE);
//          我的订单
                myOrderAboveText.setTextSize(14);
                myOrderAboveLine.setVisibility(View.GONE);
//            待退货
                myOrderReverseText.setTextSize(14);
                myOrderReverseLine.setVisibility(View.GONE);
//           待使用
                myOrderUseText.setTextSize(16);
                myOrderUseLine.setVisibility(View.VISIBLE);
                OrderInPresenter.sendMyOrderData(885, 1);
                break;
            case R.id.myOrder_reverse:
                //            代付款
                myOrderPayText.setTextSize(14);
                myOrderPayLine.setVisibility(View.GONE);
//          我的订单
                myOrderAboveText.setTextSize(14);
                myOrderAboveLine.setVisibility(View.GONE);
//            待退货
                myOrderReverseText.setTextSize(16);
                myOrderReverseLine.setVisibility(View.VISIBLE);
//           待使用
                myOrderUseText.setTextSize(14);
                myOrderUseLine.setVisibility(View.GONE);
                OrderInPresenter.sendMyOrderData(885, 4);
                break;
        }
    }

    @Override
    public void showMyOrderData(MyOrderBean myOrderBean) {
        Log.d("MyOrderActivity", "myOrderBean.getData().getList().size():" + myOrderBean.getData().getList().size());
        List<MyOrderBean.DataBean.ListBean> list = myOrderBean.getData().getList();
        if (list.size() == 0) {
            myOrderEmptyContent.setVisibility(View.VISIBLE);
            myOrderRecyView.setVisibility(View.GONE);
        } else {
            myOrderEmptyContent.setVisibility(View.GONE);
            myOrderRecyView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            myOrderRecyView.setLayoutManager(linearLayoutManager);
            MyOrderAdapter myOrderAdapter = new MyOrderAdapter(list, this);
            myOrderRecyView.setAdapter(myOrderAdapter);
        }
    }


}
