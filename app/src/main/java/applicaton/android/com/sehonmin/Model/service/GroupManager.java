package applicaton.android.com.sehonmin.Model.service;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import applicaton.android.com.sehonmin.Model.dao.FormDAO;
import applicaton.android.com.sehonmin.Model.dao.GroupDAO;
import applicaton.android.com.sehonmin.Model.dto.GroupDTO;
import applicaton.android.com.sehonmin.Model.dto.PhoneBookDTO;
import applicaton.android.com.sehonmin.R;
import applicaton.android.com.sehonmin.observer.Subject;
import applicaton.android.com.sehonmin.observer.observer;
import applicaton.android.com.sehonmin.ui.util.recyclerview.GroupAdapter;
import applicaton.android.com.sehonmin.ui.util.recyclerview.GroupDetailsAdapter;
import applicaton.android.com.sehonmin.ui.util.recyclerview.PhoneBookAdapter;

/**
 * Created by Park on 2017-12-08.
 */

public class GroupManager implements Subject {
    private static GroupManager instance;
    private GroupDAO groupDAO;
    private DatabaseReference myRef;
    private observer ob;
    private GroupAdapter groupAdapter;
    private GroupDetailsAdapter groupDetailsAdapter;
    private HashMap<String, GroupDTO> groupHashMap; // 그룹 이름을 통해서 그룹연락처를 얻음
    private ArrayList<String> groupNameList;

    private GroupManager() {
        groupDAO = GroupDAO.getInstance();
        myRef = groupDAO.getRef();
        groupHashMap = new HashMap<String, GroupDTO>();
        groupNameList = new ArrayList<String>();

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> ite = dataSnapshot.getChildren(); //
                Iterator it = ite.iterator();
                while (it.hasNext()) {
                    DataSnapshot ds = (DataSnapshot) it.next();
                    String groupName = (String) ds.getKey().toString();
                    GroupDTO groupDTO = groupHashMap.get(groupName);
                    HashMap<String, String> groupDTOHashMap;

                    if(groupDTO == null){
                        groupDTO = new GroupDTO(groupName);
                        groupHashMap.put(groupName, groupDTO);
                        groupNameList.add(groupName);

                    }

                    groupDTOHashMap  = groupDTO.getGroupDTOHashMap();
                    for (DataSnapshot snapshot : ds.getChildren()) {
                        if(!snapshot.getValue().equals(groupName)){
                           groupDTOHashMap.put(snapshot.getKey(),(String)snapshot.getValue().toString());
                        }
                    }


                    if(groupAdapter!=null){
                        groupAdapter.notifyDataSetChanged();
                    }

                    if(groupDetailsAdapter!=null){
                        groupDetailsAdapter.notifyDataSetChanged();
                    }

                }
                notifyObservers();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("min", "Failed to read value.", error.toException());
            }
        });
    }

    public void put(String groupName, ArrayList<PhoneBookDTO> phoneBookDTOArrayList){
        DatabaseReference groupRef = groupDAO.getRef().child(groupName);
        HashMap<String, Object> inputData = new HashMap<String, Object>();
        int size = phoneBookDTOArrayList.size();
        for(int i = 0; i < size; i++){
            PhoneBookDTO phoneBookDTO = phoneBookDTOArrayList.get(i);

            inputData.put(phoneBookDTO.getName(),phoneBookDTO.getNumber());
        }

        groupRef.updateChildren(inputData);
        phoneBookDTOArrayList.clear();
    }

    public void removeList(String groupName, String userName ){
        groupDAO.getRef().child(groupName).child(userName).removeValue();
    }

    public void removeGroup(String groupName){
        groupHashMap.remove(groupName);
        groupNameList.remove(groupName);
        groupDAO.getRef().child(groupName).removeValue();
    }

    public static GroupManager getInstance() {
        if (instance == null)
            instance = new GroupManager();
        return instance;
    }

    public HashMap<String, GroupDTO> getGroupHashMap() {
        return groupHashMap;
    }

    public List<String> getGroupNameList() {
        return groupNameList;
    }

    public void addGroup(String name) {
        groupDAO.addGroup(name);
    }

    @Override
    public void notifyObservers() {

        Log.i("ssts","gm on");
        ob.onCompleteLoad();
    }

    public void setObserver(observer ob) {
        this.ob = ob;
    }

    public void setGroupAdapter(GroupAdapter groupAdapter) {
        this.groupAdapter = groupAdapter;
    }

    public void setGroupDetailsAdapter(GroupDetailsAdapter groupDetailsAdapter) {
        this.groupDetailsAdapter = groupDetailsAdapter;
    }
    public int getTotalGroupMember(){
        return groupHashMap.size();
    }
}
