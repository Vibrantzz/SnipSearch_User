package com.example.vibrantzz3.snipsearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vibrantzz3 on 2/5/2018.
 */

public class GalleryViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> FragmentList= new ArrayList<>();
    private final List<String> FragmentListTitles= new ArrayList<>();
    private final Bundle fragmentBundle;


    public GalleryViewPagerAdapter(FragmentManager fm, Bundle data) {
        super(fm);
        fragmentBundle = data;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                OwnPicsFragment tl = new OwnPicsFragment();
                // Pass extra values using bundle extras as arguments.
                tl.setArguments(this.fragmentBundle);
                return tl;
            case 1:
                // Display second fragment
                UserPicsFragment up = new UserPicsFragment();
                // Pass extra values using bundle extras as arguments.
                up.setArguments(this.fragmentBundle);
                return up;
            default:
                return new MenuFragment();
        }
    }
    @Override
    public int getCount() {
        return FragmentListTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListTitles.get(position);
    }

    public void AddFragment(Fragment fragment, String Titles) {

        FragmentList.add(fragment);
        FragmentListTitles.add(Titles);

    }
}
