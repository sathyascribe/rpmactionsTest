package com.inventica.rpmapp.ui.adapter;

/**
 */
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.inventica.rpmapp.ui.activity.connection.MyContactFragment;
import com.inventica.rpmapp.ui.activity.connection.InviteConnectionFromSocialMedia;
import com.inventica.rpmapp.ui.activity.connection.RPMUserListFragment;

public class ConnectionAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ConnectionAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                RPMUserListFragment tab = new RPMUserListFragment();
                return tab;
            case 1:
                MyContactFragment tab1 = new MyContactFragment();
                return tab1;

            case 2:
                InviteConnectionFromSocialMedia tab3 = new InviteConnectionFromSocialMedia();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}