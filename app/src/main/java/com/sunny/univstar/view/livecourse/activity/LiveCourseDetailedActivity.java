package com.sunny.univstar.view.livecourse.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.LiveCourseDetailedContract;
import com.sunny.univstar.model.entity.LiveCourseDetailedEntity;
import com.sunny.univstar.presenter.LiveCourseDetailedPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LiveCourseDetailedActivity extends BaseActivity implements View.OnClickListener,LiveCourseDetailedContract.LiveCourseDetailedView {


    private CheckBox live_course_detailed_collect;
    private LinearLayout live_course_detailed_buy;
    private ImageView live_course_detailed_msg_img;
    private TextView live_course_detailed_classify;
    private TextView live_course_detailed_course_time;
    private ImageView live_course_detailed__header_img;
    private TextView live_course_detailed_nick_name;
    private ImageView live_course_detailed_vip;
    private TextView live_course_detailed_synopsis;
    private CheckBox live_course_detailed_follow_check;
    private TextView course_type_text;
    private LinearLayout live_course_detailed_type;
    private TextView live_course_detailed_reservation;
    private TextView live_course_detailed_subscribe;
    private TextView live_course_detailed_item_price;
    private WebView live_course_detailed_web;
    private ImageView live_course_detailed_return;
    private ImageView live_course_detailed_share;
    private LiveCourseDetailedContract.LiveCourseDetailedPresenter liveCourseDetailedPresenter;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live_course_detailed;
    }

    @Override
    protected void init() {
        liveCourseDetailedPresenter = new LiveCourseDetailedPresenter(this);
//        收藏按钮
          live_course_detailed_collect = (CheckBox) findViewById(R.id.live_course_detailed_collect);
//        购买
          live_course_detailed_buy = (LinearLayout) findViewById(R.id.live_course_detailed_buy);
        live_course_detailed_buy.setOnClickListener(this);
//        内容头图片
          live_course_detailed_msg_img = (ImageView) findViewById(R.id.live_course_detailed_msg_img);
//        重播还是直播
          live_course_detailed_classify = (TextView) findViewById(R.id.live_course_detailed_classify);
//        讲堂课堂
          live_course_detailed_course_time = (TextView) findViewById(R.id.live_course_detailed_course_time);
//        头像
          live_course_detailed__header_img = (ImageView) findViewById(R.id.live_course_detailed__header_img);
//        昵称
          live_course_detailed_nick_name = (TextView) findViewById(R.id.live_course_detailed_nick_name);
//        vip图片
          live_course_detailed_vip = (ImageView) findViewById(R.id.live_course_detailed_vip);
//        老师类别
          live_course_detailed_synopsis = (TextView) findViewById(R.id.live_course_detailed_synopsis);
//        关注
          live_course_detailed_follow_check = (CheckBox) findViewById(R.id.live_course_detailed_follow_check);
        live_course_detailed_follow_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        live_course_detailed_follow_check.setText("已关注");
                        live_course_detailed_follow_check.setTextColor(Color.parseColor("#9c9c9c"));
                    } else {
                        live_course_detailed_follow_check.setText("关注");
                    }
            }
        });
//        标签
          live_course_detailed_type = (LinearLayout) findViewById(R.id.live_course_detailed_type);
//        已预约数
          live_course_detailed_reservation = (TextView) findViewById(R.id.live_course_detailed_reservation);
//        总预约数
          live_course_detailed_subscribe = (TextView) findViewById(R.id.live_course_detailed_subscribe);
//        价格
          live_course_detailed_item_price = (TextView) findViewById(R.id.live_course_detailed_item_price);
//        Js显示内容
          live_course_detailed_web = (WebView) findViewById(R.id.live_course_detailed_web);
//        返回按钮
          live_course_detailed_return = (ImageView) findViewById(R.id.live_course_detailed_return);
        live_course_detailed_return.setOnClickListener(this);
//        分享按钮
          live_course_detailed_share = (ImageView) findViewById(R.id.live_course_detailed_share);
        live_course_detailed_share.setOnClickListener(this);
        webSetting();
        SharedPreferences userState = getSharedPreferences("userState", 0);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String userId = userState.getString("userId", "0");
        Map<String,String> map = new HashMap<>();
        map.put("loginUserId",userId);
//        直播课程Id
        map.put("id",id);
        liveCourseDetailedPresenter.sendLiveCourseDetailed(map);
    }

    private void webSetting(){
        //声明WebSettings子类
        WebSettings webSettings = live_course_detailed_web.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        live_course_detailed_web.setWebViewClient(new WebViewClient());
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.live_course_detailed_buy:
                Toast.makeText(this, "购买", Toast.LENGTH_SHORT).show();
                break;
            case R.id.live_course_detailed_return:
                finish();
                break;
            case R.id.live_course_detailed_share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void getLiveCourseDetailedData(LiveCourseDetailedEntity liveCourseDetailedEntity) {
          if (liveCourseDetailedEntity.getCode() == 0){
//              加载头图片
              Glide.with(this).load(liveCourseDetailedEntity.getData().getCoverImg())
                      .asBitmap()
                      .into(live_course_detailed_msg_img);
              live_course_detailed_classify.setText("重播");
//              讲堂课堂
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
              Date date = new Date(liveCourseDetailedEntity.getData().getStartDate());
              live_course_detailed_course_time.setText(""+sdf.format(date));

//              設置頭像
              Glide.with(this)
                      .load(liveCourseDetailedEntity.getData().getPhoto())
                      .asBitmap()
                      .placeholder(R.mipmap.user_head_portrait)
                      .diskCacheStrategy(DiskCacheStrategy.NONE)
                      .into(new BitmapImageViewTarget(live_course_detailed__header_img){
                          @Override
                          protected void setResource(Bitmap resource) {
                              RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),resource);
                              bitmapDrawable.setCircular(true);
                              live_course_detailed__header_img.setImageDrawable(bitmapDrawable);
                          }
                      });
//              設置昵稱
              live_course_detailed_nick_name.setText(liveCourseDetailedEntity.getData().getNickname());
//              設置Vip圖片類型
              if (liveCourseDetailedEntity.getData().getUserType() == 4){
                  live_course_detailed_vip.setImageResource(R.mipmap.home_level_vip_red);
              }else if (liveCourseDetailedEntity.getData().getUserType() == 3){
                  live_course_detailed_vip.setImageResource(R.mipmap.home_level_vip_yellow);
              }else if (liveCourseDetailedEntity.getData().getUserType() == 2){
                  live_course_detailed_vip.setImageResource(R.mipmap.home_level_vip_blue);
              }

//              老師類別
              live_course_detailed_synopsis.setText(liveCourseDetailedEntity.getData().getIntro());
//              已預約數
              live_course_detailed_reservation.setText(""+liveCourseDetailedEntity.getData().getSubscribe());
//              縂預約數
              live_course_detailed_subscribe.setText(""+liveCourseDetailedEntity.getData().getSubscribeNum());
//              價格
              live_course_detailed_item_price.setText(""+liveCourseDetailedEntity.getData().getPrice());

//              webView和JS交互
                live_course_detailed_web.loadUrl("http://share.univstar.com/view/live.html?id="+id);
          }else {
              Toast.makeText(this, liveCourseDetailedEntity.getMessage(), Toast.LENGTH_SHORT).show();
          }
    }
}
