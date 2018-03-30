package com.mtech.parttimeone.photolearn.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.mtech.parttimeone.photolearn.BuildConfig;
import com.mtech.parttimeone.photolearn.R;

import java.io.File;

public class ItemCreationActivity extends BaseActivity {

    protected static final int PICK_IMAGE_REQUEST = 1;
    protected static final int CLICK_IMAGE_REQUEST = 2;
    private static final String QUIZ_TYPE = "QUIZ";
    private static final String LEARNING_TYPE = "LEARNING";
    protected static final String TAG = "PhotoLearn";

    private String itemType = null;

    private Uri filePath = null;

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemType() {
        return itemType;
    }


    public void setFilePath(Uri filePath) {
        this.filePath = filePath;
    }

    public Uri getFilePath() {
        return filePath;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_creation);
    }

    // add button call back function - v = addBtn.
    public void addPhoto(View v) {
        registerForContextMenu(v);
        openContextMenu(v);
    }

    public void takePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CLICK_IMAGE_REQUEST);
        } else {
            checkExternalStorage();
        }

    }

    public void selectPhotofromLibrary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an image"), PICK_IMAGE_REQUEST);

    }

    public void checkExternalStorage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CLICK_IMAGE_REQUEST);
        } else {
            openCamera();
        }
    }

    public void openCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "IMG_FOLDER");

        try {
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    return;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "Exception occured:" + e.getMessage());
            e.printStackTrace();
        }


        setFilePath(FileProvider.getUriForFile(ItemCreationActivity.this,
                BuildConfig.APPLICATION_ID + ".provider",
                new File(mediaStorageDir.getPath() + File.separator +
                        "profile_img.jpg")));
        startActivityForResult(Intent.createChooser(intent, "Click an image"), CLICK_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CLICK_IMAGE_REQUEST) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Log.d(TAG, "camera permission granted");
                if (Manifest.permission.CAMERA.equals(permissions[0])) {
                    checkExternalStorage();
                } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                    openCamera();
                }

            } else {
                Log.d(TAG, "camera permission denied");
            }

        }
    }

    public void saveItemImagePath(Uri downloadUrl) {
        //Will be over riden by child classes
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bit = null;
        ImageButton addBth = (ImageButton) findViewById(R.id.add_photo_button);
        try {
            if (requestCode == PICK_IMAGE_REQUEST && null != data && null != data.getData()) {
                filePath = data.getData();
                bit = MediaStore.Images.Media.getBitmap(getContentResolver(), getFilePath());
                addBth.setImageBitmap(bit);

            } else if (requestCode == CLICK_IMAGE_REQUEST) {
                bit = MediaStore.Images.Media.getBitmap(getContentResolver(), getFilePath());
                addBth.setImageBitmap(bit);
            }
            Log.d(TAG, "OnActivityResult file path:" + filePath);
        } catch (Exception exp) {
            Log.d(TAG, "Exception occured:" + exp.getMessage());
        }
    }


}
