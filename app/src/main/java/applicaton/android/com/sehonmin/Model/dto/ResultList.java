package applicaton.android.com.sehonmin.Model.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ken13 on 2017-12-08.
 */

public class ResultList {

    private List list;
    private String formName;
    private String startDay;
    private String endDay;

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    private String groupID;
    public ResultList(String formName){
        this.formName=formName;
        list=new ArrayList<ResultDTO>();
    }

    public String getFormName(){
        return formName;
    }

    public List getList(){
        return list;
    }

    public void put(ResultDTO dto){
        list.add(dto);
    }


}
