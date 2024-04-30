package com.example.pharmacyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class mostSoldAdapter extends RecyclerView.Adapter<mostSoldAdapter.mostSoldHolder> {
    ArrayList<med> list =new ArrayList<>();
    Context context;


    public mostSoldAdapter(ArrayList<med> list, Context context) {
        this.list = list;

        this.context = context;
    }


    public void  setitem (ArrayList<med> medici){
        list.addAll(medici);
    }
    @NonNull
    @Override
    public mostSoldHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.my_row_most,parent,false);
        return new mostSoldHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull mostSoldHolder holder, int position) {

        med medic = list.get(position);
//        Collections.sort(this.list.);;
        holder.textView1.setText(medic.getName());
        holder.textView2.setText(medic.getDescription());//tey to use parse int
        holder.textView3.setText(Integer.toString(medic.getPrice()));//if it didnt work try to cast out this line
        holder.textView5.setText(Integer.toString(medic.getQuantity()));
        holder.textView6.setText(Integer.toString(medic.getSold()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class mostSoldHolder extends RecyclerView.ViewHolder{
        TextView textView1,textView2,textView3,textView4,textView5,textView6;
        public mostSoldHolder(@NonNull View itemView) {
            super(itemView);
            textView1= itemView.findViewById(R.id.textView4);
            textView2= itemView.findViewById(R.id.textView6);
            textView3= itemView.findViewById(R.id.textView7);
            //  textView4 = itemView.findViewById(R.id.textView8);//for the menu option
            textView5 = itemView.findViewById(R.id.textView9);//for quantity
            textView6 = itemView.findViewById(R.id.textView11);

        }
    }
}
