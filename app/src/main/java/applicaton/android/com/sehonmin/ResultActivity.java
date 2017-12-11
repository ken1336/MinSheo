package applicaton.android.com.sehonmin;

import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.TextView;

import applicaton.android.com.sehonmin.Model.service.FormManager;
import applicaton.android.com.sehonmin.Model.service.ResultManager;
import applicaton.android.com.sehonmin.ui.util.recyclerview.ResultParticipateAdapter;
import applicaton.android.com.sehonmin.ui.util.recyclerview.itemdecoration.MarginItemDecoration;

public class ResultActivity extends AppCompatActivity {
    private TextView groupid;
    private TextView formname;
    private TextView statistic;
    private TextView comment;
    private TextView endday;
    private ResultManager rm;
    private FormManager fm;

    RecyclerView rvContacts;

    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("sstss","resultstart");
        Intent intent=getIntent();
        rm=ResultManager.getInstance();
        fm=FormManager.getInstance();
        setContentView(R.layout.activity_result);


        String formName=intent.getStringExtra("data");
        Log.i("sstss",rm.getComment(formName));
        groupid=(TextView)findViewById(R.id.result_detail_group);

        formname=(TextView)findViewById(R.id.result_detail_name);
        statistic=(TextView)findViewById(R.id.result_detail_statistic);
        comment=(TextView)findViewById(R.id.result_detail_comment);
        endday=(TextView)findViewById(R.id.result_detail_endday_value);

        groupid.setText(rm.getGroupID(formName));
        Log.i("sstss",rm.getGroupID(formName));
        formname.setText(formName);
        statistic.setText(rm.getStatistic(formName));
        Log.i("sstss",rm.getStatistic(formName));
        comment.setText(fm.getComment(formName));
        endday.setText(rm.getEndDay(formName));




        ResultParticipateAdapter adapter = new ResultParticipateAdapter(formName,this);
        adapter.notifyDataSetChanged();

        rvContacts = (RecyclerView)findViewById(R.id.result_recycler_view);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));



        //txt.setText(list.getStartDay()+","+list.getEndDay()+","+list.getFormName());




    }
    public void setStatistic(String statistic){
        this.statistic.setText(statistic);
    }

}
