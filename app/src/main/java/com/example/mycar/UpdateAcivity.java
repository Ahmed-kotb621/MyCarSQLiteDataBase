package com.example.mycar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdateAcivity extends AppCompatActivity {
    ImageView image_update;
    EditText etName,etColor,etModel,etCC,etPrice;
    Button btn_update;
    byte[] image;
    String id,name,color,model,cc,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_acivity);



    }
    void getInetentAndData(){
        if(getIntent().hasExtra("id")  &&
                getIntent().hasExtra("name") && getIntent().hasExtra("color")&&
        getIntent().hasExtra("model")&&getIntent().hasExtra("cc")&&getIntent().hasExtra("price")){
            //Getting Data from Intent
           // image =getIntent().getByteArrayExtra("image");
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            color = getIntent().getStringExtra("color");
            model = getIntent().getStringExtra("model");
            cc = getIntent().getStringExtra("cc");
            price = getIntent().getStringExtra("price");

            //Setting Intent Data
            //image_update.setImageResource(Integer.valueOf(String.valueOf(image)));
            etName.setText(name);
            etColor.setText(color);
            etModel.setText(model);
            etCC.setText(cc);
            etPrice.setText(price);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}
