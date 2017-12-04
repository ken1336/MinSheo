package applicaton.android.com.sehonmin.db.dao;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import applicaton.android.com.sehonmin.db.dto.FormDTO;


/**
 * Created by ken13 on 2017-12-03.
 */

public class FormDAO {

    private FirebaseDatabase database;
    private static FormDAO instance;

    private DatabaseReference ref;

    private FormDAO(){

        database = FirebaseDatabase.getInstance();
        ref=database.getReference("database").child("form");

    }

    public static FormDAO getInstance(){
        if(instance==null)
            instance=new FormDAO();
        return instance;
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

    public void submitData(FormDTO dto){
        ref.child(dto.getFormName()).setValue(dto.getElements());
    }

    public void deleteField(FormDTO dto){
        ref.child(dto.getFormName()).updateChildren(dto.getElements());
    }
    public void deleteForm(FormDTO dto){
        ref.child(dto.getFormName()).removeValue();
    }

}
