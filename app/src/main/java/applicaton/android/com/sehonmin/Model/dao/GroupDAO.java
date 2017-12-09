package applicaton.android.com.sehonmin.Model.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import applicaton.android.com.sehonmin.usermanagement.core.User;

/**
 * Created by Park on 2017-12-08.
 */

public class GroupDAO {
    private FirebaseDatabase database;
    private static GroupDAO instance;

    private DatabaseReference ref;

    private GroupDAO(){
        database = FirebaseDatabase.getInstance();
        ref=database.getReference("database").child(User.getUserID()).child("group");
    }

    public static GroupDAO getInstance(){
        if(instance==null)
            instance = new GroupDAO();
        return instance;
    }

    public DatabaseReference getRef(){
        return ref;
    }

    public DatabaseReference getChild(String child){
        ref=ref.child(child);
        return ref;
    }

    public void addGroup(String name){
        HashMap<String, Object> tmp = new HashMap<String, Object>();
        tmp.put("name",name);
        ref.child(name).updateChildren(tmp);
    }

}
