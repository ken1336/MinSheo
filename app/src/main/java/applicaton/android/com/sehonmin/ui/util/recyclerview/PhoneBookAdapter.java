package applicaton.android.com.sehonmin.ui.util.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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

    public PhoneBookAdapter() {
        mPhoneBookDTOs = PhoneBookManager.getInstance().getPhoneBookList();
    }

    @Override
    public PhoneBookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View contactView = inflater.inflate(R.layout.item_phonedto, parent, false);
        ViewHolder viewHolder = new ViewHolder(mContext, contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhoneBookAdapter.ViewHolder viewHolder, int position) {
        PhoneBookDTO phoneBookDTO  = mPhoneBookDTOs.get(position);

        TextView textView = viewHolder.nameTextView;
        textView.setText(phoneBookDTO.getName());
        //Button button = viewHolder.messageButton;
        //button.setText("Delete");
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
        public Button messageButton;

        private Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            //messageButton = (Button) itemView.findViewById(R.id.message_button);

            this.context = context;

            //messageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // We can access the data within the views
            //Toast.makeText(context, nameTextView.getText() + Integer.toString(position), Toast.LENGTH_SHORT).show();
            //mPhoneBookDTOs.removeItem(position);

        }
    }
}
