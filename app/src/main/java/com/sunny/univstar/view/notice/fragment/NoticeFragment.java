package com.sunny.univstar.view.notice.fragment;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;
import com.sunny.univstar.contract.HomeNoticeContract;
import com.sunny.univstar.model.entity.HomeNoticeEntity;
import com.sunny.univstar.presenter.HomeNoticePresenter;
import com.sunny.univstar.view.notice.activity.NoticeDetailedActivity;
import com.sunny.univstar.view.notice.adapter.HomeNoticeAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends BaseFragment implements HomeNoticeContract.HomeNoticeView, View.OnClickListener {


    private TextView time_bolting;
    private RecyclerView notice_list;
    private PullRefreshLayout notice_pullRefresh;
    private HomeNoticeContract.HomeNoticePresenter homeNoticePresenter;
    private List<HomeNoticeEntity.DataBean.ListBean> listData;
    private List<HomeNoticeEntity.DataBean> list;
    private HomeNoticeAdapter adapter;
    private boolean isTimeFilter = true;
    private PopupWindow popupWindow;
    private TextView startText;
    private TextView endText;
    private Calendar calendar;
    private LinearLayout net_work_bg;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void init() {
        calendar = Calendar.getInstance();
        listData = new ArrayList<>();
        list = new ArrayList<>();
        homeNoticePresenter = new HomeNoticePresenter(this);
//        实例化预告
        net_work_bg = getView().findViewById(R.id.net_work_bg);
        time_bolting = getView().findViewById(R.id.time_bolting);
        time_bolting.setOnClickListener(this);
        notice_list = getView().findViewById(R.id.notice_list);
        notice_pullRefresh = getView().findViewById(R.id.notice_pullRefresh);
//        notice_pullRefresh.setBackgroundColor(Color.parseColor("#00000000"));
        initTimeFilterPopup();
        adapter = new HomeNoticeAdapter(listData);
        adapter.setOnClickItem(new HomeNoticeAdapter.OnClickItem() {
            @Override
            public void onClickItem(View view, int position) {
                Intent intent = new Intent(getContext(), NoticeDetailedActivity.class);
                intent.putExtra("id",listData.get(position).getId()+"");
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        notice_list.setLayoutManager(linearLayoutManager);
        notice_list.setAdapter(adapter);
//        请求网络数据所需参数
        Map<String, String> map = new HashMap<>();
//        页数
        map.put("page", "1");
//        每页数据
        map.put("rows", "2");
//        用户ID
        map.put("loginUserId", "0");
//        开课时间
        map.put("startDate", "");
//        结课时间
        map.put("endDate", "");
        homeNoticePresenter.sendHomeNotice(map);

    }

    @Override
    protected void initData() {
        notice_pullRefresh.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                notice_pullRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notice_pullRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
//        homeMasterFragmentSwipe.setRefreshing(false);
        notice_pullRefresh.setRefreshStyle(PullRefreshLayout.STYLE_CIRCLES);
    }


    @Override
    public void getHomeNoticeData(HomeNoticeEntity homeNoticeEntity) {
        if (homeNoticeEntity.getData().getList().size() > 0 && homeNoticeEntity.getData().getList() != null) {
            listData.clear();
            listData.addAll(homeNoticeEntity.getData().getList());
            list.clear();
            list.add(homeNoticeEntity.getData());
            adapter.notifyDataSetChanged();
            notice_pullRefresh.setVisibility(View.VISIBLE);
            net_work_bg.setVisibility(View.GONE);
        } else {
            notice_pullRefresh.setVisibility(View.GONE);
            net_work_bg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time_bolting:
                if (isTimeFilter) {
//                    notice_pullRefresh.setAlpha(0.5);
                    notice_pullRefresh.setAlpha(0.4f);
                    time_bolting.setText("取消");
                    isTimeFilter = !isTimeFilter;
                    popupWindow.showAsDropDown(time_bolting, 0, 0, Gravity.BOTTOM);
                } else {
                    notice_pullRefresh.setAlpha(1);
                    time_bolting.setText("时间筛选");
                    isTimeFilter = !isTimeFilter;
                    popupWindow.dismiss();
                }
                break;
//            开课时间
            case R.id.notice_start:
                showDatePickerDialog(getActivity(), Color.parseColor("#00AEFF"), startText, calendar);
                break;
//            结束时间
            case R.id.notice_end:
                showDatePickerDialog(getActivity(), Color.parseColor("#00AEFF"), endText, calendar);
                break;
//            重置
            case R.id.notice_time_resetting:
                startText.setText("");
                endText.setText("");
                popupWindow.dismiss();
                break;
//            确认
            case R.id.notice_time_yes:
                Map<String, String> map = new HashMap<>();
//        页数
                map.put("page", "1");
//        每页数据
                map.put("rows", "10");
//        用户ID
                SharedPreferences userState = getActivity().getSharedPreferences("userState", 0);
                int userId = userState.getInt("loginUserId", 0);
                map.put("loginUserId", userId+"");
//        开课时间
                map.put("startDate", startText.getText().toString());
//        结课时间
                map.put("endDate", endText.getText().toString());
                homeNoticePresenter.sendHomeNotice(map);
                popupWindow.dismiss();
                break;
        }
    }

    private void initTimeFilterPopup() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.time_filter_item, null);
        LinearLayout notice_start = view.findViewById(R.id.notice_start);
        notice_start.setOnClickListener(this);
        LinearLayout notice_end = view.findViewById(R.id.notice_end);
        notice_end.setOnClickListener(this);
        Button notice_time_resetting = view.findViewById(R.id.notice_time_resetting);
        notice_time_resetting.setOnClickListener(this);
        Button notice_time_yes = view.findViewById(R.id.notice_time_yes);
        notice_time_yes.setOnClickListener(this);
        startText = view.findViewById(R.id.notice_start_text);
        endText = view.findViewById(R.id.notice_end_text);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                notice_pullRefresh.setAlpha(1);
                time_bolting.setText("时间筛选");
                isTimeFilter = !isTimeFilter;
            }
        });
    }

    public void showDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
