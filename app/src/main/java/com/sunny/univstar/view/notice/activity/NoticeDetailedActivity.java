package com.sunny.univstar.view.notice.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.FollowPraiseContract;
import com.sunny.univstar.contract.NoticeDetailedContract;
import com.sunny.univstar.model.entity.NoticeDetailedEntity;
import com.sunny.univstar.model.utils.ShapeUtils;
import com.sunny.univstar.presenter.FollowPraiisePresenter;
import com.sunny.univstar.presenter.NoticeDetailedPresenter;
import com.sunny.univstar.view.personal.activity.LoginActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


public class NoticeDetailedActivity extends BaseActivity implements NoticeDetailedContract.NoticeDetailedView, FollowPraiseContract.FollowPraiseView {
    @Bind(R.id.notice_detailed_collect)
    CheckBox noticeDetailedCollect;
    @Bind(R.id.notice_detailed_phone)
    TextView noticeDetailedPhone;
    @Bind(R.id.notice_detailed_buy)
    LinearLayout noticeDetailedBuy;
    @Bind(R.id.masterdetail_coachbtn)
    LinearLayout masterdetailCoachbtn;
    @Bind(R.id.notice_detailed_msg_img)
    ImageView noticeDetailedMsgImg;
    @Bind(R.id.notice_detailed_reservation)
    TextView noticeDetailedReservation;
    @Bind(R.id.notice_detailed_subscribe)
    TextView noticeDetailedSubscribe;
    @Bind(R.id.notice_detailed_time)
    TextView noticeDetailedTime;
    @Bind(R.id.img_re)
    RelativeLayout imgRe;
    @Bind(R.id.notice_detailed_type_text)
    TextView noticeDetailedTypeText;
    @Bind(R.id.notice_detailed_type)
    LinearLayout noticeDetailedType;
    @Bind(R.id.notice_detailed_address)
    TextView noticeDetailedAddress;
    @Bind(R.id.notice_detailed_price)
    TextView noticeDetailedPrice;
    @Bind(R.id.img_re4)
    LinearLayout imgRe4;
    @Bind(R.id.live_course_detailed_web)
    WebView liveCourseDetailedWeb;
    @Bind(R.id.notice_detailed_return)
    ImageView noticeDetailedReturn;
    @Bind(R.id.notice_detailed_share)
    ImageView noticeDetailedShare;

/*//    收藏
    @Bind(R.id.notice_detailed_collect)
    CheckBox noticeDetailedCollect;
//    咨询电话
    @Bind(R.id.notice_detailed_phone)
    TextView noticeDetailedPhone;
//    预约
    @Bind(R.id.notice_detailed_buy)
    LinearLayout noticeDetailedBuy;
//    内容图片
    @Bind(R.id.notice_detailed_msg_img)
    ImageView noticeDetailedMsgImg;
//    已预约数
    @Bind(R.id.notice_detailed_reservation)
    TextView noticeDetailedReservation;
//    总预约数
    @Bind(R.id.notice_detailed_subscribe)
    TextView noticeDetailedSubscribe;
//    开课时间
    @Bind(R.id.notice_detailed_time)
    TextView noticeDetailedTime;
//    类型
    @Bind(R.id.notice_detailed_type_text)
    TextView noticeDetailedTypeText;

    @Bind(R.id.notice_detailed_type)
    LinearLayout noticeDetailedType;

//    地址
    @Bind(R.id.notice_detailed_address)
    TextView noticeDetailedAddress;
//    价格
    @Bind(R.id.notice_detailed_price)
    TextView noticeDetailedPrice;
//    web
    @Bind(live_course_detailed_web)
    WebView liveCourseDetailedWeb;
//    返回
    @Bind(R.id.notice_detailed_return)
    ImageView noticeDetailedReturn;
//    分享
    @Bind(R.id.notice_detailed_share)
    ImageView noticeDetailedShare;*/


    private NoticeDetailedContract.NoticeDetailedPresenter noticeDetailedPresenter;
    private String id;
    private String mobile;
    private int noticeId;
    private FollowPraiseContract.FollowPraisePresenter followPraisePresenter;
    private boolean isLogin;
    private int userId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice_detailed;
    }

    @Override
    protected void init() {
        followPraisePresenter = new FollowPraiisePresenter(this);
        noticeDetailedPresenter = new NoticeDetailedPresenter(this);
        SharedPreferences userState = getSharedPreferences("userState", 0);
        userId = userState.getInt("loginUserId", 0);
        isLogin = userState.getBoolean("isLogin", false);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Log.e("aaaaaaaaaaaaaaa", id + "");
        Map<String, String> map = new HashMap<>();
        map.put("loginUserId", userId + "");
        map.put("courseId", id);
        noticeDetailedPresenter.sendNoticeDetailed(map);
        webSetting(liveCourseDetailedWeb);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.notice_detailed_collect, R.id.notice_detailed_phone, R.id.notice_detailed_buy, R.id.notice_detailed_return, R.id.notice_detailed_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.notice_detailed_collect:
                if (!isLogin) {
                    noticeDetailedCollect.setChecked(false);
                    startActivity(new Intent(this, LoginActivity.class));
                    return;
                } else {
                    if (noticeDetailedCollect.isChecked()) {
                        Map<String, String> map = new HashMap<>();
                        map.put("loginUserId", userId + "");
                        map.put("id", noticeId + "");
                        map.put("type", "体验课");
                        followPraisePresenter.sendFollowPraise("https://www.univstar.com/v1/m/user/favorite", map);
                    } else {
                        Map<String, String> map = new HashMap<>();
                        map.put("loginUserId", userId + "");
                        map.put("id", noticeId + "");
                        map.put("type", "体验课");
                        followPraisePresenter.sendFollowPraise("https://www.univstar.com/v1/m/user/favorite/cancel", map);
                    }
                }
                break;
            case R.id.notice_detailed_phone:
                call(mobile);
                break;
            case R.id.notice_detailed_buy:
                break;
            case R.id.notice_detailed_return:
                finish();
                break;
            case R.id.notice_detailed_share:
                ShapeUtils shapeUtils = new ShapeUtils(this);
                shapeUtils.setWeb("http://share.univstar.com/share/trailer-detail.html?courseId=" + noticeId,
                        null, "风里雨里,心愿艺考等你", R.mipmap.ic_launcher);
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void getNoticeDetailedData(NoticeDetailedEntity noticeDetailedEntity) {
        Log.e("aaaaaaaaaaaaaaa", noticeDetailedEntity.getMessage());
        if (noticeDetailedEntity.getCode() == 0) {
            NoticeDetailedEntity.DataBean data = noticeDetailedEntity.getData();
            noticeId = data.getId();
            mobile = data.getMobile();
            if (data.getFavorite() == 0) {
                noticeDetailedCollect.setChecked(false);
            } else {
                noticeDetailedCollect.setChecked(true);
            }
//            设置内容图片
            Glide.with(this).load(data.getCoverImg())
                    .asBitmap()
                    .into(noticeDetailedMsgImg);

//            设置预约
            noticeDetailedReservation.setText("" + data.getSubscribeNum());
            noticeDetailedSubscribe.setText(data.getReservationNum() + "");

//            设置开课时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date(data.getStartDate());
            noticeDetailedTime.setText(sdf.format(date));

//            设置地址
            noticeDetailedAddress.setText(data.getAddress());

//            设置价格
            noticeDetailedPrice.setText(data.getPrice() + "");

            liveCourseDetailedWeb.loadUrl("http://share.univstar.com/view/course.html?courseid=" + this.id);
        } else {
            Toast.makeText(this, noticeDetailedEntity.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void webSetting(WebView webView) {
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

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

        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void getFollowPraise(String msg) {

    }

}
