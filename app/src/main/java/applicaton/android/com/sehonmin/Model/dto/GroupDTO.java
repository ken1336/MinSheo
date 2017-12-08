package applicaton.android.com.sehonmin.Model.dto;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ken13 on 2017-12-07.
 */

public class GroupDTO {

    private String name;
    private HashMap<String, String> groupDTOHashMap;

    public GroupDTO(String name){
        this.name=name;
        groupDTOHashMap = new HashMap<String,String>();
    }

    public HashMap<String, String> getGroupDTOHashMap() {
        return groupDTOHashMap;
    }

    public void setGroupDTOHashMap(HashMap<String, String> groupDTOHashMap) {
        this.groupDTOHashMap = groupDTOHashMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
