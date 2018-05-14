package com.sunny.univstar.view.teachertype.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.model.utils.MD5Util2;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

public class CoachActivity extends BaseActivity {


    @Bind(R.id.coact_return)
    TextView coactReturn;
    @Bind(R.id.coact_major)
    RecyclerView coactMajor;
    @Bind(R.id.coact_teacher_name)
    TextView coactTeacherName;
    @Bind(R.id.coact_add)
    LinearLayout coactAdd;
    @Bind(R.id.coact_content)
    EditText coactContent;
    @Bind(R.id.coact_permission)
    TextView coactPermission;
    @Bind(R.id.coact_money)
    TextView coactMoney;
    @Bind(R.id.coact_video)
    CustomVideoView coactVideo;
    @Bind(R.id.coact_video_stater)
    ImageView coactVideoStater;
    @Bind(R.id.coact_video_rela)
    RelativeLayout coactVideoRela;
    private String videoPath;
    private Intent intent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coach;
    }

    @Override
    protected void init() {
        intent = getIntent();
        String name = intent.getStringExtra("name");
        videoPath = intent.getExtras().getString("videoPath");
        coactTeacherName.setText(name);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.coact_return, R.id.coact_add, R.id.coact_permission,R.id.coact_video_stater})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.coact_return:
                finish();
                break;
            case R.id.coact_add:
                startActivity(new Intent(this, ShootVideoActivity.class));
                finish();
                break;
            case R.id.coact_permission:
                break;
            case R.id.coact_video_stater:
                coactVideoStater.setVisibility(View.GONE);
                coactVideo.setBackground(null);
                coactVideo.start();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String videoUrl = intent.getStringExtra("videoUrl");
        if (videoUrl != null) {
            File file = new File(videoUrl);
            String fileMD5 = MD5Util2.getFileMD5(file);
            Log.e("video-md5", fileMD5);
            coactVideoRela.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse(videoUrl);
            coactVideo.setMediaController(new MediaController(this));
            coactVideo.setOnCompletionListener( new MyPlayerOnCompletionListener());
            coactVideo.setVideoURI(uri);
            Bitmap localVideoThumbnail = getLocalVideoThumbnail(videoUrl);
            Drawable bitmapDrawable = new BitmapDrawable(localVideoThumbnail);
            coactVideo.setBackground(bitmapDrawable);
            coactAdd.setEnabled(false);
        }
    }
    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            coactVideoStater.setVisibility(View.VISIBLE);
        }
    }
    public Bitmap getLocalVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        //MediaMetadataRetriever 是android中定义好的一个类，提供了统一
        //的接口，用于从输入的媒体文件中取得帧和元数据；
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(filePath);
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }
}
