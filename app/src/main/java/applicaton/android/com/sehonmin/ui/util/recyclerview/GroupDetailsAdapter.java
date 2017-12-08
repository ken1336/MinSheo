package applicaton.android.com.sehonmin.ui.util.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import applicaton.android.com.sehonmin.Model.dto.GroupDTO;
import applicaton.android.com.sehonmin.Model.service.GroupManager;
import applicaton.android.com.sehonmin.R;

/**
 * Created by Park on 2017-12-08.
 */

public class GroupDetailsAdapter extends RecyclerView.Adapter<GroupDetailsAdapter.ViewHolder> {
    private static Context mContext;
    private String groupName;
    private GroupDTO groupDTO;
    private ArrayList<String> groupNameList;
    private HashMap<String, String> groupDTOHashMap;

    public GroupDetailsAdapter(GroupDTO groupDTO) {
        this.groupDTO = groupDTO;

        groupDTOHashMap = groupDTO.getGroupDTOHashMap();
        groupNameList = new ArrayList<String>(groupDTOHashMap.keySet());
    }

    @Override
    public GroupDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View contactView = inflater.inflate(R.layout.group_details_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(mContext, contactView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GroupDetailsAdapter.ViewHolder viewHolder, int position) {

        String name = groupNameList.get(position);
        String phoneNum = groupDTOHashMap.get(name);

        TextView groupDetailsNameView = viewHolder.groupDetailsNameView;
        groupDetailsNameView.setText(name);
        TextView groupDetailsPhoneNumView = viewHolder.groupDetailsPhoneNumView;
        groupDetailsPhoneNumView.setText(phoneNum);
        viewHolder.setGroupDTO(groupDTO);
    }

    @Override
    public int getItemCount() {
        groupNameList = new ArrayList<String>(groupDTOHashMap.keySet());
        Collections.sort(groupNameList);

        return groupNameList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView groupDetailsNameView;
        public TextView groupDetailsPhoneNumView;
        public Button groupDetailsPhoneNumBtn;
        public GroupDTO groupDTO;
        private GroupDetailsAdapter groupDetailsAdapter;

        public void setGroupDTO(GroupDTO groupDTO) {
            this.groupDTO = groupDTO;
        }

        public ViewHolder(Context context, View itemView, GroupDetailsAdapter groupDetailsAdapter) {
            super(itemView);
            this.groupDetailsAdapter = groupDetailsAdapter;
            groupDetailsNameView = (TextView) itemView.findViewById(R.id.group_details_name);
            groupDetailsPhoneNumView = (TextView) itemView.findViewById(R.id.group_details_phone_num);
            groupDetailsPhoneNumBtn = (Button) itemView.findViewById(R.id.group_details_del_phone_num_btn);

            groupDetailsPhoneNumBtn.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            groupDTO.getGroupDTOHashMap().remove(groupDetailsNameView.getText().toString());
            groupDetailsAdapter.notifyItemRemoved(getLayoutPosition());
            GroupManager.getInstance().removeList(groupDTO.getName(), groupDetailsNameView.getText().toString());
        }

    }
}