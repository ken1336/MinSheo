package applicaton.android.com.sehonmin.Model.service;

import applicaton.android.com.sehonmin.Model.dao.FormDAO;
import applicaton.android.com.sehonmin.observer.Subject;
import applicaton.android.com.sehonmin.observer.observer;

/**
 * Created by ken13 on 2017-12-07.
 */

public class GroupManager  implements Subject {
    public observer ob;
    private FormDAO dao;
    private static GroupManager instance;

    private GroupManager(){

    }

    public static GroupManager getInstance(){
        if(instance==null)
            instance=new GroupManager();
        return instance;
    }

    @Override
    public void notifyObservers() {

    }
}
