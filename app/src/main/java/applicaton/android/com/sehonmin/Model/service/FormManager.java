package applicaton.android.com.sehonmin.Model.service;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import applicaton.android.com.sehonmin.Model.dao.FormDAO;
import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.Model.dto.ResultDTO;
import applicaton.android.com.sehonmin.observer.Subject;
import applicaton.android.com.sehonmin.observer.observer;
import applicaton.android.com.sehonmin.usermanagement.core.User;

/**
 * Created by ken13 on 2017-12-03.
 */

public class FormManager implements Subject{

    public observer ob;
    private FormDAO dao;
    private DatabaseReference myRef;
    private Map<String , FormDTO> formMap;
    private static FormManager instance=new FormManager();


    public static FormManager getInstance(){
        if(instance==null)
            instance=new FormManager();
        return instance;
    }

    private FormManager(){

        formMap=new HashMap<String, FormDTO>();
        dao=FormDAO.getInstance();
        myRef=dao.getRef();

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()==null){
                    notifyObservers();
                    return;
                }
                try {

                    Log.i("sssss",dataSnapshot.getValue().toString());
                    JSONObject jo1 = new JSONObject(dataSnapshot.getValue().toString());
                    int count = jo1.names().length();

                    for (int i = 0; i < count; i++) {

                        String str = jo1.get(jo1.names().get(i).toString()).toString();
                        JSONObject ja = new JSONObject(str);
                        FormDTO inDTO = new FormDTO(jo1.names().get(i).toString());
                        int countj = ja.names().length();
                        for (int t = 0; t < countj; t++)
                            inDTO.put(ja.names().getString(t), ja.get(ja.names().getString(t)));
                        formMap.put(jo1.names().get(i).toString(), inDTO);

                    }
                    notifyObservers();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("min", "Failed to read value.", error.toException());
            }

        });
    }
    public void createForm(String form_name){
        FormDTO dto=new FormDTO(form_name);
        dto.put("comment","input");
        dto.put("Total","input");
        dto.put("activation","input");
        formMap.put(form_name,dto);
    }
    public FormDTO getForm(String form_name){
        return formMap.get(form_name);
    }
    public void createFiled(String form_name, String key, String value){

        FormDTO dto=formMap.get(form_name);
        dto.put(key,value);


    }

    public void submitData(FormDTO dto){
        dao.submitData(dto);
    }


//////////////////////////////////////delete method///////////////////////////////////////////////////////////////////
    /*
    public void deleteForm(String form_name){
        FormDTO dto=formMap.get(form_name);
        dao.deleteForm(dto);
    }
    public void deleteKey(String form_name, String key){
        FormDTO dto=formMap.get(form_name);
        dto.remove(key);
        dao.deleteField(dto);
    }
    */

    @Override
    public void notifyObservers() {

        ob.onCompleteLoad();
    }

    public void setObserver(observer ob){
        this.ob=ob;
    }

    public List getItemList(){
        List<FormDTO> list=new ArrayList<>();

        Iterator it=formMap.keySet().iterator();
        while(it.hasNext()) {
            Object key = it.next();

            FormDTO d=formMap.get(key);
            list.add(d);

        }

        return list;
    }
}
