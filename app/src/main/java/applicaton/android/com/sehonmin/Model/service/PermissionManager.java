package applicaton.android.com.sehonmin.Model.service;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Park on 2017-12-07.
 */

public class PermissionManager {

    private static PermissionManager instance;
    private AppCompatActivity mainActivity;

    private PermissionManager(AppCompatActivity appCompatActivity){
        mainActivity = appCompatActivity;
    }

    public static PermissionManager getInstance(){
        return instance;
    }

    public static PermissionManager getInstance(AppCompatActivity appCompatActivity){
        if(instance == null){
            instance = new PermissionManager(appCompatActivity);
        }
        return instance;
    }

    private void requestPermission(final String permission){ //Manifest.permission.READ_CONTACTS
        int permissionResult = mainActivity.checkSelfPermission(permission);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (permissionResult == PackageManager.PERMISSION_DENIED) {

                if (mainActivity.shouldShowRequestPermissionRationale(permission)) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(mainActivity);
                    dialog.setTitle("권한이 필요합니다.").setMessage("이 기능을 사용하기 위해서는 단말기의 \"전화걸기\" 권한이 필요합니다. 계속 하시겠습니까?")
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) { /** * 새로운 인스턴스(onClickListener)를 생성했기 때문에 * 버전체크를 다시 해준다. */
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // CALL_PHONE 권한을 Android OS에 요청한다.
                                        mainActivity.requestPermissions(new String[]{permission}, 1000);
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
                    mainActivity.requestPermissions(new String[]{permission}, 1000);
                }
            } else {
                // 즉시 실행

            }
        } // 마시멜로우 미만의 버전일 때
        else {
            //즉시 실행

        }
    }
}
