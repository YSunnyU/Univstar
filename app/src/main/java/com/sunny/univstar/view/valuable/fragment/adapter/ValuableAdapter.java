package com.sunny.univstar.view.valuable.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sunny.univstar.view.valuable.fragment.ValuableSonFragment;

import java.util.List;

/**
 * Created by 张玗 on 2018/5/6.
 */

public class ValuableAdapter extends FragmentPagerAdapter {
    private List<String> title_list;

    private List<ValuableSonFragment> son_frag;

    public ValuableAdapter(FragmentManager fm, List<String> title_list, List<ValuableSonFragment> son_frag) {
        super(fm);
        this.title_list = title_list;

        this.son_frag = son_frag;
    }

    @Override
    public Fragment getItem(int position) {
        return son_frag.get(position);
    }

    @Override
    public int getCount() {
        return son_frag.isEmpty()?0:son_frag.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title_list.get(position);
    }
}
