package applicaton.android.com.sehonmin.db.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ken13 on 2017-12-03.
 */
//

public class FormDTO {

    private String formName;
    private Map<String, Object> map;

    public FormDTO(){}
    public FormDTO(String formName,String id){
        map=new HashMap<>();
        this.formName=formName;
        map.put("id", id);
    }
    public String getFormName(){
        return formName;
    }
    public void put(String key, Object obj){
        map.put(key, obj);
    }
    public void remove(String key){
        map.remove(key);

    }
    public Map<String, Object> getElements(){
        return map;
    }

}
