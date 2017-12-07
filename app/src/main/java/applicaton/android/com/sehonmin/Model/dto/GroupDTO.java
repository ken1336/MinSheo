package applicaton.android.com.sehonmin.Model.dto;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ken13 on 2017-12-07.
 */

public class GroupDTO {

    private String groupID;
    private List list;

    public GroupDTO(String groupID){
        this.groupID=groupID;
        list=new ArrayList();
    }

    public List getMap() {
        return list;
    }

    public void setMap(List list) {
        this.list = list;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
}
