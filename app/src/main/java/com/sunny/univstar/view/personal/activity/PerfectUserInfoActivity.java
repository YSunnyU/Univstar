package com.sunny.univstar.view.personal.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.RegisterContract;
import com.sunny.univstar.model.entity.RegisterCodeEntity;
import com.sunny.univstar.presenter.RegisterPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.sunny.univstar.view.personal.activity.LoginSetActivity.sharedPreferences;

public class PerfectUserInfoActivity extends BaseActivity implements RegisterContract.RegisterView {
    @Bind(R.id.perfect_info_return)
    ImageView perfectInfoReturn;
    @Bind(R.id.perfect_user_img)
    ImageView perfectUserImg;
    @Bind(R.id.perfect_user_name)
    EditText perfectUserName;
    @Bind(R.id.perfect_user_name_close)
    ImageView perfectUserNameClose;
    @Bind(R.id.user_sex_man)
    RadioButton userSexMan;
    @Bind(R.id.user_sex_woman)
    RadioButton userSexWoman;
    @Bind(R.id.user_sex_group)
    RadioGroup userSexGroup;
    @Bind(R.id.perfect_user_psw)
    EditText perfectUserPsw;
    @Bind(R.id.perfect_user_psw_close)
    ImageView perfectUserPswClose;
    @Bind(R.id.user_perfect_yes)
    Button userPerfectYes;
    private RegisterContract.RegisterPresenter presenter;
    /*//返回按钮
    @Bind(R.id.perfect_info_return)
    ImageView perfectInfoReturn;
    //    头像上传
    @Bind(R.id.perfect_user_img)
    ImageView perfectUserImg;
    //    用户昵称
    @Bind(R.id.perfect_user_name)
    EditText perfectUserName;
    //    删除用户昵称
    @Bind(R.id.perfect_user_name_close)
    ImageView perfectUserNameClose;
    //    男
    @Bind(R.id.user_sex_man)
    RadioButton userSexMan;
    //    女
    @Bind(R.id.user_sex_woman)
    RadioButton userSexWoman;
    //    选择器
    @Bind(R.id.user_sex_group)
    RadioGroup userSexGroup;
    //    密码框
    @Bind(R.id.perfect_user_psw)
    EditText perfectUserPsw;
    //    删除密码
    @Bind(R.id.perfect_user_psw_close)
    ImageView perfectUserPswClose;
    //    完成完善
    @Bind(R.id.user_perfect_yes)
    Button userPerfectYes;*/
    private String phone;
    private SharedPreferences.Editor edit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_perfect_user_info;
    }

    @Override
    protected void init() {
        presenter = new RegisterPresenter(this);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.perfect_info_return, R.id.perfect_user_img, R.id.perfect_user_name_close, R.id.perfect_user_psw_close, R.id.user_perfect_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            返回
            case R.id.perfect_info_return:
                finish();
                break;
//            头像
            case R.id.perfect_user_img:


                break;
//            删除昵称
            case R.id.perfect_user_name_close:
                perfectUserName.setText("");
                break;
//            删除密码
            case R.id.perfect_user_psw_close:
                perfectUserPsw.setText("");
                break;
//            完成完善
            case R.id.user_perfect_yes:
                String userSex = "";
                if (userSexMan.isChecked()) {
                    userSex = "0";
                } else if (userSexWoman.isChecked()) {
                    userSex = "1";
                }
                Intent intent = getIntent();
                phone = intent.getStringExtra("phone");
                Map<String, String> map = new HashMap<>();
                map.put("sex", userSex);
                map.put("nickname", perfectUserName.getText().toString());
                map.put("photo", "");
                map.put("mobile", phone);
                map.put("psw", perfectUserPsw.getText().toString());
                presenter.sendRegister("https://www.univstar.com/v1/m/user/saveCompleteUser", map);
                break;
        }
    }

    @Override
    public void getIdentifyingCode(RegisterCodeEntity registerCodeEntity) {
        Toast.makeText(this, registerCodeEntity.getMessage(), Toast.LENGTH_SHORT).show();
//        保存用戶狀態
        SharedPreferences userState = getSharedPreferences("userState", 0);
        edit = userState.edit();
//        登录状态
//        如果是0 就没有登录
//        如果是1  就已经登录
        sharedPreferences = getSharedPreferences("keeepUserName", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("isLogin", false);
        edit.commit();
//        edit.putBoolean("isUserLogin",false);
        Toast.makeText(this, registerCodeEntity.getMessage(), Toast.LENGTH_SHORT).show();
//        注册成功自动登陆
        if (registerCodeEntity.getCode() != 0) {
            Map<String, String> map = new HashMap<>();
            map.put("mobile", phone);
            map.put("password", perfectUserPsw.getText().toString());
            presenter.sendCode("https://www.univstar.com/v1/m/user/login/mobile", map);
        }
    }

    @Override
    public void codePhoneAndIdentifying(RegisterCodeEntity registerCodeEntity) {
        edit.putString("userId", registerCodeEntity.getData().getId() + "");
        edit.commit();
    }

    private int f = 1;


}
