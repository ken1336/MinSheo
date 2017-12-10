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
import applicaton.android.com.sehonmin.ui.util.recyclerview.FormListAdapter;
import applicaton.android.com.sehonmin.usermanagement.core.User;

/**
 * Created by ken13 on 2017-12-03.
 */

public class FormManager implements Subject {
    public observer ob;
    private FormDAO formDAO;
    private DatabaseReference myRef;
    private ArrayList<FormDTO> formDTOArrayList;
    private HashMap<String, String> hashMap;
    private static FormManager instance;
    private FormListAdapter formListAdapter;

    public static FormManager getInstance() {
        if (instance == null)
            instance = new FormManager();
        return instance;
    }

    private FormManager() {
        formDAO = FormDAO.getInstance();
        myRef = formDAO.getRef();
        formDTOArrayList = new ArrayList<FormDTO>();
        hashMap = new HashMap<String,String>();

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapShot : dataSnapshot.getChildren()) {
                    String name = childSnapShot.getKey().toString();

                    if(hashMap.get(name) == null) {
                        FormDTO formDTO = new FormDTO();

                        formDTO.setName(name);
                        formDTO.setStartDay((String) childSnapShot.child("startDay").getValue());
                        formDTO.setEndDay((String) childSnapShot.child("endDay").getValue());
                        formDTO.setComment((String) childSnapShot.child("comment").getValue());
                        formDTO.setGroupID((String) childSnapShot.child("groupID").getValue());
                        formDTOArrayList.add(formDTO);

                        hashMap.put(name,name);
                    }
                }

                if(formListAdapter!=null){
                    formListAdapter.notifyDataSetChanged();
                }

                notifyObservers();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("min", "Failed to read value.", error.toException());
            }

        });
    }

    public void createForm(String formName, String startDay, String endDay, String comment, String groupID) {
        FormDAO formDAO = FormDAO.getInstance();
        DatabaseReference ref = formDAO.getRef().child(formName);
        HashMap<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("startDay", startDay);
        tmpMap.put("endDay", endDay);
        tmpMap.put("comment", comment);
        tmpMap.put("groupID", groupID);

        ref.updateChildren(tmpMap);
    }

    public FormListAdapter getFormListAdapter() {
        return formListAdapter;
    }

    public void setFormListAdapter(FormListAdapter formListAdapter) {
        this.formListAdapter = formListAdapter;
    }

    public void deleteForm(FormDTO formDTO){
        formDTOArrayList.remove(formDTO);
        formDAO.getRef().child(formDTO.getName()).removeValue();
    }

    @Override
    public void notifyObservers() {
        ob.onCompleteLoad();
    }

    public void setObserver(observer ob) {
        this.ob = ob;
    }

    public List getItemList() {
        return formDTOArrayList;
    }

}
