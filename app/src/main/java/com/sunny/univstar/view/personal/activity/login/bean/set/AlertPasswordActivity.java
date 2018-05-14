package com.sunny.univstar.view.personal.activity.login.bean.set;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.AlertPasswordContract;
import com.sunny.univstar.presenter.AlertPasswordPresenter;
import com.sunny.univstar.view.personal.activity.login.bean.AlertPswBean;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

//修改密码登录密码第一个页面
public class AlertPasswordActivity extends BaseActivity implements AlertPasswordContract.AlertPasswordInView {

    public AlertPasswordContract.AlertPasswordInPresenter alertPasswordInPresenter;
    @Bind(R.id.alertPsw_phoneNum)
    TextView alertPswPhoneNum;
    @Bind(R.id.alertPsw_ValidateCode)
    EditText alertPswValidateCode;
    @Bind(R.id.alertPsw_getValidateCode)
    TextView alertPswGetValidateCode;
    @Bind(R.id.alertPsw_btn)
    Button alertPswBtn;
    //    @Bind(R.id.alertPsw_back)
//    ImageView alertPswBack;
//    @Bind(R.id.alertPsw_ValidateCode)
//    EditText alertPswValidateCode;
//    @Bind(R.id.alertPsw_getValidateCode)
//    TextView alertPswGetValidateCode;
//    @Bind(R.id.alertPsw_btn)
//    Button alertPswBtn;
//    @Bind(R.id.alertPsw_phoneNum)
//    TextView alertPswPhoneNum;
    private int codeInt = 60;
    private Timer timer;
    public SharedPreferences sharedPreferences;
    private String mobile;
    private String authCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_alert_password;
    }

    @Override
    protected void init() {
        timer = new Timer();
        alertPasswordInPresenter = new AlertPasswordPresenter(this);
        sharedPreferences = getSharedPreferences("userState", MODE_PRIVATE);
        mobile = sharedPreferences.getString("mobile", "");
        alertPswPhoneNum.setText(mobile);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.alertPsw_back, R.id.alertPsw_getValidateCode, R.id.alertPsw_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.alertPsw_back:
                finish();
                break;
            case R.id.alertPsw_getValidateCode:
                alertPasswordInPresenter.sendValidateCodeData(mobile);
                //    计时器
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        codeInt--;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                alertPswGetValidateCode.setText(codeInt + "s");
                                alertPswGetValidateCode.setEnabled(false);
                                if (codeInt < 1) {
                                    alertPswGetValidateCode.setText("获取验证码");
                                    alertPswGetValidateCode.setEnabled(true);
                                    timer.cancel();
                                    codeInt = 60;
                                }
                            }
                        });

                    }
                }, 0, 1000);
//                alertPasswordInPresenter.sendValidateCodeData(mobile);
                break;
            case R.id.alertPsw_btn:
                alertPasswordInPresenter.sendIsValidateCodeDataTrueData(mobile, alertPswValidateCode.getText().toString());

                break;
        }
    }

    //    得到验证码
    @Override
    public void getValidateCodeDataData(AlertPswBean alertPswBean) {
        if (alertPswBean.getMessage().equals("验证码已发送")) {
            Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show();
        }

    }

    //    判断验证码是否正确
    @Override
    public void getIsValidateCodeDataTrueData(AlertPswBean alertPswBean) {

        if (alertPswBean.getMessage().equals("成功")) {
            sharedPreferences = getSharedPreferences("userState", MODE_PRIVATE);
//            int loginUserId = sharedPreferences.getInt("loginUserId", 0);
//            mobile = sharedPreferences.getString("mobile", "");
//            Log.d("AlertPasswordActivity", alertPswBean.getMessage());
            Intent intent = new Intent(AlertPasswordActivity.this, NewPswActivity.class);
            startActivity(intent);
        }
        Toast.makeText(this, alertPswBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
