package com.sunny.univstar.view.personal.activity.login.bean.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.UpdateUserMessContract;
import com.sunny.univstar.presenter.UpdateUserMessPresenter;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.UpdateNikenameActivity;
import com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.UpdateUserBean;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

import static com.sunny.univstar.app.App.context;

public class PersonalInformationActivity extends BaseActivity implements UpdateUserMessContract.UpdateUserDataInView {

    @Bind(R.id.userInfo_back)
    ImageView userInfoBack;
    @Bind(R.id.userInfo_avatar)
    ImageView userInfoAvatar;
    @Bind(R.id.userInfo_userImage)
    RelativeLayout userInfoUserImage;
    @Bind(R.id.userInfo_userName_nickname)
    TextView userInfoUserNameNickname;
    @Bind(R.id.userInfo_userName)
    RelativeLayout userInfoUserName;
    @Bind(R.id.userInfo_changeSex_sex)
    TextView userInfoChangeSexSex;
    @Bind(R.id.userInfo_changeSex)
    RelativeLayout userInfoChangeSex;
    @Bind(R.id.userInfo_changeArea_area)
    TextView userInfoChangeAreaArea;
    @Bind(R.id.userInfo_changeArea)
    RelativeLayout userInfoChangeArea;
    @Bind(R.id.userInfo_changeBirthday_birthday)
    TextView userInfoChangeBirthdayBirthday;
    @Bind(R.id.userInfo_changeBirthday)
    RelativeLayout userInfoChangeBirthday;
    public static SharedPreferences sharedPreferences;
    private Uri mUri;
    private Bitmap head;
    private Intent intent;
    private int sex = 0;
    public UpdateUserMessContract.UpdateUserDataInPresenter updateUserDataInPresenter;
    private int loginUserId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void init() {
        updateUserDataInPresenter = new UpdateUserMessPresenter(this);
        sharedPreferences = getSharedPreferences("userState", 0);
        loginUserId = sharedPreferences.getInt("loginUserId", 0);
        String nickname = sharedPreferences.getString("nickname", "");
        userInfoUserNameNickname.setText(nickname);
    }

    @Override
    protected void initData() {


    }

    @OnClick({R.id.userInfo_back, R.id.userInfo_userImage, R.id.userInfo_userName, R.id.userInfo_changeSex, R.id.userInfo_changeArea, R.id.userInfo_changeBirthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.userInfo_back:
                String userName = userInfoUserNameNickname.getText().toString();
                String userArea = userInfoChangeAreaArea.getText().toString();
                Log.d("PersonalInformationActi", userName);
                Log.d("PersonalInformationActi", userArea);
//                updateUserDataInPresenter.sendUPdateUserData(loginUserId, userName, sex, "1991227", userArea);

                finish();
                break;
            case R.id.userInfo_userImage:
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
//                dialog.dismiss();
                break;
            case R.id.userInfo_userName:
                intent = new Intent(PersonalInformationActivity.this, UpdateNikenameActivity.class);
                intent.putExtra("nikeName", "updateName");
                startActivityForResult(intent, 1);
                break;
            case R.id.userInfo_changeSex:
                final String[] items = new String[]{"男", "女"};

                AlertDialog dialog = new AlertDialog.Builder(this).setTitle("请选择")
                        .setItems(items, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sex = which;
                                userInfoChangeSexSex.setText(items[which]);
//                                postUserInfo();
                            }
                        }).create();
                dialog.show();
                break;
            case R.id.userInfo_changeArea:
                intent.putExtra("area", "updateArea");
                startActivityForResult(intent, 2);
                break;
            case R.id.userInfo_changeBirthday:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理扫描结果（在界面上显示）
        switch (requestCode) {

            case 1:
                if (resultCode == RESULT_OK) {
                    // 裁剪图片
                    mUri = data.getData();
                    cropPhoto(mUri);
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    // 裁剪图片
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    mUri = Uri.fromFile(temp);
                    cropPhoto(mUri);
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");

                    ByteArrayOutputStream bao = new ByteArrayOutputStream();
                    head.compress(Bitmap.CompressFormat.JPEG, 100, bao);
                    byte[] datas = bao.toByteArray();
                    if (head != null) {
//                        saveBitmap(head);//保存Bitmap文件到本地

                        Glide.with(this)
                                .load(datas)
                                .asBitmap()
                                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                                .into(new ImageViewTarget<Bitmap>(userInfoAvatar) {
                                    @Override
                                    protected void setResource(Bitmap resource) {
                                        RoundedBitmapDrawable circularBitmapDrawable =
                                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                                        circularBitmapDrawable.setCircular(true); //设置圆角弧度
                                        userInfoAvatar.setBackground(circularBitmapDrawable);
                                    }
                                });
                    }
                }
                break;
        }

        if (requestCode == 1 && resultCode == 2) {
            String nameChange = data.getStringExtra("nameChange");
            userInfoUserNameNickname.setText(nameChange);
        }
        if (requestCode == 2 && resultCode == 7) {
            String areaChange = data.getStringExtra("areaChange");
            userInfoChangeAreaArea.setText(areaChange);
        }


    }

    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);

        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }


    @Override
    public void showUpdateUserData(UpdateUserBean updateUserBean) {
        Log.d("PersonalInformationActi", updateUserBean.getMessage());
    }
}
