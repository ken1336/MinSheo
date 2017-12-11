package applicaton.android.com.sehonmin.Model.service;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import applicaton.android.com.sehonmin.Model.dao.ResultDAO;
import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.Model.dto.ResultDTO;
import applicaton.android.com.sehonmin.Model.dto.ResultList;
import applicaton.android.com.sehonmin.observer.Subject;
import applicaton.android.com.sehonmin.observer.observer;
import applicaton.android.com.sehonmin.ui.util.recyclerview.ResultDetailAdapter;
import applicaton.android.com.sehonmin.ui.util.recyclerview.ResultListAdapter;
import applicaton.android.com.sehonmin.ui.util.recyclerview.ResultParticipateAdapter;

/**
 * Created by ken13 on 2017-12-04.
 */

public class ResultManager implements Subject {
    private ResultDAO dao;
    private observer ob;
    private FormManager fm;
    private GroupManager gm;
    private ResultDetailAdapter detailAdapter;
    private ResultListAdapter resultListAdapter;
    private ResultParticipateAdapter resultParticipateAdapter;
    private DatabaseReference myRef;
    private int field_num;
    public boolean ready=false;
    private Map<String , ResultList> map;
    public static ResultManager instance;


    public void setDetailAdapter(ResultDetailAdapter detailAdapter) {
        this.detailAdapter = detailAdapter;
    }

    public void setResultListAdapter(ResultListAdapter resultListAdapter) {
        this.resultListAdapter = resultListAdapter;
    }

    public void setResultParticipateAdapter(ResultParticipateAdapter resultParticipateAdapter) {
        this.resultParticipateAdapter = resultParticipateAdapter;
    }

    public static ResultManager getInstance(){
        if(instance==null)
            instance=new ResultManager();
        return instance;
    }

    private ResultManager(){

        map=new HashMap<String, ResultList>();
        fm=FormManager.getInstance();
        gm=GroupManager.getInstance();
        dao=ResultDAO.getInstance();
        myRef=dao.getRef();
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot childSnapShot : dataSnapshot.getChildren()) {
                        String formName = childSnapShot.getKey().toString();

                        Log.i("stst2",formName);
                        ResultList list=new ResultList(formName);
                        List list2=fm.getItemList();
                        for(int i=0;i<list2.size();i++){
                            FormDTO fdto=(FormDTO)list2.get(i);
                           list.setGroupID(fdto.getGroupID());
                           list.setStartDay(fdto.getStartDay());
                           list.setEndDay(fdto.getEndDay());
                           list.setComment(fdto.getComment());
                        }

                        Iterator it=dataSnapshot.child(formName).getChildren().iterator();
                        while(it.hasNext()){

                            DataSnapshot key=(DataSnapshot)it.next();
                            Iterator it2=key.getChildren().iterator();
                            ResultDTO rdto=new ResultDTO(key.getKey());
                            while(it2.hasNext()){
                                DataSnapshot key2=(DataSnapshot)it2.next();
                                rdto.put(key2.getKey(),key2.getValue().toString());
                            }
                            list.put(rdto);
                            map.put(formName,list);

                        }
                    }
                    if(resultListAdapter!=null)
                        resultListAdapter.notifyDataSetChanged();
                    if(resultParticipateAdapter!=null)
                        resultParticipateAdapter.notifyDataSetChanged();
                    if(detailAdapter!=null)
                        detailAdapter.notifyDataSetChanged();
                    notifyObservers();
                    return;

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("min", "Failed to read value.", error.toException());
            }

        });
        dao.getRef().push();




    }

    public ResultList getForm(String form_name){
        return map.get(form_name);
    }

    public Map getMap(){
        return map;
    }

    @Override
    public void notifyObservers() {

        Log.i("ssts","rm on");
        ob.onCompleteLoad();
    }
    public void setObserver(observer ob){
        this.ob=ob;
    }


   /* public void deleteForm(String form_name){
        ResultDTO dto=formMap.get(form_name);
        dao.deleteForm(dto);
    }
    public void deleteKey(String form_name, String key){
        ResultDTO dto=formMap.get(form_name);
        dto.remove(key);
        dao.deleteField(dto);
    }
*/
   public void deleteResult(String formName){
       map.remove(formName);
       dao.deleteResult(formName);

   }
   public List getItemList(){
       List<ResultList> list=new ArrayList<>();

       Iterator it=map.keySet().iterator();
       while(it.hasNext()) {
           Object key = it.next();

           ResultList d=map.get(key);
           list.add(d);

       }

       return list;
   }

   public String getStatistic(String resultName){

       return map.get(resultName).getPaticipation()+"/"+gm.getTotalGroupMember(map.get(resultName).getGroupID());
   }
   public String getComment(String resultName){

       if(map.get(resultName).getComment().equals("")){

       }

       return map.get(resultName).getComment();
   }
   public String getEndDay(String resultName){
       return map.get(resultName).getEndDay();
   }
   public String getGroupID(String resultName){
       return map.get(resultName).getGroupID();
   }

}
