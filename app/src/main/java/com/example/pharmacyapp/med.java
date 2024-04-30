
package com.example.pharmacyapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.List;

public class med implements Serializable, Comparable<med> {
    @Exclude
    private  String key ;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private int sold;
    public med (String n,String d,int p,int q,int s){
        name=n;
        description= d ;
        price=p;
        quantity = q;
        sold = s;

    }
    public med (){


    }//if this shit didnt work check the employee class in cambo tutorial

    protected med(Parcel in) {
        name = in.readString();
        description = in.readString();
        price = in.readInt();
        quantity = in.readInt();
        sold = in.readInt();
    }


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int compareTo(med med) {

        return this.sold - med.getSold();
    }
}
