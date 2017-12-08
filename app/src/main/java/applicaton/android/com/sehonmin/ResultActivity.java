package applicaton.android.com.sehonmin;

import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import applicaton.android.com.sehonmin.ui.util.recyclerview.ResultParticipateAdapter;

public class ResultActivity extends AppCompatActivity {
    TextView txt;

    RecyclerView rvContacts;

    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        // txt=(TextView)findViewById(R.id.textsome);


        String formName=intent.getStringExtra("data");


       /* Bundle bundle = new Bundle();
        rff.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.result_fragment,rff);
        fragmentTransaction.commit();*/


        setContentView(R.layout.activity_result);


        ResultParticipateAdapter adapter = new ResultParticipateAdapter(formName);
        adapter.notifyDataSetChanged();

        rvContacts = (RecyclerView)findViewById(R.id.result_recycler_view);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));


        //txt.setText(list.getStartDay()+","+list.getEndDay()+","+list.getFormName());




    }

}
