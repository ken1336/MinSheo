package applicaton.android.com.sehonmin.ui.core;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import applicaton.android.com.sehonmin.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ThirdListFragment extends Fragment {


    public ThirdListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third_list, container, false);
    }

}
