package applicaton.android.com.sehonmin.Model.dao;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.usermanagement.core.User;


/**
 * Created by ken13 on 2017-12-03.
 */

public class FormDAO {

    private FirebaseDatabase database;
    private static FormDAO instance;

    private DatabaseReference ref;

    private FormDAO(){
        database = FirebaseDatabase.getInstance();
        ref=database.getReference("database").child(User.getUserID()).child("form");
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

   /* public void submitData(FormDTO dto){
        dto.put("StartDay",dto.getStartDay());
        dto.put("EndDay",dto.getEndDay());

        ref.child(dto.getFormName()).setValue(dto.getElements());
    }
    public void submitElementData(String key,FormDTO dto){
        dto.getElements().get(key);
    }

    public void deleteField(FormDTO dto){
        ref.child(dto.getFormName()).updateChildren(dto.getElements());
    }
    public void deleteForm(FormDTO dto){
        ref.child(dto.getFormName()).removeValue();
    }*/

}
