package com.example.mycar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ModelHolder> {
    Context context;
    Activity activity;
    ArrayList<Model> modelArrayList;

    public CarAdapter(Context context, ArrayList<Model> modelArrayList,Activity activity) {
        this.activity=activity;
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item,parent,false);

        return new ModelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ModelHolder holder, final int position) {
        final Model model =modelArrayList.get(position);
        Bitmap bitmap =holder.ByteArrayToBitmap(model.getImage());
        //holder.carImage.setImageBitmap(bitmap);
        holder.Cname.setText(model.getName());
        holder.Ccolor.setText(model.getColor());
        holder.Cmodel.setText(model.getC_model());
        holder.Ccc.setText(model.getCc());
        holder.Cprice.setText(model.getPrice());

        holder.item_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,UpdateAcivity.class);
               // intent.putExtra("id",model.id);
                intent.putExtra("image",model.image);
                intent.putExtra("name",model.name);
                intent.putExtra("color",model.color);
                intent.putExtra("model",model.c_model);
                intent.putExtra("cc",model.cc);
                intent.putExtra("price",model.price);
                activity.startActivityForResult(intent,1);


            }
        });

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ModelHolder extends RecyclerView.ViewHolder{
        ImageView carImage;
        TextView Cname,Ccolor,Cmodel,Ccc,Cprice;
        RelativeLayout item_relative;

        public ModelHolder(@NonNull View itemView) {
            super(itemView);
            carImage=itemView.findViewById(R.id.Car_image);
            Cname=itemView.findViewById(R.id.Car_name);
            Ccolor=itemView.findViewById(R.id.Car_color);
            Cmodel=itemView.findViewById(R.id.Car_model);
            Ccc=itemView.findViewById(R.id.Car_cc);
            Cprice=itemView.findViewById(R.id.Car_price);
            item_relative=itemView.findViewById(R.id.item_relative_layout);
        }
        public Bitmap ByteArrayToBitmap(byte[] byteArray)
        {
            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
            Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
            return bitmap;
        }
    }
}
