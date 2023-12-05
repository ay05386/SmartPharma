package com.example.pharmacyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class addmedActivity extends AppCompatActivity {
    med med0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmed);
        EditText editText1 = findViewById(R.id.editTextTextPersonName2);
        EditText editText2 = findViewById(R.id.editTextTextPersonName3);
        EditText editText3 = findViewById(R.id.editTextNumber);
        EditText editText4 = findViewById(R.id.editTextTextPersonName4);
        Button btn = findViewById(R.id.button4);
        DAOmed daOmed =new DAOmed();
        med0= (med) getIntent().getSerializableExtra("EDIT");
       if(med0!=null){
           btn.setText("UPDATE");
           editText1.setText(med0.getName());
           editText2.setText(med0.getDescription());
           editText3.setText(Integer.toString(med0.getPrice()));
           editText4.setText(Integer.toString(med0.getQuantity()));
       }else{
           btn.setText("ADD");
       }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (med0 == null){
            med medicine = new med(editText1.getText().toString(),editText2.getText().toString(),Integer.parseInt(editText3.getText().toString()),Integer.parseInt(editText4.getText().toString()),0);
            daOmed.add(medicine).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
               System.out.println("add med success");
                    startActivity(new Intent(addmedActivity.this,navigation.class));


                }
            });
            }else{
                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("name",editText1.getText().toString());
                    hashMap.put("description",editText2.getText().toString());
                   // hashMap.put("price",editText3.getText().toString());
                    hashMap.put("price",Integer.parseInt(editText3.getText().toString()));
                //    hashMap.put("quantity",editText4.getText().toString());//if it deint work replace it with integer.tostring
                    hashMap.put("quantity",Integer.parseInt(editText4.getText().toString()));
                    daOmed.update(med0.getKey(),hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            System.out.println("updated");
                            startActivity(new Intent(addmedActivity.this,navigation.class));
                        }
                    });




                }


            }
        });


    }

}