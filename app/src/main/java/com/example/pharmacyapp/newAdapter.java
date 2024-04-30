package com.example.pharmacyapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacyapp.ui.slideshow.SlideshowFragment;

import java.io.Serializable;
import java.util.ArrayList;

public class newAdapter extends RecyclerView.Adapter<newAdapter.MyViewHolder> {
Context context;
ArrayList<med> list =new ArrayList<>();

    //GalleryFragment fragment = new GalleryFragment();
    SlideshowFragment fragment;
//this adapter is for the foxandroid video i will make another one for cambo android
 recycleclick recycleclick;
    public newAdapter(Context context, ArrayList<med> list,recycleclick recycleclick) {
        this.context = context;
this.recycleclick = recycleclick;


    }
   public void  setitem (ArrayList<med> medici){
        list.addAll(medici);
   }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(v,recycleclick);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
 med medic = list.get(position);
 holder.textView1.setText(medic.getName());
 holder.textView2.setText(medic.getDescription());//tey to use parse int
 holder.textView3.setText(Integer.toString(medic.getPrice()));//if it didnt work try to cast out this line
        holder.textView5.setText(Integer.toString(medic.getQuantity()));
        holder.textView6.setText(Integer.toString(medic.getSold()));
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       // Log.d(TAG, "onclic: done");
     //   System.out.println(holder.getAdapterPosition());
        recycleclick.onclic(holder.getAdapterPosition());
        fragment = new SlideshowFragment();
        // i can just get the position of the clicked object from the "list" Array and pass the object
      Bundle bundle= new Bundle();
      //  bundle.putParcelable("themed",list.get(4));
        fragment.setArguments(bundle);

    }
});
holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View view) {
        Intent intent= new Intent(context,addmedActivity.class);
        intent.putExtra("EDIT",medic);//im not sure bout this so if it didnt work try one of these two 1- try holder.getpositoin 2- try passing the positoin to the slideshow fragment and pass the object from there
        context.startActivity(intent);
        recycleclick.onitemlong(holder.getAdapterPosition());

        return true;
    }
});
    }
    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView1,textView2,textView3,textView4,textView5,textView6;
         recycleclick recycleclick;
        public MyViewHolder(@NonNull View itemView,recycleclick recycleclick) {
            super(itemView);
            textView1= itemView.findViewById(R.id.textView4);
            textView2= itemView.findViewById(R.id.textView6);
            textView3= itemView.findViewById(R.id.textView7);
          //  textView4 = itemView.findViewById(R.id.textView8);//for the menu option
            textView5 = itemView.findViewById(R.id.textView9);//for quantity
            textView6 = itemView.findViewById(R.id.textView11);//for sales
            this.recycleclick = recycleclick;



        }


        @Override
        public void onClick(View view) {
         recycleclick.onclic(getAdapterPosition());

        }
    }
    public interface recycleclick{
        void onclic(int position);
        void onitemlong (int position);
    }


}
