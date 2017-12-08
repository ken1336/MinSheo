package applicaton.android.com.sehonmin.Model.dao;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Park on 2017-12-07.
 */

public class PhoneBookDAO {
    private static PhoneBookDAO instance;
    private AppCompatActivity mainActivity;

    private PhoneBookDAO(AppCompatActivity appCompatActivity){
        mainActivity = appCompatActivity;
    }

    public static PhoneBookDAO getInstance(){
        return instance;
    }

    public static PhoneBookDAO getInstance(AppCompatActivity appCompatActivity){
        if(instance == null){
            instance = new PhoneBookDAO(appCompatActivity);
        }
        return instance;
    }

    public Cursor getPhoneBookIdAndNameCursor(){
        return mainActivity.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " asc");
    }

    public Cursor getPhoneNumberCursor(String id){
        return mainActivity.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                null, null);
    }

}
