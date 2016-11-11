package com.vivere.app.vivere;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Giorgos on 11/11/2016.
 */

public class myPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public myPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AppointmentsFragment appointmentsFragment = new AppointmentsFragment();
                return appointmentsFragment;
            case 1:
                MedicationFragment tab2 = new MedicationFragment();
                return tab2;
            case 2:
                ExamsFragment tab3 = new ExamsFragment();
                return tab3;
            case 3:
                HistoryFragment historyFragment = new HistoryFragment();
                return historyFragment;
            case 4:
                HabitsFragment habitsFragment = new HabitsFragment();
                return habitsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
