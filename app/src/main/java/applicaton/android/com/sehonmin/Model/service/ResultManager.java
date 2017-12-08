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

    private Map<String , ResultDTO> map;
    public static ResultManager instance;
    public static ResultManager getInstance(){
        if(instance==null)
            instance=new ResultManager();
        return instance;
    }
    private ResultManager(){

        map=new HashMap<String, ResultDTO>();

        dao=ResultDAO.getInstance();



        myRef=dao.getRef();

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()==null) {
                    Log.i("result managers","asdfasdf");
                    notifyObservers();
                    return;
                }
                //Log.i("kkkkk","dididi");
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                try {
                    Log.i("result managers",dataSnapshot.toString());

                    JSONObject jo1 = new JSONObject(dataSnapshot.getValue().toString());

                    int count = jo1.names().length();

                    for (int i = 0; i < count; i++) {

                        //Log.d("minkkk", "num   "+jo1.names().get(0).toString());
                        //Log.d("minkkk", "num   "+jo1.names().get(1).toString());
                        //Log.d("minkkk", "num   "+jo1.names().get(2).toString());
                        String formName=jo1.names().get(0).toString();
                        ResultDTO dto=new ResultDTO(formName);
                        String str = jo1.get(jo1.names().get(i).toString()).toString();
                        JSONObject ja = new JSONObject(str);
                        int countt=ja.names().length();
                        for(int f=0;f<countt;f++){
                            dto.put(ja.names().get(f).toString(),ja.get(ja.names().get(f).toString()).toString());
                        }
                        map.put(formName, dto);

                        //dto.put(ja.names().get(i).toString(),ja.get(ja.names().get(i).toString()).toString());
                        Log.d("resultmanagers", ja.toString());




                        /*int countj = ja.names().length();

                        for (int t = 0; t < countj; t++) {
                            String str2 = ja.get(ja.names().get(t).toString()).toString();
                            Log.d("resultmin", "count: "+ str2);
                            JSONObject ja2 = new JSONObject(str2);
                            int count2=ja2.names().length();
                            Log.d("resultmanagers", "name: "+ ja.names().get(t).toString());
                            ResultDTO inDTO = new ResultDTO(ja.names().get(t).toString());
                            for(int c2=0;c2<count2;c2++){
                                Log.d("resultmanagers", "key: "+ ja2.names().getString(c2)+","+ja2.get(ja2.names().getString(c2)));
                                inDTO.put(ja2.names().getString(c2), ja2.get(ja2.names().getString(c2)));
                            }
                            map.put(ja.names().get(t).toString(), inDTO);
                        }*/
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("min", "Failed to read value.", error.toException());
            }

        });
        dao.getRef().push();




    }

    public ResultDTO getForm(String form_name){
        return map.get(form_name);
    }

    public Map getMap(){
        return map;
    }

    @Override
    public void notifyObservers() {
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
       List<ResultDTO> list=new ArrayList<>();

       Iterator it=map.keySet().iterator();
       while(it.hasNext()) {
           Object key = it.next();

           ResultDTO d=map.get(key);
           list.add(d);

       }

       return list;
   }

}
