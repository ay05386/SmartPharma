package com.example.pharmacyapp.ui.gallery;


public class med {
    private String name;
    private String description;
    private int price;
    public med (String n,String d,int p){
        name=n;
        description= d ;
        price=p;

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
}
