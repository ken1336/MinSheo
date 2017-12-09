package applicaton.android.com.sehonmin;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import applicaton.android.com.sehonmin.Model.service.FormManager;

import applicaton.android.com.sehonmin.Model.service.PhoneBookManager;
import applicaton.android.com.sehonmin.Model.service.ResultManager;
import applicaton.android.com.sehonmin.ui.util.MinPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager viewPager;

    private TabLayout tabLayout;
    FormManager fm=FormManager.getInstance();
    ResultManager rm= ResultManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("그룹");
        setContentView(R.layout.activity_main);

        PhoneBookManager.getInstance(this);

        setOnclickListener();
        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.group_tab2));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.survey2));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.result));
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
        setViewPager();

    }


    public void setViewPager(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MinPagerAdapter(getSupportFragmentManager(),this));
        viewPager.setCurrentItem(0);
        viewPager.getAdapter().notifyDataSetChanged();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch(position){
                    case 0:
                        setTitle("그룹");
                        break;
                    case 1:
                        setTitle("조사");
                        break;
                    case 2:
                        setTitle("결과");
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
