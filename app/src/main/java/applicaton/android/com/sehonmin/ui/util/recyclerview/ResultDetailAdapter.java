package applicaton.android.com.sehonmin.ui.util.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.Model.dto.ResultDTO;
import applicaton.android.com.sehonmin.Model.service.FormManager;
import applicaton.android.com.sehonmin.Model.service.ResultManager;
import applicaton.android.com.sehonmin.R;

/**
 * Created by ken13 on 2017-12-09.
 */

public class ResultDetailAdapter extends RecyclerView.Adapter<ResultDetailAdapter.ViewHolder>  {

    List<String[]> list;
    private ResultDTO dto;
    public ResultDetailAdapter(ResultDTO dto){
        list=new ArrayList<String[]>();
        this.dto=dto;
        Map map=dto.getElements();
        Iterator it=map.keySet().iterator();
        Log.i("ssts",String.valueOf(map.size()));
        ResultManager.getInstance().setDetailAdapter(this);
        while(it.hasNext()){
            Object key = it.next();
            list.add(new String[]{key.toString(), map.get(key).toString()});
        }
    }
    private Context context;

    @Override
    public ResultDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.activity_result_participate_detail_list, parent, false);
        ResultDetailAdapter.ViewHolder viewHolder = new ResultDetailAdapter.ViewHolder(context, contactView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ResultDetailAdapter.ViewHolder holder, int position) {
        final int Position =position;
        TextView resultDetailKey = holder.resultDetailKey;
        resultDetailKey.setText(list.get(position)[0].toString()+" : ");
        Log.i("ssts",list.get(position)[0].toString()+","+list.size());
        TextView resultDetaiValue = holder.resultDetailValue;
        resultDetaiValue.setText(list.get(position)[1].toString());
        Log.i("ssts",list.get(position)[1].toString());




    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView resultDetailKey;
        public TextView resultDetailValue;
        public Switch mSwitchBtn;
        private Context context;
        private LinearLayout layout;
        ResultDetailAdapter.ViewHolder holder;
        LinearLayout detailLayout;
        boolean check=false;


        public ViewHolder(Context context, View itemView) {
            super(itemView);
            resultDetailKey = (TextView) itemView.findViewById(R.id.result_detail_key);
            resultDetailValue = (TextView) itemView.findViewById(R.id.result_detail_value);


            layout=(LinearLayout)itemView.findViewById(R.id.result_detail_layout);
          //  layout.setOnClickListener(this);

            holder=this;
            this.context = context;




        }

        @Override
        public void onClick(View view) {
            //Animation animation = AnimationUtils.loadAnimation(context,R.anim.slide);
            if(check==false) {
                /*detailLayout = (LinearLayout) view.findViewById(R.id.form_list_detail);
                detailLayout.setVisibility(View.VISIBLE);*/
                TextView textStart=(TextView)view.findViewById(R.id.form_start_date);
                //textStart.setText("start day: "+dto.getStartDay());
                TextView textend=(TextView)view.findViewById(R.id.form_end_date);
                //textend.setText("end day: "+dto.getEndDay());

             //   Toast.makeText(context, "VISIBLE " + holder.getPosition(), Toast.LENGTH_SHORT).show();
                check=true;
            }
            else{
                detailLayout = (LinearLayout) view.findViewById(R.id.form_list_detail);
                detailLayout.setVisibility(View.GONE);

                Toast.makeText(context, "GONE: " + holder.getPosition(), Toast.LENGTH_SHORT).show();
                check=false;
            }
        }
    }


}
