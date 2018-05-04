package com.sunny.univstar.view.notice.fragment;


import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.sunny.univstar.R;
import com.sunny.univstar.base.BaseFragment;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends BaseFragment {


    @Bind(R.id.texy)
    TextView texy;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }*/


}
