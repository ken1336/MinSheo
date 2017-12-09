package applicaton.android.com.sehonmin.ui.util.recyclerview;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import applicaton.android.com.sehonmin.Model.dto.PhoneBookDTO;
import applicaton.android.com.sehonmin.Model.service.PhoneBookManager;
import applicaton.android.com.sehonmin.R;

/**
 * Created by Park on 2017-12-08.
 */

public class PhoneBookAdapter extends RecyclerView.Adapter<PhoneBookAdapter.ViewHolder> {
    private List<PhoneBookDTO> mPhoneBookDTOs;
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPref;
    private ArrayList<PhoneBookDTO> phoneBookDTOArrayList;
    private ArrayList<Boolean> firstA;
    private boolean first = true;
    private int count;

    public PhoneBookAdapter(ArrayList<PhoneBookDTO> phoneBookDTOArrayList) {
        this.phoneBookDTOArrayList = phoneBookDTOArrayList;
        phoneBookDTOArrayList.clear();
        firstA = new ArrayList<Boolean>();
        mPhoneBookDTOs = PhoneBookManager.getInstance().getPhoneBookList();

    }

    @Override
    public PhoneBookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mPref = mContext.getSharedPreferences("person", Context.MODE_PRIVATE);
        mEditor = mPref.edit();
        count = mPhoneBookDTOs.size();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View contactView = inflater.inflate(R.layout.item_phonedto, parent, false);
        ViewHolder viewHolder = new ViewHolder(mContext, contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhoneBookAdapter.ViewHolder viewHolder, int position) {

        mPhoneBookDTOs = PhoneBookManager.getInstance().getPhoneBookList();

        PhoneBookDTO phoneBookDTO = mPhoneBookDTOs.get(position);

        final TextView textView = viewHolder.nameTextView;
        final TextView numberView = viewHolder.phoneTextView;

        textView.setText(phoneBookDTO.getName());
        numberView.setText(phoneBookDTO.getNumber());

        if (mPhoneBookDTOs.get(position).isSelected()) {
            //
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#d5d5d5"));
        } else {
            //phoneBookDTOArrayList.add(phoneBookDTO);
            viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

    }
    public void setSelected(int pos) {
        first = false;
        try {
            if (mPhoneBookDTOs.size() > 1) {
                //mPhoneBookDTOs.get(mPref.getInt("position", 0)).setSelected(false);
                mEditor.putInt("position", pos);
                mEditor.commit();
            }
            if (mPhoneBookDTOs.get(pos).isSelected()) {
                phoneBookDTOArrayList.remove(mPhoneBookDTOs.get(pos));
                mPhoneBookDTOs.get(pos).setSelected(false);
            } else {
                phoneBookDTOArrayList.add(mPhoneBookDTOs.get(pos));
                mPhoneBookDTOs.get(pos).setSelected(true);
            }
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mPhoneBookDTOs.size();
    }

    public void removeItem(int p) {
        mPhoneBookDTOs.remove(p);
        notifyItemRemoved(p);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        public TextView phoneTextView;
        public Button messageButton;

        private Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.phone_book_name);
            phoneTextView = (TextView) itemView.findViewById(R.id.phone_book_number);

            this.context = context;

        }

        @Override
        public void onClick(View view) {

        }
    }
}
