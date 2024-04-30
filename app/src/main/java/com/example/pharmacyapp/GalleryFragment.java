package com.example.pharmacyapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacyapp.R;
//import com.example.pharmacyapp.databinding.FragmentGalleryBinding;
import com.example.pharmacyapp.myAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GalleryFragment extends Fragment implements newAdapter.recycleclick {
    public GalleryFragment() {
    }

    String s1[];
    String s2[];
    DAOmed daOmed;
    myAdapter myAdapter;
    DatabaseReference database;
    ArrayList<med> medArrayList;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    int key;
    int size;
    ArrayList<med> thelist= new ArrayList<med>();
    ArrayList<Integer> index = new ArrayList<Integer>();
med themed;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_gallery, container, false);

        s1 = getResources().getStringArray(R.array.medicines);
        s2 = getResources().getStringArray(R.array.descriptions);
        RecyclerView recyclerView = rootview.findViewById(R.id.recycler);


        sharedPreferences = getActivity().getSharedPreferences("med", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());//remove this line if it dosent work
        //  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(manager);
        medArrayList = new ArrayList<>();
        // myAdapter = new myAdapter(getActivity(),m); // i will change the m in the adapter to make work with firebase
        myAdapter = new myAdapter(getActivity(), medArrayList,this);
        recyclerView.setAdapter(myAdapter);

        size = sharedPreferences.getInt("size",0);
        for(int i=0; i<=size;i++){
             index.add(sharedPreferences.getInt("key"+i,0));
        }
        //index.add(key);

        System.out.println(index.size());
        for(int i = 0; i < index.size(); i++) {
           System.out.println(index.get(i));
        }
        daOmed = new DAOmed();
        //  loadData();
        database = FirebaseDatabase.getInstance().getReference("med");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    med medic = dataSnapshot.getValue(med.class);
                   medic.setKey(dataSnapshot.getKey());
                    thelist.add(medic);
                }


                for(int i = 0; i < index.size(); i++) {
                    themed = thelist.get(index.get(i));
                    medArrayList.add(themed);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return rootview;

        // return super.onCreateView(inflater, container, savedInstanceState); //if it didn't work replaace the return rootview with this line and see
    }

    private void loadData() {

        daOmed.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // ArrayList<med> medics = new ArrayList<med>();
                // medics = new ArrayList<med>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    med medic = data.getValue(med.class);
                    medArrayList.add(medic);
                }/*
                Bundle bundle = getArguments();
                 med medobject = (med) bundle.getSerializable("themed");
                 medArrayList.add(medobject);*/

                //myAdapter.setItem(medArrayList);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onclic(int position) {
        //list.get(position);
        //Log.d(TAG, "onclic: done");
        System.out.println(position);
        Toast.makeText(getActivity(), "aight", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onitemlong(int position) {

    }
}


