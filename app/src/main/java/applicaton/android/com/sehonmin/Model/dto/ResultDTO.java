package applicaton.android.com.sehonmin.db.dto;


import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by ken13 on 2017-12-04.
 */

public class ResultDTO {
    private String formName;

    private Map<String, Object> map;

    public ResultDTO(String formName){
        this.formName=formName;
        map=new HashMap<String, Object>();

    }
    public String getFormName(){
        return formName;
    }
    public void put(String key, Object obj){
        map.put(key, obj);
    }
    public Map getElements(){
        return map;
    }
}
