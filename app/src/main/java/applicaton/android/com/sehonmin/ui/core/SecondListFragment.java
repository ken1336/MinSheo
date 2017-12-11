package applicaton.android.com.sehonmin.ui.core;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import applicaton.android.com.sehonmin.FormCreationActivity;
import applicaton.android.com.sehonmin.Model.service.FormManager;
import applicaton.android.com.sehonmin.R;
import applicaton.android.com.sehonmin.ui.util.recyclerview.FormListAdapter;
import applicaton.android.com.sehonmin.ui.util.recyclerview.itemdecoration.MarginItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondListFragment extends Fragment implements View.OnClickListener{
    private RecyclerView rvForms;
    private FloatingActionButton mCreateBtn;
    private String formName;
    private  LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvForms = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        mCreateBtn = (FloatingActionButton)getActivity().findViewById(R.id.create_form_btn);

        mCreateBtn.setOnClickListener(this);

        FormListAdapter adapter = new FormListAdapter();

        rvForms.setAdapter(adapter);
        linearLayoutManager =new LinearLayoutManager(getActivity());
        rvForms.setLayoutManager(linearLayoutManager);
        rvForms.addItemDecoration(new MarginItemDecoration(20));

        rvForms.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        mCreateBtn.setVisibility(View.GONE);
                        return;
                    }
                }
                mCreateBtn.setVisibility(View.VISIBLE);
            }
        });

        FormManager.getInstance().setFormListAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second_list3, container, false);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getActivity(),FormCreationActivity.class);
        startActivity(intent);
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
