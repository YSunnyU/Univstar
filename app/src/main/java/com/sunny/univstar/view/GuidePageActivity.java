package com.sunny.univstar.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sunny.univstar.R;

public class GuidePageActivity extends AppCompatActivity {

    private ImageView guideImage;
   // private Handler handler = new Handler();

    /*int len = 3;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            len--;

            if (len == 0) {
                startActivity(new Intent(GuidePageActivity.this, MainActivity.class));
                handler.removeCallbacks(runnable);
            } else {
                handler.postDelayed(runnable, 300);
            }
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);
        initView();

    }

    private void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(GuidePageActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
       // handler.postDelayed(runnable, 300);
    }
}
