package com.example.pharmacyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {
    String data1[],data2[];
    Context context;
    med[] m1;
   // ArrayList<med> medArrayList= new ArrayList<>();
ArrayList<med> medArrayList;
    newAdapter.recycleclick recycleclick;


    public myAdapter(Context context, ArrayList<med> medArrayList,newAdapter.recycleclick recycleclick) {
        this.context = context;
        this.medArrayList = medArrayList;
        this.recycleclick = recycleclick;
    }

    public  void setItem(ArrayList<med>meds){
medArrayList.addAll(meds);
    }
/*

 public myAdapter(Context ct, ArrayList<med> medArrayList){
//data1 = s1;
//data2 = s2;
context = ct;
//medArrayList = medArrayList;
 }*/
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.my_row_sales,parent,false);
     return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        myViewHolder holder1 =(myViewHolder) holder;
        med med1 = medArrayList.get(position);
        holder1.textView1.setText(med1.getName());//if this shit didnt work replace it with medarraylist.get name
        //holder1.textView2.setText(med1.getDescription());
        holder1.textView3.setText(Integer.toString(med1.getPrice()));
        //holder1.textView4.setText(Integer.toString(med1.getQuantity()));
        holder.textView6.setText(Integer.toString(med1.getSold()));
        //if it didnt work replace it with Integer.toString(m1[position].getPrice()))



   /*
     holder.textView1.setText(m1[position].getName());
        holder.textView2.setText(m1[position].getDescription());
        holder.textView3.setText(Integer.toString(m1[position].getPrice()));*/

    }


        @Override
        public int getItemCount() {
          return medArrayList.size();


        }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3,textView4,textView5,textView6;
        public myViewHolder(@NonNull View itemView) {

            super(itemView);
            textView1= itemView.findViewById(R.id.textView4);
            //textView2= itemView.findViewById(R.id.textView6);
            textView3= itemView.findViewById(R.id.textView7);
            //textView4 = itemView.findViewById(R.id.textView9);
            //textView5 = itemView.findViewById(R.id.textView9);//for quantity
            textView6 = itemView.findViewById(R.id.textView11);//for sales

        }
    }
}
