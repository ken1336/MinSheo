package applicaton.android.com.sehonmin.ui.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

import applicaton.android.com.sehonmin.data.util.PhoneBookManager;
import applicaton.android.com.sehonmin.ui.core.FirstFragment;
import applicaton.android.com.sehonmin.ui.core.SecondFragment;
import applicaton.android.com.sehonmin.ui.core.ThirdFragment;

/**
 * Created by ken13 on 2017-12-02.
 */

public class MinPagerAdapter extends FragmentStatePagerAdapter {

    public MinPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public ListFragment getItem(int position)
    {
        ListFragment listFragment;
        switch(position) {
            case 0:
                listFragment =  new FirstFragment();
                listFragment.setListAdapter(PhoneBookManager.getInstance().getPhoneNumberAdapter());
                break;
            case 1:
                listFragment =  new SecondFragment();
                //listFragment.setListAdapter(PhoneBookManager.getInstance().getPhoneNumberAdapter());
                break;
            case 2:
                listFragment =  new ThirdFragment();
                //listFragment.setListAdapter(PhoneBookManager.getInstance().getPhoneNumberAdapter());
                break;
            default:
                return null;
        }
        return listFragment;
    }

    @Override
    public int getCount()
    {
        return 3;
    }
}