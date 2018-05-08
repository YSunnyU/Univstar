package com.sunny.univstar.view.myself.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.view.personal.activity.LoginActivity;
import com.sunny.univstar.view.personal.activity.LoginSetActivity;
import com.sunny.univstar.view.personal.activity.RegisterActivity;
import com.sunny.univstar.view.personal.activity.login.bean.view.HeadImageActivity;
import com.sunny.univstar.view.personal.activity.login.bean.view.PersonalInformationActivity;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MySelfFragment extends BaseFragment {


    @Bind(R.id.home_myself_btn_register)
    Button homeMyselfBtnRegister;
    @Bind(R.id.home_myself_btn_login)
    Button homeMyselfBtnLogin;
    @Bind(R.id.user_no_login)
    LinearLayout userNoLogin;
    @Bind(R.id.user_yes_login)
    LinearLayout userYesLogin;
    @Bind(R.id.home_myself_settiog)
    ImageView homeMyselfSettiog;
    @Bind(R.id.czzx_go)
    ImageView czzxGo;
    @Bind(R.id.czzx_sl)
    TextView czzxSl;
    @Bind(R.id.wyrz_go)
    ImageView wyrzGo;
    public static SharedPreferences sharePreferences;
    @Bind(R.id.user_yes_login_img)
    ImageView userYesLoginImg;
    @Bind(R.id.login_user_name)
    TextView loginUserName;
    @Bind(R.id.home_myself_content)
    TextView homeMyselfContent;
    @Bind(R.id.home_myself_logined)
    RelativeLayout homeMyselfLogined;
    @Bind(R.id.user_no_login_img)
    ImageView userNoLoginImg;
    @Bind(R.id.home_myself_message)
    ImageView homeMyselfMessage;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_self;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.user_yes_login_img, R.id.home_myself_content, R.id.user_no_login_img, R.id.home_myself_btn_register, R.id.home_myself_btn_login, R.id.home_myself_settiog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_myself_btn_register:
                getActivity().startActivity(new Intent(getContext(), RegisterActivity.class));
                break;
            case R.id.home_myself_btn_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);

                startActivity(intent);
                break;
            case R.id.home_myself_settiog:
                sharePreferences = getActivity().getSharedPreferences("userState", 0);
                boolean isUserLogin = sharePreferences.getBoolean("isLogin", false);
                if (isUserLogin == false) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginSetActivity.class));
                }
                break;
//            登录成功后的头像点击事件
            case R.id.user_yes_login_img:
//                点击头像跳转到头像页面
                startActivity(new Intent(getActivity(), HeadImageActivity.class));
                break;
//            登录成功后我的信息按钮
            case R.id.home_myself_content:
                startActivity(new Intent(getActivity(), PersonalInformationActivity.class));
                break;
//            没有登录时的头像点击事件
            case R.id.user_no_login_img:
                sharePreferences = getActivity().getSharedPreferences("userState", 0);
                boolean isLogin = sharePreferences.getBoolean("isLogin", false);
                if (isLogin == false) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
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
            loginUserName.setText(nickname);
        } else {
            homeMyselfLogined.setVisibility(View.GONE);
            userNoLoginImg.setVisibility(View.VISIBLE);
            userYesLogin.setVisibility(View.GONE);
            userNoLogin.setVisibility(View.VISIBLE);
        }

    }
}
