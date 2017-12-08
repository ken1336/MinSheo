package applicaton.android.com.sehonmin.ui.core;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import applicaton.android.com.sehonmin.FormCreationActivity;
import applicaton.android.com.sehonmin.R;
import applicaton.android.com.sehonmin.ui.util.recyclerview.FormListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondListFragment extends Fragment implements View.OnClickListener{

    private RecyclerView rvContacts;
    private Button mCreateBtn;
    private String formName;
    private View view;



    public SecondListFragment() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCreateBtn=(Button)getActivity().findViewById(R.id.create_form_btn);
        mCreateBtn.setOnClickListener(this);

        FormListAdapter adapter = new FormListAdapter();
        adapter.notifyDataSetChanged();

        rvContacts = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_second_list3, container, false);
        return view;
    }

    @Override
    public void onClick(View view) {


        show();

    }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_form_name, null);

        builder.setView(view);
        final Button mOkBtn = (Button) view.findViewById(R.id.btn_form_name_ok);
        final Button mCancelBtn=(Button) view.findViewById(R.id.btn_form_name_cancel);
        final EditText name = (EditText) view.findViewById(R.id.input_form_name);


        final AlertDialog dialog = builder.create();
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                formName=name.getText().toString();
                dialog.dismiss();
                Intent intent=new Intent(getActivity(),FormCreationActivity.class);
                intent.putExtra("formName",formName);
                startActivity(intent);
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
