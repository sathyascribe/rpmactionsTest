package com.inventica.rpmapp.ui.activity.connection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter
        extends FragmentPagerAdapter {

    private MyListUserModel myListUserModel;
    public ViewPagerAdapter(FragmentManager fm, MyListUserModel myListUserModel) {
        super(fm);
        this.myListUserModel = myListUserModel;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        bundle.putParcelable("Object",myListUserModel);

        if (position == 0)
        {

            fragment = new UserInfoFragment();
            fragment.setArguments(bundle);


        }
        else if (position == 1)
        {
           fragment = new UserAchievementFragment();
            fragment.setArguments(bundle);
        }

        return fragment;

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Basic Details";
        }
        else if (position == 1)
        {
            title = "Achievements";
        }

        return title;
    }
}