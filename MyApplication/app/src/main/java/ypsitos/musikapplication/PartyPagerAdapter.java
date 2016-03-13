package ypsitos.musikapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by David on 3/12/16.
 */
public class PartyPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PartyPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PlaylistFragment tab1 = new PlaylistFragment();
                return tab1;
            case 1:
                PeopleFragment tab2 = new PeopleFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
