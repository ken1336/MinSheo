package applicaton.android.com.sehonmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import applicaton.android.com.sehonmin.Model.service.FormManager;
import applicaton.android.com.sehonmin.observer.observer;

public class LoadingActivity extends AppCompatActivity implements observer {


    private static observer ob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ob=this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                FormManager fm= FormManager.getInstance();
                fm.setObserver(LoadingActivity.getContext());
            }
        }).start();


        Log.i("loading","시작...");

    }

    public static observer getContext(){
        return ob;
    }


    @Override
    public void onCompleteLoad() {


        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
