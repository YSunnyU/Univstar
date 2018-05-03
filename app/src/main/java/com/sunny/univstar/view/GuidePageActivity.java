package com.sunny.univstar.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sunny.univstar.R;

public class GuidePageActivity extends AppCompatActivity {

    private ImageView guideImage;
    private Handler handler = new Handler();

    int len = 3;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            len--;

            if (len == 0) {
                startActivity(new Intent(GuidePageActivity.this, PreferenceActivity.class));
            } else {
                handler.postDelayed(runnable, 300);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);
        initView();

    }

    private void initView() {
        guideImage = (ImageView) findViewById(R.id.guideImage);
        guideImage.setImageResource(R.mipmap.guide);
        handler.postDelayed(runnable,300);
    }
}
