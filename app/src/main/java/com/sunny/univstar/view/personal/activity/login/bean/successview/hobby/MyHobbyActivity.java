package com.sunny.univstar.view.personal.activity.login.bean.successview.hobby;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.PreferenceContract;
import com.sunny.univstar.presenter.PreferencePresenter;
import com.sunny.univstar.view.preference.PreferenceAboveBean;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MyHobbyActivity extends BaseActivity implements PreferenceContract.PreferenceInView, MajorAdapter.ItemSelectCallBack, CollegeAdapter.ItemSelectCallBack {
    PreferenceContract.PreferenceInPresenter preferenceInPresenter;
    /*@Bind(R.id.myHobby_back)
    TextView myHobbyBack;
    @Bind(R.id.myHobby_majorRecyView)
    RecyclerView myHobbyMajorRecyView;
    @Bind(R.id.myHobby_CollegeRecyView)
    RecyclerView myHobbyCollegeRecyView;
    @Bind(R.id.myHobby_keepBtn)
    Button myHobbyKeepBtn;*/
    public List<String> majorNums;
    @Bind(R.id.myHobby_back)
    TextView myHobbyBack;

    @Bind(R.id.myHobby_majorRecyView)
    RecyclerView myHobbyMajorRecyView;
    @Bind(R.id.myHobby_CollegeRecyView)
    RecyclerView myHobbyCollegeRecyView;
    @Bind(R.id.myHobby_keepBtn)
    Button myHobbyKeepBtn;

    @OnClick({R.id.myHobby_back, R.id.myHobby_keepBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myHobby_back:
                finish();
                break;
            case R.id.myHobby_keepBtn:
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_hobby;
    }

    @Override
    protected void init() {
        preferenceInPresenter = new PreferencePresenter(this);
        preferenceInPresenter.sendPreferenceData();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showPreferenceData(PreferenceAboveBean preferenceAboveBean) {
//        得到学校集合
        List<PreferenceAboveBean.DataBean.CollegesBean> colleges = preferenceAboveBean.getData().getColleges();
//        得到专业集合
        List<PreferenceAboveBean.DataBean.MajorsBean> majors = preferenceAboveBean.getData().getMajors();
        //        配置专业集合
        setMajorUI(majors);
        //    配置学校数据
        setCollegesUI(colleges);

        Log.d("MyHobbyActivity", "colleges.size():" + colleges.size());
        Log.d("MyHobbyActivity", "majors.size():" + majors.size());
    }
//        配置专业集合

    private void setMajorUI(List<PreferenceAboveBean.DataBean.MajorsBean> majors) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        myHobbyMajorRecyView.setLayoutManager(gridLayoutManager);
        MajorAdapter majorAdapter = new MajorAdapter(MyHobbyActivity.this, majors, this);
        myHobbyMajorRecyView.setAdapter(majorAdapter);
    }

    //    配置学校数据
    private void setCollegesUI(List<PreferenceAboveBean.DataBean.CollegesBean> colleges) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        myHobbyCollegeRecyView.setLayoutManager(gridLayoutManager);
        CollegeAdapter collegeAdapter = new CollegeAdapter(colleges, MyHobbyActivity.this);
        myHobbyCollegeRecyView.setAdapter(collegeAdapter);
    }

    @Override
    public void onItemSelect(List<String> selectList) {
        majorNums = selectList;
        showNum();

    }

    //
    private void showNum() {

        int num = 0;
        if (majorNums != null) {

            num += majorNums.size();
        }

    /*if(schoolnums!=null){

        num += schoolnums.size();
    }

    if(num<=0){

        usermajor_select_aty_num.setText("请选择");
    }
    else{

        usermajor_select_aty_num.setText(num+"");
    }*/
    }


}
