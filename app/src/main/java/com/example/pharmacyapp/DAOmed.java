package com.example.pharmacyapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOmed {
  private DatabaseReference databaseReference;
    public DAOmed(){
   FirebaseDatabase db =  FirebaseDatabase.getInstance();
   databaseReference= db.getReference(med.class.getSimpleName());
    }
    public Task<Void> add(med medicine){

        return databaseReference.push().setValue(medicine);
    }


    public Query get(){
        return databaseReference.orderByKey();
    }

public Task<Void> update (String key, HashMap<String,Object> hashMap) {
return databaseReference.child(key).updateChildren(hashMap);
}
public Task<Void>remove (String key){
       return databaseReference.child(key).removeValue();
}
}

