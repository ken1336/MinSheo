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

import applicaton.android.com.sehonmin.Model.dao.ResultDAO;
import applicaton.android.com.sehonmin.Model.dto.FormDTO;
import applicaton.android.com.sehonmin.Model.dto.ResultDTO;
import applicaton.android.com.sehonmin.Model.dto.ResultList;
import applicaton.android.com.sehonmin.observer.Subject;
import applicaton.android.com.sehonmin.observer.observer;

/**
 * Created by ken13 on 2017-12-04.
 */

public class ResultManager implements Subject {
    private ResultDAO dao;
    private observer ob;

    private DatabaseReference myRef;
    private int field_num;
    public boolean ready=false;

    private Map<String , ResultList> map;

    public static ResultManager instance;
    public static ResultManager getInstance(){
        if(instance==null)
            instance=new ResultManager();
        return instance;
    }
    private ResultManager(){

        map=new HashMap<String, ResultList>();


        dao=ResultDAO.getInstance();



        myRef=dao.getRef();

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               /* if(dataSnapshot.getValue()==null) {
                    Log.i("result managers","asdfasdf");
                    notifyObservers();
                    return;
                }
                try {
                    Log.i("result managers","rm roading");
                    JSONObject jo1 = new JSONObject(dataSnapshot.getValue().toString());
                    int count = jo1.names().length();
                    for (int i = 0; i < count; i++) {
                        Log.i("result managers","dataSnapshot.getValue().toString()");
                        String formName=jo1.names().get(0).toString();
                        String str = jo1.get(jo1.names().get(i).toString()).toString();
                        JSONObject ja = new JSONObject(str);
                        ResultList list=new ResultList(jo1.names().get(i).toString());
                        int count2=ja.names().length();
                        for(int k=0;k<count2;k++){
                            Log.i("result managers","ja");
                            ResultDTO dto=new ResultDTO(ja.names().get(k).toString());
                            String str2=ja.get(ja.names().get(k).toString()).toString();
                            JSONObject ja2=new JSONObject(str2);
                            int ja2Size=ja2.length();
                            for(int t=0;t<ja2Size;t++){
                                dto.put(ja2.names().get(t).toString(),ja2.get(ja2.names().get(t).toString()).toString());
                            }
                            list.put(dto);
                            FormManager fm=FormManager.getInstance();
                            String startDay=fm.getForm(list.getFormName()).getStartDay();
                            String endDay=fm.getForm(list.getFormName()).getEndDay();
                            String groupID=fm.getForm(list.getFormName()).getGroupID();
                            list.setEndDay(endDay);
                            list.setStartDay(startDay);
                            list.setGroupID(groupID);
                            Log.i("result managers","ja");
                        }
                        map.put(formName,list);
                    }*/
                    notifyObservers();
               /* } catch (JSONException e) {
                    e.printStackTrace();
                }*/

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

}
