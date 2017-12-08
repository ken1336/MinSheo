package applicaton.android.com.sehonmin.ui.core;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import applicaton.android.com.sehonmin.Model.service.ResultManager;
import applicaton.android.com.sehonmin.R;
import applicaton.android.com.sehonmin.ui.util.recyclerview.ResultListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdListFragment extends Fragment implements View.OnClickListener{

    private RecyclerView rvContacts;
    private Button mCreateBtn;
    private String formName;
    private View view;

    public ThirdListFragment() {
        // Required empty public constructor
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ResultListAdapter adapter = new ResultListAdapter();
        adapter.notifyDataSetChanged();

        rvContacts = (RecyclerView) getActivity().findViewById(R.id.result_list_recycler_view);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third_list, container, false);
    }

    @Override
    public void onClick(View view) {

    }
}
