package applicaton.android.com.sehonmin.ui.core;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import applicaton.android.com.sehonmin.FormCreationActivity;
import applicaton.android.com.sehonmin.Model.dto.GroupDTO;
import applicaton.android.com.sehonmin.Model.service.GroupManager;
import applicaton.android.com.sehonmin.Model.service.PhoneBookManager;
import applicaton.android.com.sehonmin.R;
import applicaton.android.com.sehonmin.ui.util.recyclerview.FormListAdapter;
import applicaton.android.com.sehonmin.ui.util.recyclerview.GroupAdapter;
import applicaton.android.com.sehonmin.ui.util.recyclerview.PhoneBookAdapter;
import applicaton.android.com.sehonmin.ui.util.recyclerview.itemdecoration.DividerItemDecoration;
import applicaton.android.com.sehonmin.ui.util.recyclerview.itemdecoration.MarginItemDecoration;
import applicaton.android.com.sehonmin.ui.util.recyclerview.itemdecoration.RecyclerItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstListFragment extends Fragment implements View.OnClickListener{

    private GroupAdapter groupAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView rvContacts = (RecyclerView) getActivity().findViewById(R.id.rvphone);
        Button createGroupButton = (Button) getActivity().findViewById(R.id.create_group_btn);

        groupAdapter = new GroupAdapter();

       /* rvContacts.addItemDecoration(
                new DividerItemDecoration(getActivity().getDrawable(R.drawable.divider)));*/
       rvContacts.setHasFixedSize(true);
        rvContacts.setAdapter(groupAdapter);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rvContacts.setLayoutManager(gridLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                MarginItemDecoration(20);
        rvContacts.addItemDecoration(itemDecoration);
        createGroupButton.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first_list, container, false);
    }

    @Override
    public void onClick(View view) {
        show();
    }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.group_create_dialog, null);

        builder.setView(view);
        final Button mOkBtn = (Button) view.findViewById(R.id.group_create_dialog_ok_btn);
        final Button mCancelBtn=(Button) view.findViewById(R.id.group_create_dialog_cancel_btn);
        final EditText name = (EditText) view.findViewById(R.id.group_create_dialog_et);

        final AlertDialog dialog = builder.create();
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String groupName = name.getText().toString();
                GroupManager.getInstance().addGroup(groupName);
                GroupManager.getInstance().setGroupAdapter(groupAdapter);
                dialog.dismiss();
            }
        });
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
