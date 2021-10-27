package com.example.mycar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btn_add;
    RecyclerView recyclerView;
    DataBaseHelper dataBaseHelper;
    ArrayList<Model> modelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add=findViewById(R.id.btn_float);
        recyclerView=findViewById(R.id.recyclerView);
        dataBaseHelper=new DataBaseHelper(this);
        modelArrayList=dataBaseHelper.getAllData();
        CarAdapter carAdapter =new CarAdapter(this,modelArrayList,MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(carAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);

            }
        });

    }

}
