package applicaton.android.com.sehonmin;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import applicaton.android.com.sehonmin.ui.util.MinPagerAdapter;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {


    ViewPager vp;
    Button btn_first;
    Button btn_second;
    Button btn_third;
    View.OnClickListener movePageListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

       ////////////////////데이터베이스 테스트aa
       /* FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");




        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("min", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("min", "Failed to read value.", error.toException());
            }
        });*/



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
