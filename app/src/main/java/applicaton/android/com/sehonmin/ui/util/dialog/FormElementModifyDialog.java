package applicaton.android.com.sehonmin.ui.util.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.R;
import applicaton.android.com.sehonmin.ui.util.recyclerview.FormCreateAdapter;

/**
 * Created by ken13 on 2017-12-06.
 */

public class FormElementModifyDialog {
    private Context context;
    private int position;
    private Button mOkBtn;
    private Button mCancelBtn;
    private EditText mChange;


    private FormCreateAdapter adapter;

    public FormElementModifyDialog(Context context,  FormCreateAdapter adapter,int position){
        this.position=position;
        this.context=context;
        this.adapter=adapter;
    }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_form_element_modify, null);

        builder.setView(view);
        mOkBtn = (Button) view.findViewById(R.id.btn_form_element_change_ok);
        mCancelBtn=(Button) view.findViewById(R.id.btn_form_element_change_cancel);
        mChange = (EditText) view.findViewById(R.id.input_form_element_change);


        final AlertDialog dialog = builder.create();
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adapter.modifyList(position,mChange.getText().toString());

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
