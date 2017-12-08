package applicaton.android.com.sehonmin.ui.util.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.R;

/**
 * Created by ken13 on 2017-12-05.
 */

public class FormCreateAdapter extends RecyclerView.Adapter<FormCreateAdapter.ViewHolder>  {

    private FormDTO contents;
    private List<String[]> list;
    private Context context;
    private ItemClick itemClick;

    public interface ItemClick {
        public void onClick(View view,int position);
    }

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }




    public FormCreateAdapter(FormDTO dto){
        contents= dto;

        list=new ArrayList<String[]>();
        Iterator it=dto.getElements().keySet().iterator();
        Map map=dto.getElements();
        list.add(new String[]{"add element","+"});
        while(it.hasNext()) {
            Object key = it.next();
            list.add(new String[]{key.toString(), map.get(key).toString()});

        }

    }
    @Override
    public FormCreateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.activity_form_element_list, parent, false);
        FormCreateAdapter.ViewHolder viewHolder = new FormCreateAdapter.ViewHolder(context, contactView);

        return viewHolder;

    }


    @Override
    public void onBindViewHolder(FormCreateAdapter.ViewHolder holder, int position) {

        final int Position =position;
        TextView textView = holder.nameTextView;
        textView.setText(list.get(position)[0].toString()+" : ");
        TextView button = holder.messageButton;
        button.setText(list.get(position)[1].toString());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(itemClick != null){
                    itemClick.onClick(v, Position);
                }
            }
        });


        //button.setText("Message");

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public String getFormName(){
        return contents.getFormName();
    }
    public List getList(){
        return list;
    }
    public void addList(String key, String value){
        list.add(new String[]{key,value});
        notifyDataSetChanged();

    }
    public void modifyList(int position, String change){


        String[] args=list.get(position);
        String key=args[0];

        list.remove(position);
        list.add(position,new String[]{key,change});
        notifyDataSetChanged();

    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView messageButton;
        private Context context;



        public ViewHolder(Context context, View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.element_1);
            messageButton = (TextView) itemView.findViewById(R.id.element_2);
            this.context = context;

        }




    }

}
