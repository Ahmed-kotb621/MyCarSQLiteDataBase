package com.example.mycar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE = 1;
    ImageView image_add;
    EditText etName,etColor,etModel,etCC,etPrice;
    Button btn_add;private final static int REQUEST_CODE_Gallery = 1;
    Uri imageuri;
    Bitmap convertedBitmap;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        image_add=findViewById(R.id.add_car);
        etName=findViewById(R.id.et_car_name);
        etColor=findViewById(R.id.et_car_color);
        etModel=findViewById(R.id.et_car_model);
        etCC=findViewById(R.id.et_car_cc);
        etPrice=findViewById(R.id.et_car_price);
        btn_add=findViewById(R.id.btn_saveCar);
        dataBaseHelper =new DataBaseHelper(this);

        image_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkdevicePermission();
            }


        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] img=imageViewtoByte(convertedBitmap);
                String name=etName.getText().toString();
                String color=etColor.getText().toString();
                int model=Integer.valueOf(etModel.getText().toString());
                int cc=Integer.valueOf(etCC.getText().toString());
                int price=Integer.valueOf(etPrice.getText().toString());

                saveCarToDatabase(img,name,color,model,cc,price);

            }
        });


    }
    public void checkdevicePermission() {
        Log.d(TAG, "checkdevicePermission: startd");
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(AddActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
            }

        } else {
            Log.d(TAG, "checkdevicePermission: we already  have the permission");
            callGalleryforImage();
        }
    }

    public void callGalleryforImage() {
        Log.d(TAG, "callGalleryforImage: Calling gallery for image");
        Intent startGallery = new Intent(Intent.ACTION_PICK);
        startGallery.setType("image/*");
        startActivityForResult(startGallery, REQUEST_CODE_Gallery);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: started" + requestCode);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: Permission Granted:");
                callGalleryforImage();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: started" + data.toString());
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_Gallery && resultCode == RESULT_OK) {
            Log.d(TAG, "onActivityResult:after result code " + requestCode);

            imageuri = data.getData();
            Log.d(TAG, "onActivityResult: Got the Image Uri" + imageuri);
            Toast.makeText(this, "We Got the image from Gallery", Toast.LENGTH_SHORT).show();

            // changing to byte array with input stream
            try {
                InputStream inputStream=getContentResolver().openInputStream(imageuri);
                convertedBitmap= BitmapFactory.decodeStream(inputStream);
                image_add.setImageBitmap(convertedBitmap);


            }
            catch (FileNotFoundException e){
                Log.d(TAG, "onActivityResult: "+e.getMessage());

            }




        }


    }
    private byte[] imageViewtoByte(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    public void saveCarToDatabase(byte[] img,String name,String color,int model,int cc,int price){

        boolean insertData= dataBaseHelper.addCar(img,name,color,model,cc,price);
        if(insertData){
            Toast.makeText(this, "Data Got Inserted Into the Database Sucessfully", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Data Insertion Failed" , Toast.LENGTH_SHORT).show();
        }


    }
}
