package applicaton.android.com.sehonmin.data.util;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Park on 2017-12-03.
 * Modified by Park on 2017-12-03 19:28
 */

public class PhoneBookManager {
    private static PhoneBookManager phoneBookManager;
    private AppCompatActivity mainActivity;
    private SimpleAdapter adapter = null;

    private PhoneBookManager(AppCompatActivity appCompatActivity){
        mainActivity = appCompatActivity;
        requestPermission();
    }

    public static PhoneBookManager getInstance(){
        return phoneBookManager;
    }

    public static PhoneBookManager getInstance(AppCompatActivity appCompatActivity){
        if(phoneBookManager == null){
            phoneBookManager = new PhoneBookManager(appCompatActivity);
        }
        return phoneBookManager;
    }

    private void requestPermission(){
        int permissionResult = mainActivity.checkSelfPermission(Manifest.permission.READ_CONTACTS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (permissionResult == PackageManager.PERMISSION_DENIED) {

                if (mainActivity.shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                    Log.i("asdfsdfsadfasdf", "ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ");
                    AlertDialog.Builder dialog = new AlertDialog.Builder(mainActivity);
                    dialog.setTitle("권한이 필요합니다.").setMessage("이 기능을 사용하기 위해서는 단말기의 \"전화걸기\" 권한이 필요합니다. 계속 하시겠습니까?")
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) { /** * 새로운 인스턴스(onClickListener)를 생성했기 때문에 * 버전체크를 다시 해준다. */
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // CALL_PHONE 권한을 Android OS에 요청한다.
                                        mainActivity.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1000);
                                    }
                                }
                            })
                            .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mainActivity, "기능을 취소했습니다", Toast.LENGTH_SHORT).show();
                                }
                            }).create().show();
                } else {
                    // CALL_PHONE 권한을 Android OS에 요청한다.
                    mainActivity.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1000);
                }
            } else {
                // 즉시 실행

            }
        } // 마시멜로우 미만의 버전일 때
        else {
            //즉시 실행

        }
    }

    public SimpleAdapter getPhoneNumberAdapter() {
        if(adapter ==null) {
            ArrayList<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
            Cursor c = mainActivity.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " asc");
            while (c.moveToNext()) {
                HashMap<String, String> map = new HashMap<String, String>();
                // 연락처 id 값
                String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                // 연락처 대표 이름
                String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                map.put("name", name);
                Cursor phoneCursor = mainActivity.getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                        null, null);

                // 데이터가 있는 경우
                if (phoneCursor.moveToFirst()) {
                    String number = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    map.put("phone", number);
                }

                phoneCursor.close();
                dataList.add(map);
            }// end while
            c.close();
            adapter = new SimpleAdapter(mainActivity.getApplicationContext(),
                    dataList,
                    android.R.layout.simple_list_item_2,
                    new String[]{"name", "phone"},
                    new int[]{android.R.id.text1, android.R.id.text2});
        }

        return adapter;
    }
}
