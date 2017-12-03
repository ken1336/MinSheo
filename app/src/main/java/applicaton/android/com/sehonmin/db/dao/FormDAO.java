package applicaton.android.com.sehonmin.db.dao;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ken13 on 2017-12-03.
 */

public class FormDAO {

    private FirebaseDatabase database;


    private DatabaseReference ref;

    public FormDAO(){
        database = FirebaseDatabase.getInstance();
        ref=database.getReference("database").child("form");

    }

    public void createElement(String key, String value){


    }
    public void deleteElement(String key){

    }
    public void deleteForm(){

        ref.child("form1").removeValue();
    }
    public void submitData(){



    }

    public void createForm(String form_name,String id){

    }



    public DatabaseReference getRef(){
        return ref;
    }

    public DatabaseReference getChild(String child){
        ref=ref.child(child);
        return ref;
    }
    public DatabaseReference getParent(){
        ref=ref.getParent();
        return ref;
    }
    public void setValue(String value){

        ref.setValue(value);

    }

}
