package com.example.johnpconsidine.bengalworkouts.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class MyPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "FAVORITES";

            case 1:
                return "ARMS";
            case 2:
                return "LEGS";
            case 3:
                return "CORE";
            case 4:
                return "FULL BODY";
            default:
                return "";
        }
    }


        @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }
    @Override
    public int getCount() {
        return this.fragments.size();
    }
}
