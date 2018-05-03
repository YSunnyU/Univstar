package com.sunny.univstar.view.preference;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by 张玗 on 2018/5/3.
 */

public class PreferenceActivity extends BaseActivity {
    @Bind(R.id.presenter_back)
    ImageView presenterBack;
    @Bind(R.id.presenter_skeep)
    TextView presenterSkeep;
    @Bind(R.id.majorList)
    ListView majorList;
    @Bind(R.id.schoolList)
    ListView schoolList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preference;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }


}
