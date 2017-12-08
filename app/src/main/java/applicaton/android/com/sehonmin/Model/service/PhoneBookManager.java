package applicaton.android.com.sehonmin.Model.service;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import applicaton.android.com.sehonmin.Model.dao.PhoneBookDAO;
import applicaton.android.com.sehonmin.Model.dto.PhoneBookDTO;

/**
 * Created by Park on 2017-12-08.
 */

public class PhoneBookManager {
    private static PhoneBookManager instance;
    private AppCompatActivity mainActivity;
    private PhoneBookDAO phoneBookDAO;

    private PhoneBookManager(AppCompatActivity appCompatActivity){
        mainActivity = appCompatActivity;
        phoneBookDAO = PhoneBookDAO.getInstance(appCompatActivity);
    }

    public static PhoneBookManager getInstance(){
        return instance;
    }

    public static PhoneBookManager getInstance(AppCompatActivity appCompatActivity){
        if(instance == null){
            instance = new PhoneBookManager(appCompatActivity);
        }
        return instance;
    }

    public List<PhoneBookDTO> getPhoneBookList(){
        List<PhoneBookDTO> phoneBookDTOList = new ArrayList<PhoneBookDTO>();
        Cursor idAndNameCursor = phoneBookDAO.getPhoneBookIdAndNameCursor();
        while (idAndNameCursor.moveToNext()) {
            PhoneBookDTO phoneBookDTO = new PhoneBookDTO();
            String id;
            String name;
            String number = null;
            // 연락처 id 값
            id = idAndNameCursor.getString(idAndNameCursor.getColumnIndex(ContactsContract.Contacts._ID));
            // 연락처 대표 이름
            name = idAndNameCursor.getString(idAndNameCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));

            Cursor phoneNumberCursor = phoneBookDAO.getPhoneNumberCursor(id);

            if (phoneNumberCursor.moveToFirst()) {
                number = phoneNumberCursor.getString(phoneNumberCursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
            }

            phoneBookDTO.setId(id);
            phoneBookDTO.setName(name);
            phoneBookDTO.setNumber(number);

            phoneBookDTOList.add(phoneBookDTO);

            phoneNumberCursor.close();
        }

        idAndNameCursor.close();

        return phoneBookDTOList;
    }
}
