package applicaton.android.com.sehonmin;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import applicaton.android.com.sehonmin.usermanagement.core.User;

public class MainActivity extends AppCompatActivity {

    private final static String TAG="debug from min";
    private Button login_btn;
    private EditText edit_id;
    private EditText edit_password;
    private Button signup_btn;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        login_btn=(Button)findViewById(R.id._login);
        edit_id=(EditText)findViewById(R.id._id);
        edit_password=(EditText)findViewById(R.id._password);
        signup_btn=(Button)findViewById(R.id._signup);
        user=User.getInstance(this);





        /*mAuthListener = new FirebaseAuth.AuthStateListener() { // 인증 상태 리스너
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("1", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d("1", "onAuthStateChanged:signed_out");
                }
            }
        };*/
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(user.logIn(edit_id.getText().toString(), edit_password.getText().toString())){
                   Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
                }


            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.signUp(edit_id.getText().toString(), edit_password.getText().toString());
            }
        });

    }






}
