package com.mtech.parttimeone.photolearn.asyncTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.activity.BaseActivity;
import com.mtech.parttimeone.photolearn.activity.ItemCreationActivity;
import com.mtech.parttimeone.photolearn.activity.LearnItemCreationActivity;

import java.util.UUID;

/**
 * Created by kunal on 25/3/2018.
 */

public class UploadAsyncTask extends AsyncTask<Uri, Void, Void> {

    private StorageReference mStorageRef;
    ItemCreationActivity activity;
    private Uri downloadUrl;
    private static final String TAG = "PhotoLearn";

    public UploadAsyncTask(ItemCreationActivity activity){
        this.activity = activity;
    }

    protected void onPreExecute(){
        mStorageRef = FirebaseStorage.getInstance().getReference();

    }

    @Override
    protected Void doInBackground(Uri... file) {
        // Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        // StorageReference riversRef = mStorageRef.child("images/rivers.jpg");
        Log.d(TAG, "Titleid:"+ activity.getItemType());
        String uniqueID = UUID.randomUUID().toString();

        mStorageRef.child("images").child(activity.getItemType()).child(uniqueID).putFile(file[0])
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        downloadUrl = taskSnapshot.getDownloadUrl();
                        activity.saveItemImagePath(downloadUrl);
                        Log.d(TAG, "downloadUrl:"+ downloadUrl);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Log.d(TAG, "Exception:"+ exception.getMessage());

                    }
                });
        return null;
    }


    protected void onPostExecute(Void result){
        Log.d(TAG, "onPostExecute:Should dismiss the progress bar");
    }

}