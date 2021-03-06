package applicaton.android.com.sehonmin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.security.acl.Group;

import applicaton.android.com.sehonmin.Model.service.FormManager;
import applicaton.android.com.sehonmin.Model.service.GroupManager;
import applicaton.android.com.sehonmin.Model.service.ResultManager;
import applicaton.android.com.sehonmin.Model.service.TokenManager;
import applicaton.android.com.sehonmin.observer.observer;
import applicaton.android.com.sehonmin.usermanagement.core.User;

public class LoadingActivity extends Activity implements observer {

    private int ready = 4;
    private static observer ob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ob=this;
        User user = User.getInstance(this);
        user.setObserver(this);
        Intent intent = getIntent();
        user.login(intent.getStringExtra("id").toString(),intent.getStringExtra("pw").toString());

        Log.i("loading","시작...");

    }

    public static observer getContext(){
        return ob;
    }


    @Override
    public void onCompleteLoad() {
        ready--;
        Log.i("tag######",ready+" ::::asdfasdfadsasdfasdf###############");
        if(ready == 3){
            FormManager fm= FormManager.getInstance();
            ResultManager rm= ResultManager.getInstance();
            GroupManager gm = GroupManager.getInstance();
            rm.setObserver(LoadingActivity.getContext());
            fm.setObserver(LoadingActivity.getContext());
            gm.setObserver(LoadingActivity.getContext());
            TokenManager.getInstance();
        }
        if(ready == 0) {
            Log.i("ssts","start!");
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
