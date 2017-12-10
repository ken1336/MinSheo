package applicaton.android.com.sehonmin.ui.util.recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.Model.service.FormManager;
import applicaton.android.com.sehonmin.Model.service.GroupManager;
import applicaton.android.com.sehonmin.R;

public class FormListAdapter extends RecyclerView.Adapter<FormListAdapter.ViewHolder>  {
    private List<FormDTO> formDTOList;
    private Context context;

    public FormListAdapter(){
        formDTOList = FormManager.getInstance().getItemList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_form_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(context, contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FormDTO formDTO = formDTOList.get(position);

        holder.formNameTextView.setText(formDTO.getName());

    }

    @Override
    public int getItemCount() {
        formDTOList = FormManager.getInstance().getItemList();
        return formDTOList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView formNameTextView;
        public Button delFormButton;
        public RecyclerView formInfoRecyclerView;
        private FormDTO formDTO;
        private boolean check;

        public ViewHolder(final Context context, View itemView) {
            super(itemView);

            formNameTextView = itemView.findViewById(R.id.form_name_tt);
            delFormButton = itemView.findViewById(R.id.del_form_btn);
            formInfoRecyclerView = itemView.findViewById(R.id.rv_form_info);

            delFormButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View view = inflater.inflate(R.layout.del_group_dialog, null);

                    builder.setView(view);
                    final Button mOkBtn = (Button) view.findViewById(R.id.del_group_yes_btn);
                    final Button mCancelBtn = (Button) view.findViewById(R.id.del_group_no_btn);

                    final AlertDialog dialog = builder.create();
                    mOkBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FormDTO formDTO = formDTOList.get(getLayoutPosition());
                            FormManager.getInstance().deleteForm(formDTO);
                            //notifyDataSetChanged();
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
            });

            formNameTextView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(!check){
                if(formDTO == null){
                    formDTO = ((ArrayList<FormDTO>)FormManager.getInstance().getItemList()).get(getLayoutPosition());

                    FormDetailAdapter formDetailAdapter = new FormDetailAdapter(formDTO);

                    formInfoRecyclerView.setAdapter(formDetailAdapter);
                    formInfoRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                }

                formInfoRecyclerView.setVisibility(View.VISIBLE);
                check = true;
            }else{
                formInfoRecyclerView.setVisibility(View.GONE);
                check = false;
            }
        }
    }

}
