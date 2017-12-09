package applicaton.android.com.sehonmin;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.Model.service.FormManager;
import applicaton.android.com.sehonmin.R;
import applicaton.android.com.sehonmin.ui.util.dialog.FormElementCreateDialog;
import applicaton.android.com.sehonmin.ui.util.dialog.FormElementModifyDialog;
import applicaton.android.com.sehonmin.ui.util.recyclerview.FormCreateAdapter;
import applicaton.android.com.sehonmin.ui.util.recyclerview.FormListAdapter;

public class FormCreationActivity extends AppCompatActivity {
    private Context context;
    private RecyclerView rv;
    private Button mSubmitBtn;
    private FloatingActionButton mStartBtn;
    private  FloatingActionButton mEndBtn;
    private Button mGroupIDBtn;
    private FormCreateAdapter adapter;
    private FormManager fm;
    private FormDTO dto;
    private String formName;
    private String startDay;
    private String endDay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_creation);
        this.context=this;

        Intent intent=getIntent();
        formName=intent.getStringExtra("formName");


        fm=FormManager.getInstance();
        fm.createForm(formName);
        dto=fm.getForm(formName);


        adapter = new FormCreateAdapter(dto);
        Log.i("sssss", String.valueOf(dto.getElements().size()));
        adapter.notifyDataSetChanged();
        adapter.setItemClick(itemClickListener());

        rv = (RecyclerView) findViewById(R.id.form_element_list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));


        mSubmitBtn=(Button)findViewById(R.id.submit_form_btn);
        mSubmitBtn.setOnClickListener(submitListener());
        mStartBtn=( FloatingActionButton)findViewById(R.id.btn_form_start_date);
        mEndBtn=( FloatingActionButton)findViewById(R.id.btn_form_end_date);
        mStartBtn.setOnClickListener(startDateListener());
        mEndBtn.setOnClickListener(endDateListener());
       /* mGroupIDBtn=(Button)findViewById(R.id.btn_form_set_groupid);
        mGroupIDBtn.setOnClickListener(setGroupIDListener());
*/
        


    }
    private View.OnClickListener submitListener(){
        View.OnClickListener listner=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList list=(ArrayList)adapter.getList();
                for(int i=1;i<list.size();i++){
                    String[] adder=(String[])list.get(i);
                    dto.put(adder[0].toString(),adder[1].toString());

                }

                fm.submitData(dto);
                onBackPressed();
            }
        };
        return listner;
    }
    private FormCreateAdapter.ItemClick itemClickListener(){
        FormCreateAdapter.ItemClick listener=new FormCreateAdapter.ItemClick() {
            @Override
            public void onClick(View view, int position) {
                if(position==0)
                    new FormElementCreateDialog(context,adapter).show();
                else
                    new FormElementModifyDialog(context,adapter,position).show();
                }
            };
        return listener;
    }

    private View.OnClickListener startDateListener(){
        View.OnClickListener listner=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = new GregorianCalendar();

                DatePickerDialog.OnDateSetListener startDateListener=startDateSetListener();

                new DatePickerDialog(context, startDateListener, cal.get(Calendar.YEAR),

                        cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();

            }
        };
        return listner;
    }

    public DatePickerDialog.OnDateSetListener startDateSetListener(){
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                startDay=year+"-"+month+"-"+day;
                dto.setStartDay(startDay);


            }
        };
        return listener;
    }

    private View.OnClickListener endDateListener(){
        View.OnClickListener listner=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = new GregorianCalendar();

                DatePickerDialog.OnDateSetListener endDateListener=endDateSetListener();

                new DatePickerDialog(context, endDateListener, cal.get(Calendar.YEAR),

                        cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();

            }
        };
        return listner;
    }

    public DatePickerDialog.OnDateSetListener endDateSetListener(){
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                endDay=year+"-"+month+"-"+day;
                dto.setEndDay(endDay);


            }
        };
        return listener;
    }
    public View.OnClickListener setGroupIDListener(){
        View.OnClickListener listener=null;

        return listener;
    }

}
