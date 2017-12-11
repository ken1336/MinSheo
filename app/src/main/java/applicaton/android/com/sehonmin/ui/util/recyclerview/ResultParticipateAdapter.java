package applicaton.android.com.sehonmin.ui.util.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import applicaton.android.com.sehonmin.Model.dto.ResultDTO;
import applicaton.android.com.sehonmin.Model.dto.ResultList;
import applicaton.android.com.sehonmin.Model.service.ResultManager;
import applicaton.android.com.sehonmin.R;
import applicaton.android.com.sehonmin.ResultActivity;

/**
 * Created by ken13 on 2017-12-08.
 */

public class ResultParticipateAdapter extends RecyclerView.Adapter<ResultParticipateAdapter.ViewHolder>  {

    private ResultList list;
    private String formName;
    private ResultDTO dto;
    private ResultActivity activity;
    private Context context;
    public ResultParticipateAdapter(String formName, ResultActivity activity){
        this.formName = formName;
        this.activity=activity;
        ResultManager.getInstance().setResultParticipateAdapter(this);

        list= (ResultList)ResultManager.getInstance().getMap().get(formName);
    }


    @Override
    public ResultParticipateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        Log.i("sssss","dfa:"+context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.activity_result_participate_list, parent, false);
        ResultParticipateAdapter.ViewHolder viewHolder = new ResultParticipateAdapter.ViewHolder(context, contactView);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        list=(ResultList)ResultManager.getInstance().getMap().get(formName);
        activity.setStatistic(ResultManager.getInstance().getStatistic(formName));
        dto=(ResultDTO)list.getList().get(position);

        Log.i("sssss",dto.getName());
        TextView textView = holder.nameTextView;
        textView.setText(dto.getName());



    }
    @Override
    public int getItemCount() {
        return ((ResultList)ResultManager.getInstance().getMap().get(formName)).getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nameTextView;
        public Switch mSwitchBtn;
        private Context context;
        private LinearLayout layout;
        ResultParticipateAdapter.ViewHolder holder;

        RecyclerView rvContacts;
        boolean check=false;


        public ViewHolder(Context context, View itemView) {
            super(itemView);
            nameTextView = (TextView)itemView.findViewById(R.id.result_ss);
            layout=(LinearLayout)itemView.findViewById(R.id.result_participate_layout);
            //mSwitchBtn = (Switch) itemView.findViewById(R.id.message_button);
            Log.i("sssss", "textview: "+nameTextView+ itemView.getAccessibilityClassName() );

            layout.setOnClickListener(this);

            holder=this;
            this.context = context;




        }

        @Override
        public void onClick(View view) {
            if(check==false) {

                check=true;
                ResultDTO dto = (ResultDTO) list.getList().get(holder.getPosition());
                ResultDetailAdapter adapter = new ResultDetailAdapter(dto);
                adapter.notifyDataSetChanged();

                rvContacts = (RecyclerView) itemView.findViewById(R.id.result_detail_recycler_view);
                rvContacts.setAdapter(adapter);
                rvContacts.setLayoutManager(new LinearLayoutManager(context));
                rvContacts.setVisibility(View.VISIBLE);
               // LinearLayout lay=(LinearLayout)itemView.findViewById(R.id.list_list_detail);
               // lay.setVisibility(View.VISIBLE);
            }
            else{
                rvContacts.setAdapter(null);
                rvContacts.setVisibility(View.GONE);
              //  LinearLayout lay=(LinearLayout)itemView.findViewById(R.id.list_list_detail);
               // lay.setVisibility(View.VISIBLE);
                check=false;
            }
        }
    }
}