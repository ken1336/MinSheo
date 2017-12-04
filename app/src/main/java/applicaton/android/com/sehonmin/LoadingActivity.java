package applicaton.android.com.sehonmin.usermanagement.core;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import applicaton.android.com.sehonmin.FirebaseFactory;
import applicaton.android.com.sehonmin.MainActivity;
import applicaton.android.com.sehonmin.R;
import applicaton.android.com.sehonmin.observer.observer;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        while(!FirebaseFactory.ready[0]){
            try {
                Thread.sleep(1000);
                Log.i("loading","대기중...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        Log.i("loading","시작...");
        startActivity(intent);
    }


}
