package applicaton.android.com.sehonmin.ui.util;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

import applicaton.android.com.sehonmin.data.util.PhoneBookManager;
import applicaton.android.com.sehonmin.ui.core.FirstFragment;
import applicaton.android.com.sehonmin.ui.core.SecondListFragment;
import applicaton.android.com.sehonmin.ui.core.ThirdListFragment;

/**
 * Created by ken13 on 2017-12-02.
 * Modified by Park on 2017-12-03 19:28
 */

public class MinPagerAdapter extends FragmentStatePagerAdapter
{
    public MinPagerAdapter(android.support.v4.app.FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public android.support.v4.app.Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                ListFragment listFragment =  new FirstFragment();
                listFragment.setListAdapter(PhoneBookManager.getInstance().getPhoneNumberAdapter());
                return listFragment;

            case 1:
                return new SecondListFragment();
            case 2:
                return new ThirdListFragment();
            default:
                return null;
        }
    }
    @Override
    public int getCount()
    {
        return 3;
    }
}
