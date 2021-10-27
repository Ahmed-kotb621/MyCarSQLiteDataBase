package com.example.mycar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.ColorSpace;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static java.sql.Types.BLOB;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    public Context context;
    public final static String DATABASE_NAME ="myCar.db";
    public final static int DATABASE_VERSIION=1;

    public final static String TABLE_NAME="car";
    public final static String ID="id";
    public final static String IMAGE ="image";
    public final static String NAME="name";
    public final static String COLOR="color";
    public final static String MODEL="model";
    public final static String CC="cc";
    public final static String PRICE="price";
    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSIION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IMAGE + " BLOB NOT NULL, " +NAME+ " TEXT, " +COLOR+ " TEXT, " + MODEL + " INTEGER, " + CC + " INTEGER, " + PRICE + " INTEGER );";
                db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public boolean addCar(byte[] image,String name,String color,int model,int cc,int price){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("image",image);
        contentValues.put("name",name);
        contentValues.put("color",color);
        contentValues.put("model",model);
        contentValues.put("cc",cc);
        contentValues.put("price",price);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1){
             return false;
        }else
            return true;

    }

    public ArrayList<Model> getAllData(){
        ArrayList<Model> arrayList =new ArrayList<>();
        SQLiteDatabase db =getWritableDatabase();
        String[] columns = {IMAGE,NAME,COLOR,MODEL,CC,PRICE};
        Log.d(TAG, "getAllData: "+columns);
        Cursor cursor=db.query(TABLE_NAME,columns,
                null,null,null,null,null);
        if (cursor!=null && cursor.moveToNext()){
            do {
                Model model =new Model();
                model.setImage(cursor.getBlob(cursor.getColumnIndex(IMAGE)));
                model.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                model.setColor(cursor.getString(cursor.getColumnIndex(COLOR)));
                model.setC_model(cursor.getString(cursor.getColumnIndex(MODEL)));
                model.setCc(cursor.getString(cursor.getColumnIndex(CC)));
                model.setPrice(cursor.getString(cursor.getColumnIndex(PRICE)));
                arrayList.add(model);
            }
            while (cursor.moveToNext());
        }
        return arrayList;
    }
    void updateData(String row_id,String name,String color,String model,String cc,String price){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        //cv.put("image",image);
        cv.put("name",name);
        cv.put("color",color);
        cv.put("model",model);
        cv.put("cc",cc);
        cv.put("price",price);
        long result =db.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }


    }
}
