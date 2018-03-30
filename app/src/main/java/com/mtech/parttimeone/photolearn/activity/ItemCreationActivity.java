package com.mtech.parttimeone.photolearn.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mtech.parttimeone.photolearn.BuildConfig;
import com.mtech.parttimeone.photolearn.R;

import java.io.File;

public class ItemCreationActivity extends BaseActivity {

    protected static final int PICK_IMAGE_REQUEST = 1 ;
    protected static final int CLICK_IMAGE_REQUEST = 2 ;
    protected static final String TAG = "PhotoLearn";

    private Uri filePath = null;
    private String itemId;

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
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
    public void addPhoto(View v){
        registerForContextMenu(v);
        openContextMenu(v);
    }

    public void takePhoto(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CLICK_IMAGE_REQUEST);
        }  else{
            checkExternalStorage();
        }

    }

    public void selectPhotofromLibrary(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an image"),PICK_IMAGE_REQUEST);

    }

    public void checkExternalStorage(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CLICK_IMAGE_REQUEST);
        }  else{
            openCamera();
        }
    }

    public void openCamera(){

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
            e.printStackTrace();
        }


      //  filePath = Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator +
      //          "profile_img.jpg"));
        filePath = FileProvider.getUriForFile(ItemCreationActivity.this,
                BuildConfig.APPLICATION_ID + ".provider",
                new File(mediaStorageDir.getPath() + File.separator +
                        "profile_img.jpg"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, filePath);
        startActivityForResult(Intent.createChooser(intent,"Click an image"),CLICK_IMAGE_REQUEST);
        //startActivityForResult(Intent.createChooser(intent,"Click an image"),CLICK_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CLICK_IMAGE_REQUEST) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Log.d(TAG, "camera permission granted");
                if(Manifest.permission.CAMERA.equals(permissions[0])){
                    checkExternalStorage();
                }else if(Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])){
                    openCamera();
                }

            } else {

                Log.d(TAG, "camera permission denied");

            }

        }
    }

    public void saveItemImagePath(Uri downloadUrl){
        Log.d(TAG, "saveItem:Call ViewModel to save Item!"+downloadUrl);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bit=null;
        ImageButton addBth = (ImageButton)findViewById(R.id.add_photo_button);
        try{
                if((requestCode == PICK_IMAGE_REQUEST || requestCode == CLICK_IMAGE_REQUEST) && null != data && null != data.getData()){
                filePath = data.getData();
                bit = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);

                addBth.setImageBitmap(bit);

            }else if(requestCode == CLICK_IMAGE_REQUEST && null != data){
                Bundle extras = data.getExtras();
                //filePath = (Uri)extras.get(MediaStore.EXTRA_OUTPUT);
                //bit = (Bitmap) extras.get("data");
                bit = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                addBth.setImageBitmap(bit);
            }
            Log.d(TAG, "OnActivityResult file path:"+filePath);
        }catch (Exception exp){
            Log.d(TAG, "Exception occured:"+exp.getMessage());
            Toast toast = Toast.makeText(ItemCreationActivity.this,"Exception occured:"+exp.getMessage(), Toast.LENGTH_LONG);
        }
    }


}
