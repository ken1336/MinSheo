package applicaton.android.com.sehonmin;

import android.app.AlertDialog;
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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.security.acl.Group;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TooManyListenersException;

import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.Model.service.FormManager;
import applicaton.android.com.sehonmin.Model.service.GroupManager;
import applicaton.android.com.sehonmin.R;
import applicaton.android.com.sehonmin.ui.util.dialog.FormElementCreateDialog;
import applicaton.android.com.sehonmin.ui.util.dialog.FormElementModifyDialog;
import applicaton.android.com.sehonmin.ui.util.recyclerview.FormCreateAdapter;
import applicaton.android.com.sehonmin.ui.util.recyclerview.FormListAdapter;

public class FormCreationActivity extends AppCompatActivity implements View.OnClickListener {
    private FormCreationActivity formCreationActivity;

    private TextView startDayTextView;
    private TextView endDayTextView;
    private TextView selectGroupTextView;

    private EditText formNameEditText;
    private EditText formCommentEditText;

    private Button startDayButton;
    private Button endDayButton;
    private Button selectGroupButton;
    private Button makeGroupButton;

    private String startDay;
    private String endDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_form);
        setTitle("수요조사 형식 만들기");
        formCreationActivity = this;

        startDayTextView = (TextView) findViewById(R.id.start_day_text);
        endDayTextView = (TextView) findViewById(R.id.end_day_text);
        selectGroupTextView = (TextView) findViewById(R.id.select_group_text);

        formNameEditText = (EditText) findViewById(R.id.form_name_edit);
        formCommentEditText = (EditText) findViewById(R.id.form_comment_edit);

        startDayButton = (Button) findViewById(R.id.start_day_btn);
        endDayButton = (Button) findViewById(R.id.end_day_btn);
        selectGroupButton = (Button) findViewById(R.id.select_group_btn);
        makeGroupButton = (Button) findViewById(R.id.make_group_btn);

        startDayButton.setOnClickListener(this);
        endDayButton.setOnClickListener(this);
        selectGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroupManager groupManager = GroupManager.getInstance();
                ArrayList<String> groupNameList = (ArrayList<String>)groupManager.getGroupNameList();

                AlertDialog.Builder builder = new AlertDialog.Builder(formCreationActivity);
                LayoutInflater inflater = LayoutInflater.from(formCreationActivity);
                View view2 = inflater.inflate(R.layout.activity_creation_form_group_dialog, null);

                ListView groupListView = (ListView) view2.findViewById(R.id.group_list_view);

                ArrayAdapter arrayAdapter = new ArrayAdapter(formCreationActivity, android.R.layout.simple_list_item_1,groupNameList);
                groupListView.setAdapter(arrayAdapter);

                builder.setView(view2);

                final AlertDialog dialog = builder.create();

                groupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        selectGroupTextView.setText(((TextView)view).getText().toString());
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        makeGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormManager formManager = FormManager.getInstance();
                formManager.createForm(formNameEditText.getText().toString(),
                        startDay,
                        endDay,
                        formCommentEditText.getText().toString(),
                        selectGroupTextView.getText().toString()
                        );
                formCreationActivity.finish();
            }
        });

    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view2 = inflater.inflate(R.layout.activity_creation_form_dialog, null);

        Button s_day_btn = (Button) view2.findViewById(R.id.s_day_btn);
        final DatePicker datePicker = (DatePicker) view2.findViewById(R.id.s_date_picker);

        builder.setView(view2);

        final AlertDialog dialog = builder.create();

        if(view.equals(startDayButton)) {
            s_day_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startDay = datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getDayOfMonth();
                    startDayTextView.setText(startDay);
                    dialog.dismiss();
                }
            });
        }else{
            s_day_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    endDay = datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getDayOfMonth();
                    endDayTextView.setText(endDay);
                    dialog.dismiss();
                }
            });
        }

        dialog.show();
    }

}
