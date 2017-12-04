package applicaton.android.com.sehonmin.ui.core;

import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

/*
* Modified by Park on 2017-12-03 19:28
* */

public class SecondFragment extends ListFragment {

    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        String strText = (String) l.getItemAtPosition(position) ;
    }

}
