package com.sunny.univstar.view.personal.activity.login.bean.set;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.NewPswContract;
import com.sunny.univstar.presenter.NewPswPresenter;
import com.sunny.univstar.view.MainActivity;
import com.sunny.univstar.view.personal.activity.login.bean.LoginSucessBean;

import butterknife.Bind;
import butterknife.OnClick;

public class NewPswActivity extends BaseActivity implements NewPswContract.NewPasswordInView {

    public NewPswContract.NewPasswordInPresenter newPasswordInPresenter;
    /*@Bind(R.id.newPsw_back)
    ImageView newPswBack;
    @Bind(R.id.newPsw_psw)
    EditText newPswPsw;
    @Bind(R.id.newPsw_rePsw)
    EditText newPswRePsw;
    @Bind(R.id.newPsw_okBtn)
    Button newPswOkBtn;*/
    public SharedPreferences sharedPreferences;
    @Bind(R.id.newPsw_back)
    ImageView newPswBack;

    @Bind(R.id.newPsw_psw)
    EditText newPswPsw;
    @Bind(R.id.newPsw_rePsw)
    EditText newPswRePsw;
    @Bind(R.id.newPsw_okBtn)
    Button newPswOkBtn;
    private String mobile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_psw;
    }

    @Override
    protected void init() {
        newPasswordInPresenter = new NewPswPresenter(this);
        sharedPreferences = getSharedPreferences("userState", MODE_PRIVATE);
        mobile = sharedPreferences.getString("mobile", "");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.newPsw_back, R.id.newPsw_okBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.newPsw_back:
                finish();
                break;
            case R.id.newPsw_okBtn:
                if (newPswPsw.getText().toString().equals(newPswRePsw.getText().toString())) {
                    newPasswordInPresenter.sendPasswordData(mobile, newPswPsw.getText().toString());
                }
                break;
        }
    }


    @Override
    public void getPasswordData(LoginSucessBean loginSucessBean) {
        if (loginSucessBean.getMessage().equals("成功")) {
            LoginSucessBean.DataBean data = loginSucessBean.getData();

            //            用户名
            String nickname = data.getNickname();
//            用户头像
            String images = (String) data.getImages();
//           用户相别
            int sex = data.getSex();
//            用户手机号
            String mobile = data.getMobile();

            sharedPreferences = getSharedPreferences("userState", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("isLogin", false);
            edit.putString("nickname", nickname);
            edit.putInt("sex", sex);
            edit.putString("images", images);
            edit.putString("mobile", mobile);
            edit.commit();
            Intent intent = new Intent(NewPswActivity.this, MainActivity.class);
            intent.putExtra("upDataPsw", "aab");
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, loginSucessBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



}
