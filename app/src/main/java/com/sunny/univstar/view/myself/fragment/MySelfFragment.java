package com.sunny.univstar.view.myself.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.view.personal.activity.RegisterActivity;

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


    @OnClick({R.id.home_myself_btn_register, R.id.home_myself_btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_myself_btn_register:
                getActivity().startActivity(new Intent(getContext(), RegisterActivity.class));
                break;
            case R.id.home_myself_btn_login:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences userState = getActivity().getSharedPreferences("userState", 0);
        if (userState.getBoolean("isUserLogin", false)) {
            userYesLogin.setVisibility(View.VISIBLE);
            userNoLogin.setVisibility(View.GONE);
        }else {
            userYesLogin.setVisibility(View.GONE);
            userNoLogin.setVisibility(View.VISIBLE);
        }

    }

}
