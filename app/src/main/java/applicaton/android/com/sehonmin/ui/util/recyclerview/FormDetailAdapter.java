package applicaton.android.com.sehonmin.ui.util.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.R;

/**
 * Created by Park on 2017-12-10.
 */

public class FormDetailAdapter extends RecyclerView.Adapter<FormDetailAdapter.ViewHolder> {
    private Context context;
    private FormDTO formDTO;

    public FormDetailAdapter(FormDTO formDTO) {
        this.formDTO = formDTO;
        //formDTOList = FormManager.getInstance().getItemList();
    }

    @Override
    public FormDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_form_info_list, parent, false);
        FormDetailAdapter.ViewHolder viewHolder = new FormDetailAdapter.ViewHolder(context, contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FormDetailAdapter.ViewHolder holder, int position) {
        switch(position){
            case 0:
                holder.formDetailNameTextView.setText("시작일");
                holder.formDetailContentTextView.setText(formDTO.getStartDay());
                break;
            case 1:
                holder.formDetailNameTextView.setText("종료일");
                holder.formDetailContentTextView.setText(formDTO.getEndDay());
                break;
            case 2:
                holder.formDetailNameTextView.setText("설명");
                holder.formDetailContentTextView.setText(formDTO.getComment());
                break;
            case 3:
                holder.formDetailNameTextView.setText("설정그룹");
                holder.formDetailContentTextView.setText(formDTO.getGroupID());
                break;

        }
        //FormDTO formDTO = formDTOList.get(position);

        //holder.formNameTextView.setText(formDTO.getName());

    }

    @Override
    public int getItemCount() {
        //formDTOList = FormManager.getInstance().getItemList();
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView formDetailNameTextView;
        public TextView formDetailContentTextView;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            formDetailNameTextView = itemView.findViewById(R.id.form_detail_name);
            formDetailContentTextView = itemView.findViewById(R.id.form_detail_content);
        }

        @Override
        public void onClick(View view){

        }
    }
}

