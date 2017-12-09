package applicaton.android.com.sehonmin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import applicaton.android.com.sehonmin.usermanagement.core.User;

/**
 * Created by Park on 2017-12-03.
 * Modified by Park on 2017-12-03 19:28
 */

public class LoginActivity extends Activity {
    private User user;
    private EditText idText;
    private EditText pwText;
    private ImageButton lgBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idText = (EditText) findViewById(R.id.editid);
        pwText = (EditText) findViewById(R.id.editpw);
        lgBtn = (ImageButton) findViewById(R.id.loginbtn);

        user = User.getInstance(this);

        lgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idText.getText().toString();
                String pw = pwText.getText().toString();
                user.logIn(id,pw);
            }
        });

    }

}
