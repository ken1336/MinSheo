package applicaton.android.com.sehonmin.Model.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ken13 on 2017-12-04.
 */

public class ResultDAO {

    private static ResultDAO instance;
    private FirebaseDatabase database;
    private DatabaseReference ref;


    private ResultDAO(){
        database = FirebaseDatabase.getInstance();

        ref=database.getReference("database").child("result");
    }

    public static ResultDAO getInstance() {
        if(instance==null){
            instance=new ResultDAO();
        }
        return instance;
    }

    public DatabaseReference getRef(){
        return ref;
    }
}
