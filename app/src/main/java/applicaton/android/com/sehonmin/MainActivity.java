package applicaton.android.com.sehonmin;

import android.Manifest;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import applicaton.android.com.sehonmin.Model.service.FormManager;
import applicaton.android.com.sehonmin.Model.service.PermissionManager;
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
        PermissionManager permissionManager = PermissionManager.getInstance(this);
        permissionManager.requestPermission(Manifest.permission.READ_CONTACTS);
        permissionManager.requestPermission(Manifest.permission.READ_PHONE_STATE);
        permissionManager.requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissionManager.requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionManager.requestPermission(Manifest.permission.INTERNET);

        setTitle("그룹");
        setContentView(R.layout.activity_main);

        PhoneBookManager.getInstance(this);

        //setOnclickListener();
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

    private int check = 1;
    private Thread th = null;
    @Override public void onBackPressed() { //super.onBackPressed();

        if(th==null) {
            Toast.makeText(this,"연속해서 누를 경우 앱이 종료됩니다.",Toast.LENGTH_SHORT).show();
            th = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(1500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    check = 1;
                    th=null;
                }
            };
            th.start();
        }
        if(check == 0){
            finish();
        }
        check--;
    }

    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();

        viewPager.setCurrentItem(tag);
    }

}
