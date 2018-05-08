package com.sunny.univstar.view.personal.activity.login.bean.view;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class HeadImageActivity extends BaseActivity {


    @Bind(R.id.head_image_back)
    ImageView headImageBack;
    @Bind(R.id.head_image_userImage)
    ImageView headImageUserImage;
    @Bind(R.id.head_image_userName)
    TextView headImageUserName;
    @Bind(R.id.head_image_attentionNum)
    TextView headImageAttentionNum;
    @Bind(R.id.head_image_fanNum)
    TextView headImageFanNum;
    @Bind(R.id.head_image_production)
    RelativeLayout headImageProduction;
    @Bind(R.id.head_image_invitation)
    RelativeLayout headImageInvitation;
    @Bind(R.id.head_image_productionText)
    TextView headImageProductionText;
    @Bind(R.id.head_image_production_line)
    TextView headImageProductionLine;
    @Bind(R.id.head_image_invitationText)
    TextView headImageInvitationText;
    @Bind(R.id.head_image_invitationLine)
    TextView headImageInvitationLine;
    @Bind(R.id.head_image_fragContainer)
    FrameLayout headImageFragContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_head_image;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.head_image_back)
    public void onViewClicked() {
        finish();
    }


    @OnClick({R.id.head_image_production, R.id.head_image_invitation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_image_production:
                headImageProductionText.setTextSize(17);
                headImageInvitationText.setTextSize(15);
                headImageProductionLine.setVisibility(View.VISIBLE);
                headImageInvitationLine.setVisibility(View.GONE);
                break;
            case R.id.head_image_invitation:
                headImageProductionText.setTextSize(15);
                headImageInvitationText.setTextSize(17);
                headImageProductionLine.setVisibility(View.GONE);
                headImageInvitationLine.setVisibility(View.VISIBLE);
                break;
        }
    }
}
