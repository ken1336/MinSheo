package applicaton.android.com.sehonmin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import applicaton.android.com.sehonmin.usermanagement.core.User;

/**
 * Created by Park on 2017-12-03.
 * Modified by Park on 2017-12-03 19:28
 */

public class LoginActivity extends Activity {
    private EditText idText;
    private EditText pwText;
    private Button lgBtn;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(Build.VERSION.SDK_INT >=21) //523738
            getWindow().setStatusBarColor(Color.argb(255,82,55,56));
        activity = this;
        idText = (EditText) findViewById(R.id.editid);
        pwText = (EditText) findViewById(R.id.editpw);
        lgBtn = (Button) findViewById(R.id.loginbtn);

        lgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idText.getText().toString();
                String pw = pwText.getText().toString();

                Intent intent = new Intent(activity,LoadingActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("pw",pw);
                activity.startActivity(intent);
                finish();
            }
        });

    }

}
