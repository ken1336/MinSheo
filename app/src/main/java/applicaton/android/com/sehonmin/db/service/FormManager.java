package applicaton.android.com.sehonmin.db.service;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import applicaton.android.com.sehonmin.db.dao.FormDAO;

/**
 * Created by ken13 on 2017-12-03.
 */

public class FormManager {

    private FormDAO dao;
    private DatabaseReference myRef;
    private int field_num;
    public FormManager(){


        dao=new FormDAO();

        myRef=dao.getRef();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                // String values = dataSnapshot.getValue(String.class);
                //Log.d("min", "Value is: " + values);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w("min", "Failed to read value.", error.toException());
            }
        });
        field_num=1;
    }

    public void createForm(String form_name,String id){
        dao.createForm(form_name,id);


    }
    public void createFiled(String value){
        dao.createElement("form"+String.valueOf(field_num),value);
        field_num++;


    }
    public void submitData(){
        dao.submitData();
    }

    public void deleteForm(String form_name){
        dao.deleteForm();
    }


    public void deleteFiled( String filed){

    }

}
