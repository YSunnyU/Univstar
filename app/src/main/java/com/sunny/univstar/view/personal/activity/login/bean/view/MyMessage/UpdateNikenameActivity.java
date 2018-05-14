package com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class UpdateNikenameActivity extends BaseActivity {
    @Bind(R.id.update_back)
    ImageView updateBack;
    @Bind(R.id.update_title)
    TextView updateTitle;
    @Bind(R.id.update_keep)
    Button updateKeep;
    @Bind(R.id.update_message)
    EditText updateMessage;
    //    @Bind(R.id.update_back)
//    ImageView updateBack;
//    @Bind(R.id.update_title)
//    TextView updateTitle;
//    @Bind(R.id.update_keep)
//    Button updateKeep;
//    @Bind(R.id.update_message)
//    EditText updateMessage;
    private Intent intent;
    private String loginUserId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_nikename;
    }

    @Override
    protected void init() {
        intent = getIntent();
        loginUserId = intent.getStringExtra("loginUserId");
        String nikeName = intent.getStringExtra("nikeName");
        String area = intent.getStringExtra("area");
        if ("updateName".equals(nikeName)) {
            updateTitle.setText("编辑昵称");
        }
        if ("updateArea".equals(area)) {
            updateTitle.setText("编辑详细地址");
        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.update_back, R.id.update_keep})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.update_back:
                finish();
                break;
            case R.id.update_keep:
//                Map<String,String> map = new HashMap<>();
                if (updateTitle.getText().toString().equals("编辑昵称")) {
//                    map.clear();
//                    map.put("id",loginUserId+"");
//                    map.put("nickname",updateMessage.getText().toString());
//                    map.put("realname","aaaaaaa");
//                    map.put("photo","");
//                    map.put("images","");
//                    map.put("intro","");
//                    map.put("details","");
//                    map.put("sex","0");
//                    map.put("birthday","");
//                    map.put("address","");
//                    updateUserDataInPresenter.sendUPdateUserData(map);
                    intent.putExtra("nameChange", updateMessage.getText().toString());
                    setResult(2, intent);
                    finish();
                }
                if ("编辑详细地址".equals(updateTitle.getText().toString())) {
//                    map.clear();
//                    map.put("id",loginUserId+"");
//                    map.put("nickname","");
//                    map.put("realname","");
//                    map.put("photo","");
//                    map.put("images","");
//                    map.put("intro","");
//                    map.put("details","");
//                    map.put("sex","0");
//                    map.put("birthday","");
//                    map.put("address",updateMessage.getText().toString());
                    intent.putExtra("areaChange", updateMessage.getText().toString());
                    setResult(7, intent);
                    finish();
                }

                break;
        }
    }


}
