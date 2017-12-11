package applicaton.android.com.sehonmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.security.acl.Group;

import applicaton.android.com.sehonmin.Model.service.FormManager;
import applicaton.android.com.sehonmin.Model.service.GroupManager;
import applicaton.android.com.sehonmin.Model.service.ResultManager;
import applicaton.android.com.sehonmin.observer.observer;
import applicaton.android.com.sehonmin.usermanagement.core.User;

public class LoadingActivity extends AppCompatActivity implements observer {

    private int ready = 4;
    private static observer ob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ob=this;
        User user = User.getInstance(this);
        user.setObserver(this);
        user.login(savedInstanceState.getString("id").toString(),savedInstanceState.getString("pw").toString());

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
        }
        if(ready == 0) {
            Log.i("ssts","start!");
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}
