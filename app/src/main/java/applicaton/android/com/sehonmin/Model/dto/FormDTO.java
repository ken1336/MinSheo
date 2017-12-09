package applicaton.android.com.sehonmin.Model.dto;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;

import org.xmlpull.v1.XmlPullParser;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import applicaton.android.com.sehonmin.R;

/**
 * Created by ken13 on 2017-12-03.
 */
//

public class FormDTO {

    private String startDay;
    private String endDay;
    private int total;
    private boolean activation;
    private String formName;
    private Map<String, Object> map;
    private String groupID;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public FormDTO(String formName){

        total=1;
        activation=false;
        map=new HashMap<>();
        this.formName=formName;

    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }



    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
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
    public String getMapElement(String key){
        return (String)map.get(key);
    }

}
