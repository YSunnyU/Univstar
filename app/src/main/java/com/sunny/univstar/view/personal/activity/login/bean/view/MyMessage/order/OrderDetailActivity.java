package com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.order;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.OrderDetailContract;
import com.sunny.univstar.presenter.OrderDetailPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity implements OrderDetailContract.OrederDetailInView {
    //    @Bind(R.id.myOrderDetail_timer)
//    TextView myOrderDetailTimer;
//    @Bind(R.id.myOrderDetail_title)
//    TextView myOrderDetailTitle;
//    @Bind(R.id.myOrderDetail_payStatus)
//    TextView myOrderDetailPayStatus;
//    @Bind(R.id.myOrderItem_skipNotice)
//    RelativeLayout myOrderItemSkipNotice;
//    @Bind(R.id.myOrderDetail_Image)
//    ImageView myOrderDetailImage;
//    @Bind(R.id.myOrderDetail_time)
//    TextView myOrderDetailTime;
//    @Bind(R.id.myOrderDetail_money)
//    TextView myOrderDetailMoney;
//    @Bind(R.id.myOrderDetail_pay)
//    Button myOrderDetailCPay;
//    @Bind(R.id.myOrderDetail_orderNum)
//    TextView myOrderDetailOrderNum;
//    @Bind(R.id.myOrderDetail_Num)
//    TextView myOrderDetailNum;
//    @Bind(R.id.myOrderDetail_deleteBtn)
//    Button myOrderDetailDeleteBtn;
//    @Bind(R.id.myOrderDetail_copyOrder)
//    Button myOrderDetailCopyOrder;
    public OrderDetailContract.OrderDetailinPresenter orderDetailInPredenter;
    @Bind(R.id.myOrderDetail_timer)
    TextView myOrderDetailTimer;
    @Bind(R.id.myOrderDetail_title)
    TextView myOrderDetailTitle;
    @Bind(R.id.myOrderDetail_payStatus)
    TextView myOrderDetailPayStatus;
    @Bind(R.id.myOrderItem_skipNotice)
    RelativeLayout myOrderItemSkipNotice;
    @Bind(R.id.myOrderDetail_Image)
    ImageView myOrderDetailImage;
    @Bind(R.id.myOrderDetail_one)
    TextView myOrderDetailOne;
    @Bind(R.id.myOrderDetail_time)
    TextView myOrderDetailTime;
    @Bind(R.id.myOrderDetail_money)
    TextView myOrderDetailMoney;
    @Bind(R.id.myOrderDetail_pay)
    Button myOrderDetailPay;
    @Bind(R.id.myOrderDetail_order)
    TextView myOrderDetailOrder;
    @Bind(R.id.myOrderDetail_orderNum)
    TextView myOrderDetailOrderNum;
    @Bind(R.id.myOrderDetail_copyOrder)
    Button myOrderDetailCopyOrder;
    @Bind(R.id.myOrderDetail_startTime)
    TextView myOrderDetailStartTime;
    @Bind(R.id.myOrderDetail_Num)
    TextView myOrderDetailNum;
    @Bind(R.id.myOrderDetail_deleteBtn)
    Button myOrderDetailDeleteBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail_activity;
    }

    @Override
    protected void init() {
        orderDetailInPredenter = new OrderDetailPresenter(this);
        Intent intent = getIntent();
//        intent.getStringExtra("myOrderItem_title");
//        intent.getStringExtra("myOrderItem_Image");
//        intent.getStringExtra("myOrderItem_time");
//        intent.getStringExtra("myOrderItem_money");
        String oid = intent.getStringExtra("oid");
        String type = intent.getStringExtra("type");

        orderDetailInPredenter.sendOrderDetailData(oid, type + "课");

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.myOrderDetail_copyOrder, R.id.myOrderDetail_deleteBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myOrderDetail_copyOrder:
                break;
            case R.id.myOrderDetail_deleteBtn:

                break;
        }
    }

    @Override
    public void showOrderDetailData(OrderDetailBean orderDetailBean) {
        OrderDetailBean.DataBean data = orderDetailBean.getData();
        OrderDetailBean.DataBean.OrderInfoBean orderInfo = data.getOrderInfo();

        myOrderDetailTitle.setText(orderInfo.getTitle());
        Glide.with(this).load(orderInfo.getCoverImg()).asBitmap().into(myOrderDetailImage);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        Date date = new Date(orderInfo.getStartDate());
        myOrderDetailTime.setText(sdf.format(date));
        myOrderDetailMoney.setText("￥" + orderInfo.getPrice() + ".0");
        myOrderDetailOrderNum.setText(orderInfo.getOrderNo());
        SimpleDateFormat sdff = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        Date datee = new Date(orderInfo.getCreateDate());
        myOrderDetailNum.setText(sdff.format(datee));


    }


}
