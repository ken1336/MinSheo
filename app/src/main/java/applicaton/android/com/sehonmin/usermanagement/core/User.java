package applicaton.android.com.sehonmin.usermanagement.core;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import applicaton.android.com.sehonmin.LoginActivity;
import applicaton.android.com.sehonmin.MainActivity;
import applicaton.android.com.sehonmin.SplashActivity;

/**
 * Created by ken13 on 2017-11-29.
 */

public class User{
    private final static String USERTAG="debug from user.class:";
    private FirebaseAuth mAuth;
    private Activity activity;
    public static User instance;
    private boolean accessibility;

    private User(Activity activity){
        mAuth = FirebaseAuth.getInstance();
        Log.i(USERTAG,"어떠: "+ mAuth.toString());
        this.activity=activity;

    }

    public static User getInstance(Activity activity){
        if(instance==null)
            instance =new User(activity);
        return instance;

    }

    public boolean logIn(String email, String password){
        Log.i("kkkk","login :" +email+","+ password);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(USERTAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.i("kkkk","login:"+user.getEmail());
                            activity.startActivity(new Intent(activity,MainActivity.class));
                            accessibility=true;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(USERTAG, "signInWithEmail:failure", task.getException());
                            Log.i("kkkk","login failed");
                            accessibility=false;
                        }

                        // ...
                    }
                });

        return accessibility;
    }


    public boolean signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(USERTAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            accessibility=true;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(USERTAG, "createUserWithEmail:failure", task.getException());

                            accessibility=false;
                        }

                    }
                });

        return accessibility;
    }


    public Boolean getAccessibility(){
        return accessibility;
    }
}
