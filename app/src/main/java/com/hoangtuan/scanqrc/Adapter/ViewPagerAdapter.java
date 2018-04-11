package com.hoangtuan.scanqrc.Adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.hoangtuan.scanqrc.Fragment.ControlFragment;

import java.util.ArrayList;

/**
 * Created by atbic on 30/3/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<ControlFragment> fragments = new ArrayList<>();
    private ControlFragment currentFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments.clear();
        fragments.add(ControlFragment.newInstance(0));
        fragments.add(ControlFragment.newInstance(1));
        fragments.add(ControlFragment.newInstance(2));
        fragments.add(ControlFragment.newInstance(3));
        fragments.add(ControlFragment.newInstance(4));

    }

    @Override
    public ControlFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((ControlFragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    public ControlFragment getCurrentFragment() {
        return currentFragment;
    }
}
