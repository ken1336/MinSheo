package applicaton.android.com.sehonmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Park on 2017-12-03.
 */

public class SplashActivity extends Activity {
    private final static int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.splash_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                overridePendingTransition(0,android.R.anim.fade_in);
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },SPLASH_TIME);
    }
}
