package com.sunny.univstar.view.work.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.app.App;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.FollowPraiseContract;
import com.sunny.univstar.contract.ValuableDetailedContract;
import com.sunny.univstar.contract.WorkDetailedContract;
import com.sunny.univstar.model.entity.SendValuableDetailedCommentsEntity;
import com.sunny.univstar.model.entity.SendWorkDetailedCommentsEntity;
import com.sunny.univstar.model.entity.ValuableDetailedCommentsEntity;
import com.sunny.univstar.model.entity.ValuableDetailedEntity;
import com.sunny.univstar.model.entity.WorkDetailedCommentsEntity;
import com.sunny.univstar.model.entity.WorkDetailedEntity;
import com.sunny.univstar.presenter.FollowPraiisePresenter;
import com.sunny.univstar.presenter.ValuableDetailedPresenter;
import com.sunny.univstar.presenter.WorkDetailedPresenter;
import com.sunny.univstar.view.master.fragment.autoui.MyScrollView;
import com.sunny.univstar.view.valuable.fragment.adapter.ValuableDetailedCommentsAdapter;
import com.sunny.univstar.view.work.adapter.WorkDetailedCommentsAdapter;
import com.sunny.univstar.view.work.adapter.WorkDetailedRewardAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkDetailedActivity extends BaseActivity implements View.OnClickListener,ValuableDetailedContract.ValuableDetailedView,WorkDetailedContract.WorkDetailedView,FollowPraiseContract.FollowPraiseView {


    private ImageView work_detailed_return;
    private ImageView work_detailed_head_img;
    private TextView work_detailed_nick_name;
    private TextView work_detailed_time;
    private TextView work_detailed_ify;
    private TextView work_detailed_content;
    private ImageView work_detailed_content_img;
    private TextView work_detailed_award;
    private RecyclerView work_detailed_award_list;
    private NestedListView work_detailed_discuss;
    private TextView work_detailed__input_btn;
    private TextView work_detailed_praise_btn;
    private TextView work_detailed_praise_num;
    private RelativeLayout wok_detail_aty_praise_group;
    private TextView work_detailed_comment_btn;
    private TextView work_detailed_comment_num;
    private RelativeLayout wok_detail_aty_comment_group;
    private RelativeLayout work_detailed__input_group;
    private Button work_detailed_pinglun_send;
    private EditText work_detailed_pinglun_input;
    private RelativeLayout wok_detail_aty_pinglun_input_group;
    private TextView gengduo;
    private LinearLayout fayan;
    private MyScrollView myScrollViewHehe;
    private WorkDetailedContract.WorkDetailedPresenter workDetailedPresenter;
    private List<WorkDetailedCommentsEntity.DataBean.CommentsBean> commentsList;
    private List<ValuableDetailedCommentsEntity.DataBean.CommentsBean> valuableCommentsList;
    private WorkDetailedCommentsAdapter adapter;
    private ValuableDetailedCommentsAdapter valuableDetailedCommentsAdapter;
    private List<WorkDetailedEntity.DataBean.RewardUserListBean> rewardUserList;
    private WorkDetailedRewardAdapter mAdapter;
    private WorkDetailedEntity.DataBean.HomewokBean homewokBean;
    private ValuableDetailedEntity.DataBean.ArtcircleBean artcircleBean;
    private FollowPraiseContract.FollowPraisePresenter followPraisePresenter;
    private ValuableDetailedContract.ValuableDetailedPresenter valuableDetailedPresenter;
    private boolean isPraise;
    private int userId;
    private String type;
    private String homewokId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_detailed;
    }

    @Override
    protected void init() {
        valuableDetailedPresenter = new ValuableDetailedPresenter(this);
        followPraisePresenter = new FollowPraiisePresenter(this);
        commentsList = new ArrayList<>();
        valuableCommentsList = new ArrayList<>();
        rewardUserList = new ArrayList<>();
        workDetailedPresenter = new WorkDetailedPresenter(this);



          work_detailed_return = (ImageView) findViewById(R.id.work_detailed_return);
        work_detailed_return.setOnClickListener(this);
//        头像
          work_detailed_head_img = (ImageView) findViewById(R.id.work_detailed_head_img);
//        昵称
          work_detailed_nick_name = (TextView) findViewById(R.id.work_detailed_nick_name);
        myScrollViewHehe = (MyScrollView) findViewById(R.id.myScrollViewHehe);
//        发表时间
          work_detailed_time = (TextView) findViewById(R.id.work_detailed_time);
//        发表分类
          work_detailed_ify = (TextView) findViewById(R.id.work_detailed_ify);
//          发表内容
          work_detailed_content = (TextView) findViewById(R.id.work_detailed_content);
//        内容图片
          work_detailed_content_img = (ImageView) findViewById(R.id.work_detailed_content_img);
//        更多
        gengduo = (TextView) findViewById(R.id.gengduo);
//        发言
        fayan = (LinearLayout) findViewById(R.id.fayan);
//        赞赏
          work_detailed_award = (TextView) findViewById(R.id.work_detailed_award);
        work_detailed_award.setOnClickListener(this);
//        赞赏头像
          work_detailed_award_list = (RecyclerView) findViewById(R.id.work_detailed_award_list);
//        评论列表
          work_detailed_discuss = (NestedListView) findViewById(R.id.work_detailed_discuss);
//        写评论
          work_detailed__input_btn = (TextView) findViewById(R.id.work_detailed__input_btn);
        work_detailed__input_btn.setOnClickListener(this);
//        赞按钮
          work_detailed_praise_btn = (TextView) findViewById(R.id.work_detailed_praise_btn);
        work_detailed_praise_btn.setOnClickListener(this);
//        赞数量
          work_detailed_praise_num = (TextView) findViewById(R.id.work_detailed_praise_num);
//          评论按钮
          work_detailed_comment_btn = (TextView) findViewById(R.id.work_detailed_comment_btn);
//          评论数量
          work_detailed_comment_num = (TextView) findViewById(R.id.work_detailed_comment_num);
//          评论1显示or影藏
          work_detailed__input_group = (RelativeLayout) findViewById(R.id.work_detailed__input_group);
//      发送评论
          work_detailed_pinglun_send = (Button) findViewById(R.id.work_detailed_pinglun_send);
        work_detailed_pinglun_send.setOnClickListener(this);
//      输入框
          work_detailed_pinglun_input = (EditText) findViewById(R.id.work_detailed_pinglun_input);
//      评论2显示or影藏
          wok_detail_aty_pinglun_input_group = (RelativeLayout) findViewById(R.id.wok_detail_aty_pinglun_input_group);
        myScrollViewHehe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (wok_detail_aty_pinglun_input_group.getVisibility() == View.VISIBLE) {
                    HideKeyboard(myScrollViewHehe);
                    work_detailed__input_group.setVisibility(View.VISIBLE);
                    wok_detail_aty_pinglun_input_group.setVisibility(View.GONE);
                    return true;
                }
                return false;
            }
        });
        work_detailed_discuss.setFocusable(false);
        adapter = new WorkDetailedCommentsAdapter(commentsList,this);
        adapter.setOnClickItem(new WorkDetailedCommentsAdapter.OnClickItem() {
            @Override
            public void onClickItem(View view, int position) {
                switch (view.getId()){
                    case R.id.comment_listitem_praise_cb:
                        CheckBox checkBox = view.findViewById(R.id.comment_listitem_praise_cb);
                        if (checkBox.isChecked()) {
                            prasre("https://www.univstar.com/v1/m/user/praise", commentsList.get(0).getList().get(position).getUserId(),commentsList.get(0).getList().get(position).getId(), "作业评论");

                            commentsList.get(0).getList().get(position).setPraiseNum(commentsList.get(0).getList().get(position).getPraiseNum() + 1);
                            checkBox.setText(commentsList.get(0).getList().get(position).getPraiseNum() + "");
                        } else {
                            prasre("https://www.univstar.com/v1/m/user/praise/cancel", commentsList.get(0).getList().get(position).getUserId(),commentsList.get(0).getList().get(position).getId(), "作业评论");
                            if (commentsList.get(0).getList().get(position).getPraiseNum() == 0) {
                                checkBox.setText(commentsList.get(0).getList().get(position).getPraiseNum() + "");
                            } else {
                                commentsList.get(0).getList().get(position).setPraiseNum(commentsList.get(0).getList().get(position).getPraiseNum() - 1);
                                checkBox.setText(commentsList.get(0).getList().get(position).getPraiseNum() + "");
                            }


                        }
                        break;
                    case R.id.comments_geng_duo:
                        Intent intent = new Intent(WorkDetailedActivity.this, ReplyListActivity.class);
                        intent.putExtra("workId",homewokId+"");
                        intent.putExtra("pid",commentsList.get(0).getList().get(position).getId()+"");
                        intent.putExtra("toContent",commentsList.get(0).getList().get(position).getContent()+"");
                        intent.putExtra("toId",commentsList.get(0).getList().get(position).getUserId()+"");
                        startActivity(intent);
                        break;
                    case R.id.comments_huifu:
                        Intent intent2 = new Intent(WorkDetailedActivity.this, ReplyListActivity.class);
                        intent2.putExtra("workId",homewokId+"");
                        intent2.putExtra("pid",commentsList.get(0).getList().get(position).getId()+"");
                        intent2.putExtra("toContent",commentsList.get(0).getList().get(position).getContent()+"");
                        intent2.putExtra("toId",commentsList.get(0).getList().get(position).getUserId()+"");
                        startActivity(intent2);
                        break;
                }

            }
        });
        work_detailed_discuss.setAdapter(adapter);
        mAdapter = new WorkDetailedRewardAdapter(rewardUserList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        work_detailed_award_list.setLayoutManager(linearLayoutManager);
        work_detailed_award_list.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildPosition(view) != (rewardUserList.size() - 1)) {
                    outRect.right = -30;
                }
            }
        });
        work_detailed_award_list.setAdapter(mAdapter);
        Intent intent = getIntent();
        homewokId = intent.getStringExtra("homewokId");
        type = intent.getStringExtra("type");
        Log.e("aaaaax", homewokId +"");
        SharedPreferences userState = getSharedPreferences("userState", 0);
        userId = userState.getInt("loginUserId",0);

        Log.e("aaaaaxx", userId +"");
        if (!"宝典".equals(type)) {
            Map<String, String> map = new HashMap<>();
            map.put("loginUserId", userId + "");
            map.put("homewokId", homewokId);
            workDetailedPresenter.sendWorkDetailed("https://www.univstar.com/v1/m/homewok/detail", map);
        }else {
            Map<String, String> map = new HashMap<>();
            map.put("loginUserId", userId + "");
            map.put("artcircleId", homewokId);
            valuableDetailedPresenter.sendValuableDetailed("https://www.univstar.com/v1/m/artcircle/detail", map);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            发送
            case R.id.work_detailed_pinglun_send:

                if (!TextUtils.isEmpty(work_detailed_pinglun_input.getText().toString())) {
                    work_detailed__input_group.setVisibility(View.VISIBLE);
                    wok_detail_aty_pinglun_input_group.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
//隐藏软键盘 //
                    imm.hideSoftInputFromWindow(work_detailed_pinglun_send.getWindowToken(), 0);

                    if (!"宝典".equals(type)) {
                        Map<String, String> map = new HashMap<>();
                        map.put("userId", userId + "");
                        map.put("content", work_detailed_pinglun_input.getText().toString().trim());
                        map.put("refId", refid + "");
                        map.put("refType",  "作业回复");
                        map.put("status", "0");
                        workDetailedPresenter.sendWorkDetailedCommentsList("https://www.univstar.com/v1/m/homewok/comments/save", map);
                    }else {
                        Map<String, String> map = new HashMap<>();
                        map.put("userId", userId + "");
                        map.put("content", work_detailed_pinglun_input.getText().toString().trim());
                        map.put("refId", refid+"");
                        map.put("refType",  "艺考圈评论");
                        map.put("status", "0");
                        valuableDetailedPresenter.sendValuableDetailedCommentsList("https://www.univstar.com/v1/m/artcircle/comments/save", map);
                    }
                    work_detailed_pinglun_input.setText("");
                }
                break;
//            返回
            case R.id.work_detailed_return:
                finish();
                break;
//            赞赏
            case R.id.work_detailed_award:
                break;
//            评论
            case R.id.work_detailed__input_btn:
                work_detailed__input_group.setVisibility(View.GONE);
                wok_detail_aty_pinglun_input_group.setVisibility(View.VISIBLE);
                break;
//            赞
            case R.id.work_detailed_praise_btn:
                if (!"宝典".equals(type)) {
                    if (isPraise) {
                        prasre("https://www.univstar.com/v1/m/user/praise/cancel", homewokBean.getStudentId(), homewokBean.getId(), "学生作业");
                        isPraise = false;
                        work_detailed_praise_btn.setActivated(false);
                        if (homewokBean.getPraiseNum() == 0) {
                            work_detailed_praise_num.setText(homewokBean.getPraiseNum() + "");
                        } else {
                            homewokBean.setPraiseNum(homewokBean.getPraiseNum() - 1);
                            work_detailed_praise_num.setText(homewokBean.getPraiseNum() + "");
                        }
                    } else {
                        prasre("https://www.univstar.com/v1/m/user/praise", homewokBean.getStudentId(), homewokBean.getId(), "学生作业");
                        isPraise = true;
                        work_detailed_praise_btn.setActivated(true);
                        homewokBean.setPraiseNum(homewokBean.getPraiseNum() + 1);
                        work_detailed_praise_num.setText(homewokBean.getPraiseNum() + "");
                    }
                }else {
                    if (isPraise) {
                        prasre("https://www.univstar.com/v1/m/user/praise/cancel", artcircleBean.getUserId(), artcircleBean.getId(), "艺考圈作品");
                        isPraise = false;
                        work_detailed_praise_btn.setActivated(false);
                        if (artcircleBean.getPraiseNum() == 0) {
                            work_detailed_praise_num.setText(artcircleBean.getPraiseNum() + "");
                        } else {
                            artcircleBean.setPraiseNum(artcircleBean.getPraiseNum() - 1);
                            work_detailed_praise_num.setText(artcircleBean.getPraiseNum() + "");
                        }
                    } else {
                        prasre("https://www.univstar.com/v1/m/user/praise", artcircleBean.getUserId(), artcircleBean.getId(), "艺考圈作品");
                        isPraise = true;
                        work_detailed_praise_btn.setActivated(true);
                        artcircleBean.setPraiseNum(artcircleBean.getPraiseNum() + 1);
                        work_detailed_praise_num.setText(artcircleBean.getPraiseNum() + "");
                    }
                }
                break;
        }
    }
    private int refid;
    @Override
    public void getWorkDetailedData(WorkDetailedEntity workDetailedEntity) {
        if (workDetailedEntity.getCode() == 0){
            refid = workDetailedEntity.getData().getHomewok().getId();
            homewokBean = workDetailedEntity.getData().getHomewok();
            if(homewokBean.getIsPraise()==0){
                isPraise = false;
                work_detailed_praise_btn.setActivated(false);
            }else{
                isPraise = true;
                work_detailed_praise_btn.setActivated(true);
            }
            if (workDetailedEntity.getData().getComments().getList().size() < 1){
                work_detailed_discuss.setVisibility(View.GONE);
                gengduo.setVisibility(View.GONE);
                fayan.setVisibility(View.VISIBLE);
            }else {
                work_detailed_discuss.setVisibility(View.VISIBLE);
                gengduo.setVisibility(View.VISIBLE);
                fayan.setVisibility(View.GONE);
            }

            rewardUserList.clear();
            rewardUserList.addAll(workDetailedEntity.getData().getRewardUserList());
            mAdapter.notifyDataSetChanged();
            initHeight(work_detailed_discuss);
//            设置头像
            Glide.with(this)
                    .load(workDetailedEntity.getData().getHomewok().getPhoto())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(new BitmapImageViewTarget(work_detailed_head_img){
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),resource);
                            bitmapDrawable.setCircular(true);
                            work_detailed_head_img.setImageDrawable(bitmapDrawable);
                        }
                    });
//            设置名字
            work_detailed_nick_name.setText(workDetailedEntity.getData().getHomewok().getNickname());
//            设置发表日期
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
            Date date = new Date(workDetailedEntity.getData().getHomewok().getCreateDate());
            work_detailed_time.setText(sdf.format(date));
//            设置发表类型
            work_detailed_ify.setText(workDetailedEntity.getData().getHomewok().getSource());
//            设置内容
            work_detailed_content.setText(workDetailedEntity.getData().getHomewok().getContent());
//            设置内容图片
            if (workDetailedEntity.getData().getHomewok().getPicProperty() != null && !"".equals(workDetailedEntity.getData().getHomewok().getPicProperty()))
            {
                String picProperty = workDetailedEntity.getData().getHomewok().getPicProperty();
                String[] split = picProperty.split("_");
                ViewGroup.LayoutParams layoutParams1 = work_detailed_content_img.getLayoutParams();
                layoutParams1.height = dip2px(this,Integer.parseInt(split[0]));
                layoutParams1.width = dip2px(this,Integer.parseInt(split[1]));
                work_detailed_content_img.setLayoutParams(layoutParams1);
            }
//            选择内容加载什么图片
            if ("图片".equals(workDetailedEntity.getData().getHomewok().getWorksType()))
                Glide.with(this).load(workDetailedEntity.getData().getHomewok().getCoverImg()).asBitmap().into(work_detailed_content_img);
            else    if ("音乐".equals(workDetailedEntity.getData().getHomewok().getWorksType()))
                work_detailed_content_img.setImageResource(R.mipmap.play_music_bg);
            else if ("视频".equals(workDetailedEntity.getData().getHomewok().getWorksType())){
                Glide.with(this).load(workDetailedEntity.getData().getHomewok().getCoverImg()).asBitmap().into(work_detailed_content_img);
            }
            Log.e("aaaaalalalala",workDetailedEntity.getData().getHomewok().getIsPraise()+"");
//            设置赞数量和状态
                work_detailed_praise_num.setText(workDetailedEntity.getData().getHomewok().getPraiseNum()+"");
                work_detailed_praise_num.setText(workDetailedEntity.getData().getHomewok().getPraiseNum()+"");
//            设置评论数量多少
            work_detailed_comment_num.setText(workDetailedEntity.getData().getHomewok().getCommentNum()+"");

                Map<String, String> map = new HashMap<>();
                map.put("page", 1 + "");
                map.put("rows", 20 + "");
                map.put("loginUserId", userId + "");
                map.put("homewokId", workDetailedEntity.getData().getHomewok().getId() + "");
                workDetailedPresenter.sendWorkDetailedComments("https://www.univstar.com/v1/m/homewok/comments", map);
        }
    }

    @Override
    public void getWorkDetailedComments(WorkDetailedCommentsEntity workDetailedCommentsEntity) {
        if (workDetailedCommentsEntity.getCode() == 0 || workDetailedCommentsEntity.getCode() == 2) {
            commentsList.clear();
            commentsList.add(workDetailedCommentsEntity.getData().getComments());

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getSendWorkDetailedComments(SendWorkDetailedCommentsEntity sendWorkDetailedCommentsEntity) {
        if (sendWorkDetailedCommentsEntity.getCode() == 0 || sendWorkDetailedCommentsEntity.getCode() == 2){
            Toast.makeText(this, sendWorkDetailedCommentsEntity.getMessage(), Toast.LENGTH_SHORT).show();
//            Map<String,String> map = new HashMap<>();
//            map.put("page",1+"");
//            map.put("rows",20+"");
//            map.put("loginUserId",userId+"");
//            map.put("homewokId",refid+"");
//            if (!"宝典".equals(type))
//                workDetailedPresenter.sendWorkDetailedComments("https://www.univstar.com/v1/m/homewok/comments/save",map);
//            else
//                workDetailedPresenter.sendWorkDetailedComments("https://www.univstar.com/v1/m/artcircle/comments/save",map);
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void initHeight(ListView listview){
        int height=0;
        View view=null;
        ListAdapter listAdapter=listview.getAdapter();
        if(listAdapter==null){
            return;
        }
        for(int i=0;i<listview.getCount();i++){
            view=listAdapter.getView(i,view,listview);
            view.measure(0,0);
            height+=view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params=listview.getLayoutParams();
        params.height=height+(listview.getDividerHeight()*(listview.getCount()-1));
        listview.setLayoutParams(params);
    }
    public void HideKeyboard(View v){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        InputMethodManager imm = ( InputMethodManager) v.getContext( ).getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow( v.getApplicationWindowToken() , 0 );
        }
    }

    @Override
    public void getFollowPraise(String msg) {
        if (msg.contains("成功")){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
    private void prasre(String url,int studentId,int attentionId,String type){
        SharedPreferences userState = getSharedPreferences("userState", 0);
        int userId = userState.getInt("loginUserId", 0);
        Log.e("userId",userId+"");
        Log.e("studentId",studentId+"");
        Log.e("attentionId",attentionId+"");
        Map<String,String> map = new HashMap<>();
        map.put("id",attentionId+"");
        map.put("loginUserId",userId+"");
        map.put("userId",studentId+"");
        map.put("type",type);
        followPraisePresenter.sendFollowPraise(url,map);
    }

    @Override
    public void getValuableDetailedData(ValuableDetailedEntity workDetailedEntity) {
        if (workDetailedEntity.getCode() == 0 && this != null){
            refid = workDetailedEntity.getData().getArtcircle().getId();
            artcircleBean = workDetailedEntity.getData().getArtcircle();
            if(artcircleBean.getIsPraise()==0){
                isPraise = false;
                work_detailed_praise_btn.setActivated(false);
            }else{
                isPraise = true;
                work_detailed_praise_btn.setActivated(true);
            }
            if (workDetailedEntity.getData().getComments().getList().size() < 1){
                work_detailed_discuss.setVisibility(View.GONE);
                gengduo.setVisibility(View.GONE);
                fayan.setVisibility(View.VISIBLE);
            }else {
                work_detailed_discuss.setVisibility(View.VISIBLE);
                gengduo.setVisibility(View.VISIBLE);
                fayan.setVisibility(View.GONE);
            }

//            rewardUserList.clear();
//            rewardUserList.addAll(workDetailedEntity.getData().getRewardUserList());
//            mAdapter.notifyDataSetChanged();
            initHeight(work_detailed_discuss);
//            设置头像
            Glide.with(App.context)
                    .load(workDetailedEntity.getData().getArtcircle().getPhoto())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(new BitmapImageViewTarget(work_detailed_head_img){
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),resource);
                            bitmapDrawable.setCircular(true);
                            work_detailed_head_img.setImageDrawable(bitmapDrawable);
                        }
                    });
//            设置名字
            work_detailed_nick_name.setText(workDetailedEntity.getData().getArtcircle().getNickname());
//            设置发表日期
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
            Date date = new Date(workDetailedEntity.getData().getArtcircle().getCreateDate());
            work_detailed_time.setText(sdf.format(date));
//            设置发表类型
            work_detailed_ify.setText(workDetailedEntity.getData().getArtcircle().getContentType());
//            设置内容
            work_detailed_content.setText(workDetailedEntity.getData().getArtcircle().getContent());
//            设置内容图片
            if (workDetailedEntity.getData().getArtcircle().getPicProperty() != null && !"".equals(workDetailedEntity.getData().getArtcircle().getPicProperty()))
            {
                String picProperty = workDetailedEntity.getData().getArtcircle().getPicProperty();
                String[] split = picProperty.split("_");
                ViewGroup.LayoutParams layoutParams1 = work_detailed_content_img.getLayoutParams();
                layoutParams1.height = dip2px(this,Integer.parseInt(split[0]));
                layoutParams1.width = dip2px(this,Integer.parseInt(split[1]));
                work_detailed_content_img.setLayoutParams(layoutParams1);
            }
//            选择内容加载什么图片
            if ("图片".equals(workDetailedEntity.getData().getArtcircle().getWorksType()))
                Glide.with(this).load(workDetailedEntity.getData().getArtcircle().getCoverImg()).asBitmap().into(work_detailed_content_img);
            else    if ("音乐".equals(workDetailedEntity.getData().getArtcircle().getWorksType()))
                work_detailed_content_img.setImageResource(R.mipmap.play_music_bg);
            else if ("视频".equals(workDetailedEntity.getData().getArtcircle().getWorksType())){
                Glide.with(this).load(workDetailedEntity.getData().getArtcircle().getCoverImg()).asBitmap().into(work_detailed_content_img);
            }
            Log.e("aaaaalalalala",workDetailedEntity.getData().getArtcircle().getIsPraise()+"");
//            设置赞数量和状态
            work_detailed_praise_num.setText(workDetailedEntity.getData().getArtcircle().getPraiseNum()+"");
            work_detailed_praise_num.setText(workDetailedEntity.getData().getArtcircle().getPraiseNum()+"");
//            设置评论数量多少
            work_detailed_comment_num.setText(workDetailedEntity.getData().getArtcircle().getCommentNum()+"");

                Map<String,String> map = new HashMap<>();
                map.put("page",1+"");
                map.put("rows",20+"");
                map.put("loginUserId",userId+"");
                map.put("artcircleId",workDetailedEntity.getData().getArtcircle().getId()+"");
                valuableDetailedPresenter.sendValuableDetailedComments("https://www.univstar.com/v1/m/artcircle/comments", map);
        }
    }

    @Override
    public void getValuableDetailedComments(ValuableDetailedCommentsEntity workDetailedCommentsEntity) {
        if (workDetailedCommentsEntity.getCode() == 0 && workDetailedCommentsEntity.getData().getComments().getList().size() > 0) {
            valuableCommentsList.clear();
            valuableCommentsList.add(workDetailedCommentsEntity.getData().getComments());
            valuableDetailedCommentsAdapter = new ValuableDetailedCommentsAdapter(valuableCommentsList,WorkDetailedActivity.this);
            valuableDetailedCommentsAdapter.setOnClickItem(new ValuableDetailedCommentsAdapter.OnClickItem() {
                @Override
                public void onClickItem(View view, boolean isPraise, int position) {
                    CheckBox checkBox = view.findViewById(R.id.comment_listitem_praise_cb);
                    if (checkBox.isChecked()) {
                        prasre("https://www.univstar.com/v1/m/user/praise", valuableCommentsList.get(0).getList().get(position).getUserId(),valuableCommentsList.get(0).getList().get(position).getId(), "艺考圈评论");

                        valuableCommentsList.get(0).getList().get(position).setPraiseNum(valuableCommentsList.get(0).getList().get(position).getPraiseNum() + 1);
                        checkBox.setText(valuableCommentsList.get(0).getList().get(position).getPraiseNum() + "");
                    } else {
                        prasre("https://www.univstar.com/v1/m/user/praise/cancel", valuableCommentsList.get(0).getList().get(position).getUserId(),valuableCommentsList.get(0).getList().get(position).getId(), "艺考圈评论");
                        if (valuableCommentsList.get(0).getList().get(position).getPraiseNum() == 0) {
                            checkBox.setText(valuableCommentsList.get(0).getList().get(position).getPraiseNum() + "");
                        } else {
                            valuableCommentsList.get(0).getList().get(position).setPraiseNum(valuableCommentsList.get(0).getList().get(position).getPraiseNum() - 1);
                            checkBox.setText(valuableCommentsList.get(0).getList().get(position).getPraiseNum() + "");
                        }


                    }
                }
            });
            work_detailed_discuss.setAdapter(valuableDetailedCommentsAdapter);
            adapter.notifyDataSetChanged();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getSendValuableDetailedComments(SendValuableDetailedCommentsEntity sendWorkDetailedCommentsEntity) {
        if (sendWorkDetailedCommentsEntity.getCode() == 0 || sendWorkDetailedCommentsEntity.getCode() == 2){
            Toast.makeText(this, sendWorkDetailedCommentsEntity.getMessage(), Toast.LENGTH_SHORT).show();
            Map<String,String> map = new HashMap<>();
            map.put("page",1+"");
            map.put("rows",20+"");
            map.put("loginUserId",userId+"");
            map.put("homewokId",refid+"");
            valuableDetailedPresenter.sendValuableDetailedComments("https://www.univstar.com/v1/m/artcircle/comments",map);
        }
    }
}
