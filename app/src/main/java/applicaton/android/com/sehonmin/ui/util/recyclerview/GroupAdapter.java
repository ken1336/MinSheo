package applicaton.android.com.sehonmin.ui.util.recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import applicaton.android.com.sehonmin.Model.dto.GroupDTO;
import applicaton.android.com.sehonmin.Model.dto.PhoneBookDTO;
import applicaton.android.com.sehonmin.Model.service.GroupManager;
import applicaton.android.com.sehonmin.Model.service.PhoneBookManager;
import applicaton.android.com.sehonmin.R;
import applicaton.android.com.sehonmin.ui.util.recyclerview.itemdecoration.RecyclerItemClickListener;

/**
 * Created by Park on 2017-12-08.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    private List<String> groupNameList;
    private static Context mContext;

    public GroupAdapter() {
        GroupManager groupManager = GroupManager.getInstance();
        groupNameList = groupManager.getGroupNameList();
    }

    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View contactView = inflater.inflate(R.layout.item_group_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(mContext, contactView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GroupAdapter.ViewHolder viewHolder, int position) {

        TextView textView = viewHolder.nameTextView;
        textView.setText(groupNameList.get(position));
        RecyclerView recyclerView = viewHolder.recyclerView;

        //Button button = viewHolder.messageButton;
        //button.setText("Delete");
    }

    @Override
    public int getItemCount() {
        return groupNameList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        public Button addGroupPersonBtn;
        public Button delGroupBtn;
        public RecyclerView recyclerView;
        private Context context;
        private GroupDTO groupDTO;
        private GroupAdapter groupAdapter;
        private PhoneBookAdapter phoneBookAdapter;
        private ArrayList<PhoneBookDTO> phoneBookDTOArrayList;

        private boolean check = false;

        public ViewHolder(Context context, View itemView, GroupAdapter groupAdapter) {
            super(itemView);
            this.groupAdapter = groupAdapter;
            this.context = context;
            phoneBookDTOArrayList = new ArrayList<PhoneBookDTO>();
            phoneBookDTOArrayList.clear();
            nameTextView = (TextView) itemView.findViewById(R.id.group_name_tt);
            addGroupPersonBtn = (Button) itemView.findViewById(R.id.add_group_person_btn);
            delGroupBtn = (Button) itemView.findViewById(R.id.del_group_btn);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_group_person);

            nameTextView.setOnClickListener(this);
            addGroupPersonBtn.setOnClickListener(this);
            delGroupBtn.setOnClickListener(this);
            //addGroupPersonBtn.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (view.equals(nameTextView)) {
                if (!check) {
                    if (groupDTO == null) {
                        groupDTO = GroupManager.getInstance().getGroupHashMap().get(GroupManager.getInstance().getGroupNameList().get(getLayoutPosition()));

                        GroupDetailsAdapter groupDetailsAdapter = new GroupDetailsAdapter(groupDTO);
                        GroupManager.getInstance().setGroupDetailsAdapter(groupDetailsAdapter);

                        recyclerView.setAdapter(groupDetailsAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

                    }

                    recyclerView.setVisibility(View.VISIBLE);

                    check = true;
                } else {
                    recyclerView.setVisibility(View.GONE);
                    check = false;
                }
            } else if (view.equals(addGroupPersonBtn)) {
                show();
            } else if (view.equals(delGroupBtn)) {
                show2();
            }
        }

        public void show() {

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.group_add_user_dialog, null);

            builder.setView(view);
            final Button mOkBtn = (Button) view.findViewById(R.id.group_user_add_ok_btn);
            final Button mCancelBtn = (Button) view.findViewById(R.id.group_user_add_cancel_btn);
            final RecyclerView phoneBook = (RecyclerView) view.findViewById(R.id.rv_add_user);

            phoneBookAdapter = new PhoneBookAdapter(phoneBookDTOArrayList);

            phoneBook.setAdapter(phoneBookAdapter);
            phoneBook.setLayoutManager(new LinearLayoutManager(mContext));
            phoneBook.addOnItemTouchListener(
                    new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            try {
                                phoneBookAdapter.setSelected(position);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    })
            );

            final AlertDialog dialog = builder.create();
            mOkBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //phoneBookDTOArrayList.clear();
                    if (phoneBookDTOArrayList.size() != 0) {
                        GroupManager.getInstance().put(nameTextView.getText().toString(), phoneBookDTOArrayList);
                        phoneBookDTOArrayList.clear();
                    }
                    phoneBookAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });
            mCancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<PhoneBookDTO> phoneBookDTOList = (ArrayList<PhoneBookDTO>)PhoneBookManager.getInstance().getPhoneBookList();
                    int size = 0;
                    for(int i = 0; i < size; i ++){
                        phoneBookDTOList.get(i).setSelected(false);
                    }
                    phoneBookAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });

            dialog.show();
        }

        public void show2() {

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.del_group_dialog, null);

            builder.setView(view);
            final Button mOkBtn = (Button) view.findViewById(R.id.del_group_yes_btn);
            final Button mCancelBtn = (Button) view.findViewById(R.id.del_group_no_btn);

            final AlertDialog dialog = builder.create();
            mOkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GroupManager.getInstance().removeGroup(nameTextView.getText().toString());
                    groupAdapter.notifyDataSetChanged();
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

    }
}
