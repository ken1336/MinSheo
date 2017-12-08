package applicaton.android.com.sehonmin.ui.util.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.Model.service.FormManager;
import applicaton.android.com.sehonmin.R;

/**
 * Created by ken13 on 2017-12-04.
 */

public class FormListAdapter extends RecyclerView.Adapter<FormListAdapter.ViewHolder>  {

    private List<FormDTO> list;

    private FormDTO dto;
    public FormListAdapter(){
        list=FormManager.getInstance().getItemList();
    }
    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.activity_form_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(context, contactView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
        ViewHolder holder;
        LinearLayout detailLayout;
        boolean check=false;


        public ViewHolder(Context context, View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            mSwitchBtn = (Switch) itemView.findViewById(R.id.message_button);
            layout=(LinearLayout)itemView.findViewById(R.id.testlayout);
            layout.setOnClickListener(this);

            holder=this;
            this.context = context;

        }

        @Override
        public void onClick(View view) {
            //Animation animation = AnimationUtils.loadAnimation(context,R.anim.slide);
            if(check==false) {
                detailLayout = (LinearLayout) view.findViewById(R.id.form_list_detail);
                detailLayout.setVisibility(View.VISIBLE);
                TextView textStart=(TextView)view.findViewById(R.id.form_start_date);
                textStart.setText("start day: "+dto.getStartDay());
                TextView textend=(TextView)view.findViewById(R.id.form_end_date);
                textend.setText("end day: "+dto.getEndDay());

                Toast.makeText(context, "VISIBLE " + holder.getPosition(), Toast.LENGTH_SHORT).show();
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
