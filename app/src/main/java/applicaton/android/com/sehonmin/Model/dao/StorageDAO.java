package applicaton.android.com.sehonmin.Model.dao;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import applicaton.android.com.sehonmin.usermanagement.core.User;

/**
 * Created by ken13 on 2017-12-12.
 */

public class StorageDAO {

    private StorageReference mStorageRef;
    private static StorageDAO instance;
    private StorageDAO(){



    }

    public static StorageDAO getInstance() {
        if(instance==null){
            instance=new StorageDAO();
        }
        return instance;
    }

    public void sendData( String filePath,String formName){
        try{
            File file=new File(filePath);
            Log.i("nanana",String.valueOf(file.length()));
            mStorageRef= FirebaseStorage.getInstance().getReference();
            //mStorageRef.child(User.getUserID()).child("result").child(formName+".xls");
            mStorageRef.child(User.getUserID()).child(formName+".xls").putFile(Uri.fromFile(file)) .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Get a URL to the uploaded content
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    Log.i("upload","success");
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            Log.i("upload","fail");
                        }
                    });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
