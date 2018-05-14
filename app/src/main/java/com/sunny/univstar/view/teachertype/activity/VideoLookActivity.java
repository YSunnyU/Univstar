package com.sunny.univstar.view.teachertype.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class VideoLookActivity extends BaseActivity {


    @Bind(R.id.video_look_return)
    ImageView videoLookReturn;
    @Bind(R.id.video_look_yes)
    TextView videoLookYes;
    @Bind(R.id.video_look_view)
    CustomVideoView videoLookView;
    private String videoPath;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_look;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        videoPath = intent.getExtras().getString("videoPath");
        Uri uri = Uri.parse(videoPath);
        videoLookView.setMediaController(new MediaController(this));
        videoLookView.setOnCompletionListener( new MyPlayerOnCompletionListener());
        videoLookView.setVideoURI(uri);
        videoLookView.start();
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.video_look_return, R.id.video_look_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.video_look_return:
                finish();
                break;
            case R.id.video_look_yes:
                Intent intent = new Intent(this,CoachActivity.class);
                intent.putExtra("videoUrl", videoPath);
                startActivity(intent);
                finish();
                break;
        }
    }
    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            videoLookView.start();
        }
    }
}
