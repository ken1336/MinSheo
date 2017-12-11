package applicaton.android.com.sehonmin.Model.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

import applicaton.android.com.sehonmin.usermanagement.core.User;

/**
 * Created by ken13 on 2017-12-04.
 */

public class ResultDAO {

    private static ResultDAO instance;
    private FirebaseDatabase database;
    private DatabaseReference ref;


    private ResultDAO(){
        database = FirebaseDatabase.getInstance();

        ref=database.getReference("database").child(User.getUserID()).child("result");
    }

    public static ResultDAO getInstance() {
        if(instance==null){
            instance=new ResultDAO();
        }
        return instance;
    }
    public void deleteResult(String resultName){
        ref.child(resultName).removeValue();

    }

    public void addForm(String formName){
        ref.child(formName);
    }

    public DatabaseReference getRef(){
        return ref;
    }
}
