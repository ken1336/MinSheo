package applicaton.android.com.sehonmin;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

import applicaton.android.com.sehonmin.db.service.FormManager;
import applicaton.android.com.sehonmin.ui.util.MinPagerAdapter;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {


    ViewPager vp;
    Button btn_first;
    Button btn_second;
    Button btn_third;
    View.OnClickListener movePageListener;


    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);




        FormManager fm=new FormManager();
        fm.createForm("form1","min");
        fm.createFiled("name");
        fm.createFiled("id");
        fm.createFiled("num");
        fm.submitData();
        fm.createForm("form12","min");
        fm.createFiled("name12");
        fm.createFiled("id12");
        fm.createFiled("num12");
        fm.submitData();
        fm.deleteForm("form1");
        /*fm.createForm("form1");
        fm.createFiled("name", "min");
        fm.createFiled("name1", "min1");*/










        //view pager 부분//////////////////////////////////////////////////////////////////////////////////////////

        vp = (ViewPager)findViewById(R.id.vp);

        vp.setAdapter(new MinPagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        Button btn_first = (Button)findViewById(R.id.btn_first);
        Button btn_second = (Button)findViewById(R.id.btn_second);
        Button btn_third = (Button)findViewById(R.id.btn_third);

        btn_first.setOnClickListener(this);
        btn_first.setTag(0);
        btn_second.setOnClickListener(this);
        btn_second.setTag(1);
        btn_third.setOnClickListener(this);
        btn_third.setTag(2);
    }
    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        vp.setCurrentItem(tag);
    }

}
