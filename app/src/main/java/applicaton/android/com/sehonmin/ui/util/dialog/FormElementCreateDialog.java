package applicaton.android.com.sehonmin.ui.util.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.R;
import applicaton.android.com.sehonmin.ui.util.recyclerview.FormCreateAdapter;

/**
 * Created by ken13 on 2017-12-05.
 */

public class FormElementCreateDialog {
    private Context context;
    private ArrayAdapter sAdapter;
    Spinner spinner;



    private FormCreateAdapter adapter;


    public FormElementCreateDialog(Context context,  FormCreateAdapter adapter){

        this.context=context;
        this.adapter=adapter;



    }
    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_form_element_create, null);

        builder.setView(view);
        final Button mOkBtn = (Button) view.findViewById(R.id.btn_element_ok);
        final Button mCancelBtn=(Button) view.findViewById(R.id.btn_element_cancel);
        final EditText key = (EditText) view.findViewById(R.id.input_element_key);


        sAdapter = ArrayAdapter.createFromResource(context, R.array.form_element, android.R.layout.simple_spinner_dropdown_item);
        spinner=view.findViewById(R.id.input_element_value);
        spinner.setAdapter(sAdapter);



        final AlertDialog dialog = builder.create();
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adapter.addList(key.getText().toString(),spinner.getSelectedItem().toString());

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
