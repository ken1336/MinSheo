package applicaton.android.com.sehonmin.Model.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import applicaton.android.com.sehonmin.Model.dto.TokenDTO;
import applicaton.android.com.sehonmin.usermanagement.core.User;

/**
 * Created by ken13 on 2017-12-12.
 */

public class TokenDAO {


    private FirebaseDatabase database;
    private DatabaseReference ref;
    private static TokenDAO instance;
    private TokenDAO(){

        database = FirebaseDatabase.getInstance();

        ref=database.getReference("database").child(User.getUserID()).child("token");
    }

    public static TokenDAO getInstance() {
        if(instance==null){

            instance=new TokenDAO();
        }
        return instance;
    }

    public void tokening(TokenDTO dto){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("id",dto.getFormName());
        map.put("token",dto.getToken());
        ref.updateChildren(map);

    }

    public DatabaseReference getRef(){
        return ref;
    }
    public void deleteToken(){
        ref.removeValue();
    }


}
