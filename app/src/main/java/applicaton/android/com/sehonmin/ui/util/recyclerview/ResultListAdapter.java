package applicaton.android.com.sehonmin.ui.util.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.Model.dto.ResultDTO;
import applicaton.android.com.sehonmin.Model.service.FormManager;
import applicaton.android.com.sehonmin.Model.service.ResultManager;
import applicaton.android.com.sehonmin.R;
import applicaton.android.com.sehonmin.ResultActivity;

/**
 * Created by ken13 on 2017-12-08.
 */

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ViewHolder>  {

    private List<ResultDTO> list;

    private ResultDTO dto;
    public ResultListAdapter(){
        list= ResultManager.getInstance().getItemList();
    }
    private Context context;

    @Override
    public ResultListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.activity_result_list, parent, false);
        ResultListAdapter.ViewHolder viewHolder = new ResultListAdapter.ViewHolder(context, contactView);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(ResultListAdapter.ViewHolder holder, int position) {
        dto=list.get(position);
        TextView textView = holder.nameTextView;
        textView.setText(dto.getFormName());



    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nameTextView;
        public Switch mSwitchBtn;
        private Context context;
        private LinearLayout layout;
        ResultListAdapter.ViewHolder holder;
        LinearLayout detailLayout;
        boolean check=false;


        public ViewHolder(Context context, View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            //mSwitchBtn = (Switch) itemView.findViewById(R.id.message_button);
            layout=(LinearLayout)itemView.findViewById(R.id.testlayout);
            layout.setOnClickListener(this);

            holder=this;
            this.context = context;




        }

        @Override
        public void onClick(View view) {
            //Animation animation = AnimationUtils.loadAnimation(context,R.anim.slide);
            Intent intent=new Intent(context, ResultActivity.class);
            intent.putExtra("data",list.get(holder.getPosition()).getFormName());

            context.startActivity(intent);
        }
    }
}
