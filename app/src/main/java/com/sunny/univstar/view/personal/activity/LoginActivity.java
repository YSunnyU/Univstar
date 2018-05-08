package com.sunny.univstar.view.personal.activity;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.LoginContract;
import com.sunny.univstar.presenter.LoginPresenter;
import com.sunny.univstar.view.personal.activity.login.bean.LoginBean;
import com.sunny.univstar.view.personal.activity.login.bean.LoginSucessBean;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.LoginContractInView {

    LoginContract.LoginContractInPresenter loginContractInPresenter;
    @Bind(R.id.login_aty_close_tv)
    TextView loginAtyCloseTv;
    @Bind(R.id.login_aty_register_tv)
    TextView loginAtyRegisterTv;
    @Bind(R.id.login_aty_phone_et)
    EditText loginAtyPhoneEt;
    @Bind(R.id.login_aty_phone_et_clear)
    ImageView loginAtyPhoneEtClear;
    @Bind(R.id.login_aty_pass_et)
    EditText loginAtyPassEt;
    @Bind(R.id.login_aty_pass_et_clear)
    ImageView loginAtyPassEtClear;
    @Bind(R.id.login_aty_findpass_tv)
    TextView loginAtyFindpassTv;
    @Bind(R.id.login_aty_loginbtn)
    Button loginAtyLoginbtn;
    @Bind(R.id.login_aty_weixin_btn)
    LinearLayout loginAtyWeixinBtn;
    @Bind(R.id.login_aty_qq_btn)
    LinearLayout loginAtyQqBtn;
    @Bind(R.id.login_aty_weibo_btn)
    LinearLayout loginAtyWeiboBtn;
    private int anInt;
    public SharedPreferences sharedPreferences;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        loginContractInPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.login_aty_close_tv, R.id.login_aty_register_tv, R.id.login_aty_phone_et_clear, R.id.login_aty_pass_et_clear, R.id.login_aty_findpass_tv, R.id.login_aty_loginbtn, R.id.login_aty_weixin_btn, R.id.login_aty_qq_btn, R.id.login_aty_weibo_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_aty_close_tv:
                break;
            case R.id.login_aty_register_tv:
                break;
            case R.id.login_aty_phone_et_clear:
                break;
            case R.id.login_aty_pass_et_clear:
                break;
            case R.id.login_aty_findpass_tv:
                break;
            case R.id.login_aty_loginbtn:
                loginContractInPresenter.sendLoginData(loginAtyPhoneEt.getText().toString(), loginAtyPassEt.getText().toString());
                break;
            case R.id.login_aty_weixin_btn:
                break;
            case R.id.login_aty_qq_btn:
                break;
            case R.id.login_aty_weibo_btn:
                break;
        }
    }

    @Override
    public void showLoginData(LoginBean loginBean) {
        Log.d("LoginActivity", "loginBean.getData().getId():" + loginBean.getData().getId());
        anInt = loginBean.getData().getId();
        loginContractInPresenter.sendUserLoginData(anInt);
    }

    @Override
    public void showLoginSuccessData(LoginSucessBean loginSucessBean) {


        if (loginSucessBean.getMessage().equals("成功")){
            LoginSucessBean.DataBean data = loginSucessBean.getData();
//        "nickname": "Sunny",
//                "realname": null,
//                "photo": null,
//                "images": null,
//                "intro": null,
//                "details": null,
//                "mobile": "13718620289",
            String nickname = data.getNickname();
            String images = (String) data.getImages();
            int sex = data.getSex();
            Log.d("LoginActivity", "sex:" + sex);

            sharedPreferences = getSharedPreferences("userState", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("isLogin",true);
            edit.putString("nickname", nickname);
            edit.putInt("sex",sex);
            edit.putString("images", images);
            edit.commit();
            finish();
        }else {
            Toast.makeText(this, loginSucessBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
