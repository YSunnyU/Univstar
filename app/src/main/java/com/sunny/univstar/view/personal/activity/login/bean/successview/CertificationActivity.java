package com.sunny.univstar.view.personal.activity.login.bean.successview;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

//认证
public class CertificationActivity extends BaseActivity {
    @Bind(R.id.myCertification_back)
    ImageView myCertificationBack;
    @Bind(R.id.myCertification_name)
    EditText myCertificationName;
    @Bind(R.id.myCertification_majors)
    EditText myCertificationMajors;
    @Bind(R.id.myCertification_inputMess)
    EditText myCertificationInputMess;
    @Bind(R.id.myCertification_showIcCard)
    ImageView myCertificationShowIcCard;
    @Bind(R.id.myCertification_postIcCard)
    LinearLayout myCertificationPostIcCard;
    @Bind(R.id.myCertification_postBtn)
    Button myCertificationPostBtn;

    /*@Bind(R.id.myCertification_back)
        ImageView myCertificationBack;
        @Bind(R.id.myCertification_name)
        EditText myCertificationName;
        @Bind(R.id.myCertification_majors)
        EditText myCertificationMajors;
        @Bind(R.id.myCertification_inputMess)
        EditText myCertificationInputMess;
        @Bind(R.id.myCertification_showIcCard)
        ImageView myCertificationShowIcCard;
        @Bind(R.id.myCertification_postIcCard)
        LinearLayout myCertificationPostIcCard;
        @Bind(R.id.myCertification_postBtn)
        Button myCertificationPostBtn;
    */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_certification;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.myCertification_back, R.id.myCertification_showIcCard, R.id.myCertification_postIcCard, R.id.myCertification_postBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myCertification_back:
                break;
            case R.id.myCertification_showIcCard:
                break;
            case R.id.myCertification_postIcCard:
                break;
            case R.id.myCertification_postBtn:
                break;
        }
    }

}
