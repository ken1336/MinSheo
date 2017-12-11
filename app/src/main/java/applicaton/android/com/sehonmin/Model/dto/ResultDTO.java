package applicaton.android.com.sehonmin.Model.dto;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by ken13 on 2017-12-04.
 */

public class ResultDTO {
    private String name;


    private Map<String, Object> map;

    public ResultDTO(String formName){
        this.name=formName;
        map=new HashMap<String, Object>();

    }
    public ResultDTO(){

        map=new HashMap<String, Object>();

    }

    public String getName(){
        return name;
    }
    public void put(String key, Object obj){
        map.put(key, obj);
    }
    public Map getElements(){
        return map;
    }
}
