package com.sunny.univstar.view.teachertype.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;
import com.sunny.univstar.contract.FindTeacherContract;
import com.sunny.univstar.model.entity.FindTeacherEntity;
import com.sunny.univstar.presenter.FindTeacherPresenter;
import com.sunny.univstar.view.teachertype.adapter.FindTeacherAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class FindTeacherActivity extends BaseActivity implements FindTeacherContract.FindTeacherView{


    @Bind(R.id.find_teacher_return)
    ImageView findTeacherReturn;
    @Bind(R.id.find_great_teacher_text)
    TextView findGreatTeacherText;
    @Bind(R.id.find_great_teacher_line)
    TextView findGreatTeacherLine;
    @Bind(R.id.find_great_teacher)
    RelativeLayout findGreatTeacher;
    @Bind(R.id.find_famous_teacher_text)
    TextView findFamousTeacherText;
    @Bind(R.id.find_famous_teacher_line)
    TextView findFamousTeacherLine;
    @Bind(R.id.find_famous_teacher)
    RelativeLayout findFamousTeacher;
    @Bind(R.id.find_daren_teacher_text)
    TextView findDarenTeacherText;
    @Bind(R.id.find_daren_teacher_line)
    TextView findDarenTeacherLine;
    @Bind(R.id.find_daren_teacher)
    RelativeLayout findDarenTeacher;
    @Bind(R.id.find_teacher_list)
    RecyclerView findTeacherList;

    //达人
    private static final String SORT_TYPE_DAREN = "2";
    //名师
    private static final String SORT_TYPE_MINGSHI = "3";
    //大师
    private static final String SORT_TYPE_DASHI = "4";

    private FindTeacherContract.FindTeacherPresneter findTeacherPresneter;

    private FindTeacherAdapter adapter;

    private List<FindTeacherEntity> mList;
    private int userId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_teacher;
    }

    @Override
    protected void init() {
        mList = new ArrayList<>();
        SharedPreferences userState = getSharedPreferences("userState", 0);
        userId = userState.getInt("loginUserId", 0);
        findTeacherPresneter = new FindTeacherPresenter(this);
        Map<String,String> map = new HashMap<>();
        map.put("loginUserId", userId+"");
        map.put("userType",SORT_TYPE_DASHI);
        map.put("page",1+"");
        findTeacherPresneter.sendFindTeacher(map);
        findTeacherList.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new FindTeacherAdapter(mList);
        adapter.setOnClickItem(new FindTeacherAdapter.OnClickItem() {
            @Override
            public void onClickItem(View view, int position) {
                Intent intent = new Intent(FindTeacherActivity.this,FindTeacherDetailsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("teacherId",mList.get(0).getData().getList().get(position));
//                intent.putExtras(bundle);
                int id68 = mList.get(0).getData().getList().get(position).get_$Id68();
                intent.putExtra("teacherId",mList.get(0).getData().getList().get(position).get_$Id68()+"");
                startActivity(intent);
            }
        });
        findTeacherList.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.find_teacher_return, R.id.find_great_teacher, R.id.find_famous_teacher, R.id.find_daren_teacher})
    public void onViewClicked(View view) {
        Map<String,String> map = new HashMap<>();
        switch (view.getId()) {
//            返回按钮
            case R.id.find_teacher_return:
                finish();
                break;
//            大师
            case R.id.find_great_teacher:
                map.clear();
                map.put("loginUserId",userId + "");
                map.put("userType",SORT_TYPE_DASHI);
                map.put("page",1+"");
                findTeacherPresneter.sendFindTeacher(map);
                findGreatTeacherLine.setVisibility(View.VISIBLE);
                findFamousTeacherLine.setVisibility(View.GONE);
                findDarenTeacherLine.setVisibility(View.GONE);
                break;
//            名师
            case R.id.find_famous_teacher:
                map.clear();
                map.put("loginUserId",userId + "");
                map.put("userType",SORT_TYPE_MINGSHI);
                map.put("page",1+"");
                findTeacherPresneter.sendFindTeacher(map);
                findGreatTeacherLine.setVisibility(View.GONE);
                findFamousTeacherLine.setVisibility(View.VISIBLE);
                findDarenTeacherLine.setVisibility(View.GONE);
                break;
//            达人
            case R.id.find_daren_teacher:
                map.clear();
                map.put("loginUserId",userId + "");
                map.put("userType",SORT_TYPE_DAREN);
                map.put("page",1+"");
                findTeacherPresneter.sendFindTeacher(map);
                findGreatTeacherLine.setVisibility(View.GONE);
                findFamousTeacherLine.setVisibility(View.GONE);
                findDarenTeacherLine.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void getFindTeacherData(FindTeacherEntity findTeacherEntity) {
        mList.clear();
        mList.add(findTeacherEntity);
        adapter.notifyDataSetChanged();
    }
}
