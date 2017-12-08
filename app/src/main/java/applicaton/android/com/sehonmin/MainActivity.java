package applicaton.android.com.sehonmin;

import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import java.util.ArrayList;
import java.util.Map;

import applicaton.android.com.sehonmin.data.util.PhoneBookManager;
import applicaton.android.com.sehonmin.Model.service.FormManager;
import applicaton.android.com.sehonmin.Model.service.ResultManager;
import applicaton.android.com.sehonmin.ui.core.FirstFragment;
import applicaton.android.com.sehonmin.ui.util.MinPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button first_fragment_btn;
    private Button second_fragment_btn;
    private Button third_fragment_btn;
    private FragmentManager fragmentManager;
    private FirstFragment ft;
    private ListView listView;
    private ArrayList<Map<String, String>> dataList;
    private SimpleAdapter adapter;
    private ViewPager viewPager;

    private TabLayout tabLayout;
    FormManager fm=FormManager.getInstance();
    ResultManager rm= ResultManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PhoneBookManager.getInstance(this);
        setViewPager();
        setOnclickListener();
        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("tab1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    public void setViewPager(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MinPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
    }

    public void setOnclickListener() {

        /*first_fragment_btn = (Button) findViewById(R.id.btn_first);
        second_fragment_btn = (Button) findViewById(R.id.btn_second);
        third_fragment_btn = (Button) findViewById(R.id.btn_third);*/

        /*first_fragment_btn.setOnClickListener(this);
        first_fragment_btn.setTag(0);
        second_fragment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = (int) view.getTag();
                viewPager.setCurrentItem(tag);

            }
        });
        second_fragment_btn.setTag(1);
        third_fragment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = (int) view.getTag();
                viewPager.setCurrentItem(tag);

            }
        });
        third_fragment_btn.setTag(2);*/

    }

    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        viewPager.setCurrentItem(tag);
    }

}
