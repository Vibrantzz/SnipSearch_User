package com.example.vibrantzz3.snipsearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vibrantzz3.snipsearch.CancelledFragment;
import com.example.vibrantzz3.snipsearch.PassedFragment;
import com.example.vibrantzz3.snipsearch.UpcomingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vibrantzz3 on 2/5/2018.
 */

public class AppointmentViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> FragmentList= new ArrayList<>();
    private final List<String> FragmentListTitles= new ArrayList<>();
    private final Bundle fragmentBundle;


    public AppointmentViewPagerAdapter(FragmentManager fm, Bundle data) {
        super(fm);
        fragmentBundle = data;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                UpcomingFragment f1 = new UpcomingFragment();
                // Pass extra values using bundle extras as arguments.
                f1.setArguments(this.fragmentBundle);
                return f1;
            case 1:
                CancelledFragment f2 = new CancelledFragment();
                // Pass extra values using bundle extras as arguments.
                f2.setArguments(this.fragmentBundle);
                return f2;
            case 2:
                PassedFragment f3 = new PassedFragment();
                // Pass extra values using bundle extras as arguments.
                f3.setArguments(this.fragmentBundle);
                return f3;
            default:
                return new UpcomingFragment();
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
