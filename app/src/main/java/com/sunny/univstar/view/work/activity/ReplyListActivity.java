package com.sunny.univstar.view.work.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.FollowPraiseContract;
import com.sunny.univstar.contract.ReplyListContract;
import com.sunny.univstar.model.entity.ReplyListEntity;
import com.sunny.univstar.model.entity.ReplyListSendEntity;
import com.sunny.univstar.presenter.FollowPraiisePresenter;
import com.sunny.univstar.presenter.ReplyListPresenter;
import com.sunny.univstar.view.work.adapter.ReplyListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class ReplyListActivity extends BaseActivity implements ReplyListContract.ReplyListView,FollowPraiseContract.FollowPraiseView {


    @Bind(R.id.reply_list_return)
    ImageView replyListReturn;
    @Bind(R.id.reply_list_title)
    TextView replyListTitle;
    @Bind(R.id.reply_list)
    RecyclerView replyList;
    @Bind(R.id.work_detailed_pinglun_send)
    Button workDetailedPinglunSend;
    @Bind(R.id.work_detailed_pinglun_input)
    EditText workDetailedPinglunInput;
    private ReplyListContract.ReplyListPresenter replyListPresenter;
    private FollowPraiseContract.FollowPraisePresenter followPraisePresenter;
    private List<ReplyListEntity.DataBean> mList;
    private ReplyListAdapter mAdapter;
    private String id;
    private String toContent;
    private String toId;
    private int loginUserId;
    private String workId;
    private String replyId;
    private int posititon = - 1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_reply_list;
    }

    @Override
    protected void init() {
        mList = new ArrayList<>();
        followPraisePresenter = new FollowPraiisePresenter(this);
        replyListPresenter = new ReplyListPresenter(this);
        mAdapter = new ReplyListAdapter(mList);
        mAdapter.setOnClickItem(new ReplyListAdapter.OnClickItem() {
            @Override
            public void onClickItem(View view, int position) {
                switch (view.getId()){
                    case R.id.reply_list_aite:
                        replyId = mList.get(0).getComments().getList().get(position).getUserId()+"";
                        showSoftInputFromWindow(ReplyListActivity.this,workDetailedPinglunInput);
                        posititon = position;
                        workDetailedPinglunInput.setText("@"+mList.get(0).getComments().getList().get(position).getNickname());
                        break;
                    case R.id.reply_list_praise:
                        CheckBox checkBox = view.findViewById(R.id.reply_list_praise);
                            if (checkBox.isChecked()) {
                                mList.get(0).getComments().getList().get(position).setPraiseNum(mList.get(0).getComments().getList().get(position).getPraiseNum() + 1);
                                checkBox.setText(mList.get(0).getComments().getList().get(position).getPraiseNum() + "");
                                sendPrice("https://www.univstar.com/v1/m/user/praise", mList.get(0).getComments().getList().get(position).getUserId(), mList.get(0).getComments().getList().get(position).getId());
                            } else {
                                if (mList.get(0).getComments().getList().get(position).getPraiseNum() == 0) {
                                    checkBox.setText(mList.get(0).getComments().getList().get(position).getPraiseNum() + "");
                                } else {
                                    mList.get(0).getComments().getList().get(position).setPraiseNum(mList.get(0).getComments().getList().get(position).getPraiseNum() - 1);
                                    checkBox.setText(mList.get(0).getComments().getList().get(position).getPraiseNum() + "");
                                }
                                sendPrice("https://www.univstar.com/v1/m/user/praise/cancel", mList.get(0).getComments().getList().get(position).getUserId(), mList.get(0).getComments().getList().get(position).getId());
                            }
                        break;
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        replyList.setLayoutManager(linearLayoutManager);
        replyList.setAdapter(mAdapter);
        SharedPreferences userState = getSharedPreferences("userState", 0);
        loginUserId = userState.getInt("loginUserId", 0);
        Intent intent = getIntent();
        id = intent.getStringExtra("pid");
        workId = intent.getStringExtra("workId");
        toContent = intent.getStringExtra("toContent");
        toId = intent.getStringExtra("toId");
        Map<String, String> map = new HashMap<>();
        map.put("loginUserId", loginUserId + "");
        map.put("homewokId", workId + "");
        map.put("commentsId", id + "");
        replyListPresenter.sendReplyList(map);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void getReplyListData(ReplyListEntity replyListEntity) {
        if (replyListEntity.getCode() == 0) {
            mList.clear();
            mList.add(replyListEntity.getData());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getReplyListSendData(ReplyListSendEntity replyListSendEntity) {
        Map<String, String> map = new HashMap<>();
        map.put("loginUserId", loginUserId + "");
        map.put("homewokId", workId + "");
        map.put("commentsId", id + "");
        replyListPresenter.sendReplyList(map);
    }

    @OnClick({R.id.work_detailed_pinglun_send, R.id.work_detailed_pinglun_input,R.id.reply_list_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.work_detailed_pinglun_send:
                Map<String,String> map = new HashMap<>();
                String replace = "";
                if (posititon != -1) {
                    String s = workDetailedPinglunInput.getText().toString();
                    replace = s.replace("@" + mList.get(0).getComments().getList().get(posititon).getNickname(), "");
                }else {
                    replace = workDetailedPinglunInput.getText().toString();
                }
                map.put("pid", id +"");//id
                map.put("userId",loginUserId+"");//用户ID
                map.put("content",replace);//用户评论内容
                map.put("toId", toId +"");//原评论人ID
                map.put("toContent", toContent +"");//原评论内容
                map.put("refId", workId +"");//id
                map.put("replyId",replyId+"");//@用户的Id
                workDetailedPinglunInput.setText("");
                replyListPresenter.sendReplyListSend(map);
                break;
            case R.id.work_detailed_pinglun_input:
                break;
            case R.id.reply_list_return:
                finish();
                break;
        }
    }
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @Override
    public void getFollowPraise(String msg) {

    }

    private void sendPrice(String url,int userId,int id){
        Map<String,String> map = new HashMap<>();
        map.put("id",id+"");
        map.put("loginUserId",loginUserId+"");
        map.put("userId",userId+"");
        map.put("type","作业回复");
        followPraisePresenter.sendFollowPraise(url,map);
    }
}
