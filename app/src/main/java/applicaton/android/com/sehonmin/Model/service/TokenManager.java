package applicaton.android.com.sehonmin.Model.service;

import android.util.Log;

import java.util.Random;

import applicaton.android.com.sehonmin.Model.dao.TokenDAO;
import applicaton.android.com.sehonmin.Model.dto.TokenDTO;

/**
 * Created by ken13 on 2017-12-12.
 */

public class TokenManager {

    TokenDAO dao;
    private static TokenManager instance;

    private TokenManager(){}

    public static TokenManager getInstance(){
        if(instance==null)
            instance=new TokenManager();
        return instance;
    }

    public String getToken(){
        String token;
        int random=(int)(Math.random()*10000+1);
        Log.i("randomm",String.valueOf(random));
        token=String.valueOf(random);

        return token;
    }

    public void updateToken(String token,String formName){
        dao=TokenDAO.getInstance();
        TokenDTO dto=new TokenDTO(token,formName);
        dao.tokening(dto);
    }
    public void deleteToken(){
        if(dao==null)
            dao=TokenDAO.getInstance();
        dao.deleteToken();
    }
}
