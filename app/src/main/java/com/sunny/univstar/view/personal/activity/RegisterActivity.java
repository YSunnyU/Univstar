package com.sunny.univstar.view.personal.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.RegisterContract;
import com.sunny.univstar.model.entity.RegisterCodeEntity;
import com.sunny.univstar.presenter.RegisterPresenter;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.OnClick;

import static com.sunny.univstar.R.id.register_phone_code;

public class RegisterActivity extends BaseActivity implements RegisterContract.RegisterView{
//     String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
private String regex = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
    //    手机号
    @Bind(R.id.register_phone_number)
    EditText registerPhoneNumber;
    //    删除手机号
    @Bind(R.id.phon_close)
    ImageView phonClose;
    //    验证码
    @Bind(register_phone_code)
    EditText registerPhoneCode;
    //    获取验证码
    @Bind(R.id.get_register_code)
    TextView getRegisterCode;
    //    删除验证码
    @Bind(R.id.phone_code_close)
    ImageView phoneCodeClose;
    //    服务协议
    @Bind(R.id.univstar_protocol)
    TextView univstarProtocol;
    //    注册
    @Bind(R.id.phone_register_btn)
    Button phoneRegisterBtn;
    //    微信登陆
    @Bind(R.id.weixin_login)
    LinearLayout weixinLogin;
    //    QQ登陆
    @Bind(R.id.qq_login)
    LinearLayout qqLogin;
    //    微博登陆
    @Bind(R.id.weibo_login)
    LinearLayout weiboLogin;
    //    返回
    @Bind(R.id.register_return)
    ImageView registerReturn;

//    发送验证码
    private final int SENDCODE = 0;
    //    验证手机号
    private final int PHONECODE = 1;
    //    验证验证码
    private final int CODECODE = 2;

    private int codeInt = 60;
    private boolean isCode = true;
    private Timer timer;
    //    P层
    private RegisterContract.RegisterPresenter registerPresenter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            if (msg.what == SENDCODE){
                timer = new Timer();
                RegisterCodeEntity regsiterCodeEntity= (RegisterCodeEntity) msg.obj;
                if ("成功".equals(regsiterCodeEntity.getMessage()) && isCode){
                    Toast.makeText(RegisterActivity.this, "手机号码验证"+regsiterCodeEntity.getMessage(), Toast.LENGTH_SHORT).show();
                    Map<String,String> map = new HashMap<>();
                    map.put("mobile",registerPhoneNumber.getText().toString());
                    map.put("authCode",registerPhoneCode.getText().toString());
                    registerPresenter.sendRegister("https://www.univstar.com/v1/m/user/verify/authcode",map);
                    isCode = false;
                    Intent intent = new Intent(RegisterActivity.this, PerfectUserInfoActivity.class);
                    intent.putExtra("phone",registerPhoneNumber.getText().toString());
                    startActivity(intent);
                    finish();
                }
                if ("验证码已发送".equals(regsiterCodeEntity.getMessage())){
                    Toast.makeText(RegisterActivity.this, regsiterCodeEntity.getMessage(), Toast.LENGTH_SHORT).show();
                    //    计时器
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            codeInt--;
;
                            getRegisterCode.setText(codeInt + "");
                            getRegisterCode.setEnabled(false);
                            if (codeInt < 1){
                                getRegisterCode.setText("获取验证码");
                                getRegisterCode.setEnabled(true);
                                timer.cancel();
                                codeInt = 60;
                            }
                        }
                    }, 0, 1000);
                }
            }else {
                RegisterCodeEntity registerCodeEntity = (RegisterCodeEntity) msg.obj;
                Toast.makeText(RegisterActivity.this, registerCodeEntity.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        registerPresenter = new RegisterPresenter(this);
        registerPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    phonClose.setVisibility(View.VISIBLE);
                }
                if (start < 1) {
                    phonClose.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        registerPhoneCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0){
                    phoneCodeClose.setVisibility(View.VISIBLE);
                }
                if (start < 1){
                    phoneCodeClose.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.register_return,R.id.register_phone_number, R.id.phon_close, register_phone_code, R.id.get_register_code, R.id.phone_code_close, R.id.univstar_protocol, R.id.phone_register_btn, R.id.weixin_login, R.id.qq_login, R.id.weibo_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            删除手机号
            case R.id.phon_close:
                registerPhoneNumber.setText("");
                phonClose.setVisibility(View.INVISIBLE);
                break;
//            获取验证码
            case R.id.get_register_code:
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher1 = pattern.matcher(registerPhoneNumber.getText().toString());
                boolean flag = matcher1.matches();

                if (flag){
                    Map<String,String> map = new HashMap<>();
                    map.put("mobile",registerPhoneNumber.getText().toString());
                    registerPresenter.sendRegister("https://www.univstar.com/v1/m/user/authcode",map);
                }else {

                }
                break;
//            删除验证码
            case R.id.phone_code_close:
                registerPhoneCode.setText("");
                phoneCodeClose.setVisibility(View.INVISIBLE);
                break;
//            服务协议
            case R.id.univstar_protocol:
                break;
//            注册
            case R.id.phone_register_btn:
                Map<String,String> map = new HashMap<>();
                map.put("mobile",registerPhoneNumber.getText().toString());
                registerPresenter.sendRegister("https://www.univstar.com/v1/m/user/verify/mobile",map);
                break;
//            微信登陆
            case R.id.weixin_login:
                break;
//            qq登陆
            case R.id.qq_login:
                break;
//            微博登陆
            case R.id.weibo_login:
                break;
            case R.id.register_return:
                finish();
                break;
        }
    }

    @Override
    public void getIdentifyingCode(RegisterCodeEntity registerCodeEntity) {
//        向Handler发送成功或失败
        Message message = new Message();
        message.what = registerCodeEntity.getCode();
        message.obj = registerCodeEntity;
        handler.sendMessage(message);
    }

    @Override
    public void codePhoneAndIdentifying(RegisterCodeEntity registerCodeEntity) {

    }
}
