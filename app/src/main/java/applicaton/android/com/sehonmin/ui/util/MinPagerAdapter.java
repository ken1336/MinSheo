package applicaton.android.com.sehonmin.ui.util;

import android.support.v4.app.FragmentStatePagerAdapter;

import applicaton.android.com.sehonmin.ui.core.FirstFragment;
import applicaton.android.com.sehonmin.ui.core.SecondFragment;
import applicaton.android.com.sehonmin.ui.core.ThirdFragment;

/**
 * Created by ken13 on 2017-12-02.
 */

public class MinPagerAdapter extends FragmentStatePagerAdapter{
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
                return FirstFragment.getInstance();
            case 1:
                return SecondFragment.getInstance();
            case 2:
                return ThirdFragment.getInstance();
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