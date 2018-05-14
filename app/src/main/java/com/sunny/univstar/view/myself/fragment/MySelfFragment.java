package com.sunny.univstar.view.myself.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.view.myself.jobs.FansActivity;
import com.sunny.univstar.view.myself.jobs.FollowActivity;
import com.sunny.univstar.view.myself.jobs.PostActivity;
import com.sunny.univstar.view.myself.jobs.WorksActivity;
import com.sunny.univstar.view.personal.activity.LoginActivity;
import com.sunny.univstar.view.personal.activity.LoginSetActivity;
import com.sunny.univstar.view.personal.activity.RegisterActivity;
import com.sunny.univstar.view.personal.activity.login.bean.successview.CertificationActivity;
import com.sunny.univstar.view.personal.activity.login.bean.successview.hobby.MyHobbyActivity;
import com.sunny.univstar.view.personal.activity.login.bean.successview.recharge.MyRechargeActivity;
import com.sunny.univstar.view.personal.activity.login.bean.view.HeadImageActivity;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection.MyCollectionActivity;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.order.MyOrderActivity;
import com.sunny.univstar.view.personal.activity.login.bean.view.PersonalInformationActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class MySelfFragment extends BaseFragment {

    //没有登录时注册按钮
    @Bind(R.id.home_myself_btn_register)
    Button homeMyselfBtnRegister;
    //    没有登录时登录按钮
    @Bind(R.id.home_myself_btn_login)
    Button homeMyselfBtnLogin;
    //    没有登录时界面
    @Bind(R.id.user_no_login)
    LinearLayout userNoLogin;
    //    登录成功后界面
    @Bind(R.id.user_yes_login)
    LinearLayout userYesLogin;
    //    设置  按钮
    @Bind(R.id.home_myself_settiog)
    ImageView homeMyselfSettiog;


    public static SharedPreferences sharePreferences;
    //    登录成功后个人头像
    @Bind(R.id.user_yes_login_img)
    ImageView userYesLoginImg;
    @Bind(R.id.login_user_name)
    TextView loginUserName;
    //    登录成功后我的信息
    @Bind(R.id.home_myself_content)
    TextView homeMyselfContent;
    //    登录成功后头部界面
    @Bind(R.id.home_myself_logined)
    RelativeLayout homeMyselfLogined;
    //    没有登录时用户头像
    @Bind(R.id.user_no_login_img)
    ImageView userNoLoginImg;
    //    消息按钮
    @Bind(R.id.home_myself_message)
    ImageView homeMyselfMessage;
    //    登录成功后重置中心
    @Bind(R.id.home_myself_yesLogin_myPay)
    RelativeLayout homeMyselfYesLoginMyPay;
    //    登录成功后个人礼物
    @Bind(R.id.home_myself_yesLogin_myGift)
    RelativeLayout homeMyselfYesLoginMyGift;
    //    登录成功后个人收藏
    @Bind(R.id.home_myself_yesLogin_myCollection)
    RelativeLayout homeMyselfYesLoginMyCollection;
    //    登录成功后个人偏好
    @Bind(R.id.home_myself_yesLogin_myHobby)
    RelativeLayout homeMyselfYesLoginMyHobby;
    //   登录成功后认证
    @Bind(R.id.home_myself_yesLogin_certification)
    TextView homeMyselfYesLoginCertification;
    @Bind(R.id.home_myself_yesLogin_goCertification)
    RelativeLayout homeMyselfYesLoginGoCertification;
    @Bind(R.id.home_myself_yesLogin_pay)
    LinearLayout homeMyselfYesLoginPay;
    @Bind(R.id.home_myself_yesLogin_user)
    LinearLayout homeMyselfYesLoginUser;
    @Bind(R.id.home_myself_yesLogin_reverse)
    LinearLayout homeMyselfYesLoginReverse;
    @Bind(R.id.home_myself_yesLogin_order)
    LinearLayout homeMyselfYesLoginOrder;
//    @Bind(R.id.user_yes_login_img)
//    ImageView userYesLoginImg;
    @Bind(R.id.jobsWorks_btn)
    LinearLayout jobsWorksBtn;
    @Bind(R.id.jobsPosts_btn)
    LinearLayout jobsPostsBtn;
    @Bind(R.id.jobsFollow_btn)
    LinearLayout jobsFollowBtn;
    @Bind(R.id.jobsFans_btn)
    LinearLayout jobsFansBtn;
    @Bind(R.id.czzx_go)
    ImageView czzxGo;
    @Bind(R.id.czzx_sl)
    TextView czzxSl;
//    @Bind(R.id.home_myself_yesLogin_myPay)
//    RelativeLayout homeMyselfYesLoginMyPay;
//    @Bind(R.id.home_myself_yesLogin_myGift)
//    RelativeLayout homeMyselfYesLoginMyGift;
//    @Bind(R.id.home_myself_yesLogin_myCollection)
//    RelativeLayout homeMyselfYesLoginMyCollection;
//    @Bind(R.id.home_myself_yesLogin_myHobby)
//    RelativeLayout homeMyselfYesLoginMyHobby;
    @Bind(R.id.wyrz_go)
    ImageView wyrzGo;
//    @Bind(R.id.home_myself_yesLogin_certification)
//    TextView homeMyselfYesLoginCertification;
//    @Bind(R.id.user_yes_login)
//    LinearLayout userYesLogin;
    private Intent myOrderIntent;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_self;
    }

    @Override
    protected void init() {
        myOrderIntent = new Intent(getActivity(), MyOrderActivity.class);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.user_yes_login_img, R.id.home_myself_content, R.id.user_no_login_img, R.id.home_myself_btn_register, R.id.home_myself_btn_login, R.id.home_myself_settiog, R.id.home_myself_yesLogin_myPay, R.id.home_myself_yesLogin_myGift, R.id.home_myself_yesLogin_myCollection, R.id.home_myself_yesLogin_myHobby, R.id.home_myself_yesLogin_goCertification, R.id.home_myself_yesLogin_pay, R.id.home_myself_yesLogin_user, R.id.home_myself_yesLogin_reverse, R.id.home_myself_yesLogin_order,R.id.jobsWorks_btn, R.id.jobsPosts_btn, R.id.jobsFollow_btn, R.id.jobsFans_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //            没有登录时的头像点击事件
            case R.id.user_no_login_img:
                sharePreferences = getActivity().getSharedPreferences("userState", 0);
                boolean isLogin = sharePreferences.getBoolean("isLogin", false);
                if (isLogin == false) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
//            注册
            case R.id.home_myself_btn_register:
                getActivity().startActivity(new Intent(getContext(), RegisterActivity.class));
                break;
//            登录
            case R.id.home_myself_btn_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
//            设置按钮
            case R.id.home_myself_settiog:
                sharePreferences = getActivity().getSharedPreferences("userState", 0);
                boolean isUserLogin = sharePreferences.getBoolean("isLogin", false);
                if (isUserLogin == false) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginSetActivity.class));
                }
                break;


//登录后界面
            //            登录成功后我的信息按钮
            case R.id.home_myself_content:
                startActivity(new Intent(getActivity(), PersonalInformationActivity.class));
                break;
            //            登录成功后的头像点击事件
            case R.id.user_yes_login_img:
//                点击头像跳转到头像页面
                startActivity(new Intent(getActivity(), HeadImageActivity.class));
                break;
//            充值中心
            case R.id.home_myself_yesLogin_myPay:
                startActivity(new Intent(getActivity(), MyRechargeActivity.class));
                break;
//            我的礼物
            case R.id.home_myself_yesLogin_myGift:

                break;
//            我的收藏
            case R.id.home_myself_yesLogin_myCollection:
                startActivity(new Intent(getActivity(), MyCollectionActivity.class));
                break;
//            我的偏好
            case R.id.home_myself_yesLogin_myHobby:
                startActivity(new Intent(getActivity(), MyHobbyActivity.class));
                break;
//            认证
            case R.id.home_myself_yesLogin_goCertification:
                startActivity(new Intent(getActivity(), CertificationActivity.class));
                break;
            //代付款
            case R.id.home_myself_yesLogin_pay:

                myOrderIntent.putExtra("order", "one");
                startActivity(myOrderIntent);
                break;
//            待使用
            case R.id.home_myself_yesLogin_user:
                myOrderIntent.putExtra("order", "two");
                startActivity(myOrderIntent);
                break;
//            待退货
            case R.id.home_myself_yesLogin_reverse:
                myOrderIntent.putExtra("order", "three");
                startActivity(myOrderIntent);
                break;
//            我的订单
            case R.id.home_myself_yesLogin_order:
                myOrderIntent.putExtra("order", "four");
                startActivity(myOrderIntent);
                break;
            case R.id.jobsWorks_btn:
                startActivity(new Intent(getActivity(), WorksActivity.class));
                break;
            case R.id.jobsPosts_btn:
                startActivity(new Intent(getActivity(), PostActivity.class));

                break;
            case R.id.jobsFollow_btn:
                startActivity(new Intent(getActivity(), FollowActivity.class));

                break;
            case R.id.jobsFans_btn:
                startActivity(new Intent(getActivity(), FansActivity.class));

                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences userState = getActivity().getSharedPreferences("userState", 0);
        boolean isUserLogin = userState.getBoolean("isLogin", false);
        String nickname = userState.getString("nickname", "");
        if (isUserLogin == true) {

            userYesLogin.setVisibility(View.VISIBLE);
            userNoLogin.setVisibility(View.GONE);
            homeMyselfLogined.setVisibility(View.VISIBLE);
            userNoLoginImg.setVisibility(View.GONE);
//            设置用户名字
            loginUserName.setText(nickname);
        } else {
            homeMyselfLogined.setVisibility(View.GONE);
            userNoLoginImg.setVisibility(View.VISIBLE);
            userYesLogin.setVisibility(View.GONE);
            userNoLogin.setVisibility(View.VISIBLE);
        }

    }




}
