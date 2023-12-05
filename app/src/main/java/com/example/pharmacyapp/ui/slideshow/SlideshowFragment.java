package com.example.pharmacyapp.ui.slideshow;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacyapp.DAOmed;
import com.example.pharmacyapp.GalleryFragment;
import com.example.pharmacyapp.R;
import com.example.pharmacyapp.databinding.FragmentSlideshowBinding;
import com.example.pharmacyapp.med;
import com.example.pharmacyapp.newAdapter;
import com.example.pharmacyapp.shared;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class SlideshowFragment extends Fragment implements newAdapter.recycleclick {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    RecyclerView recyclerView;
    DatabaseReference database;
    newAdapter newAdapter;
    DAOmed daOmed;
    ArrayList<med> list;
    ArrayList<med> medec;
    int thepostion;
    med medobject;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    ArrayList<Integer>positoins;
    //SlideshowFragment slideshowFragment = new SlideshowFragment();
    int x= 0;// maybe if i deleted the =0 the data will still be stored

    @Override
    public void onclic(int position) {

        //list.get(position);
        //Log.d(TAG, "onclic: done");
        // System.out.println(position);

        Toast.makeText(getActivity(), "sold", Toast.LENGTH_SHORT).show();
        thepostion=position;
         DAOmed dao = new DAOmed();
        GalleryFragment fragment = new GalleryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("themed",6);
        editor.putInt("key"+ x, thepostion);
        editor.putInt("size", x);
        editor.commit();
        x++;
        System.out.println(thepostion);
        fragment.setArguments(bundle);
        System.out.println(medec.get(thepostion).getKey());
        HashMap<String,Object> hashMap = new HashMap<>();
        int n =medec.get(thepostion).getQuantity();
        n--;
        int m =medec.get(thepostion).getSold();
        m++;
        hashMap.put("quantity",n);
        hashMap.put("sold",m);
        dao.update(medec.get(thepostion).getKey(),hashMap);


    }

    @Override
    public void onitemlong(int position) {
        /*
medec.remove(position);
daOmed.remove(medec.get(thepostion).getKey());
newAdapter.notifyItemRemoved(position);*/

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView= root.findViewById(R.id.recview);
        medec = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //we will try creating another bundle object here if it didnt work then i will refrence to that old video of editing recycld views
        //if it didnt work or got an error try to add to the list and just view the added object on the list
        sharedPreferences = getActivity().getSharedPreferences("med", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        if(!sharedPreferences.contains("key")){
            editor.putInt("key", 0);
            editor.commit();
        }
        // sharedPreferences = getContext().getSharedPreferences();

        //    database = FirebaseDatabase.getInstance().getReference("med");
        //  list = new ArrayList<>();
        newAdapter = new newAdapter(getActivity(),list,this);
        recyclerView.setAdapter(newAdapter);
        daOmed = new DAOmed();
        loadData();/*
        GalleryFragment fragment = new GalleryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("themed",thepostion);
        fragment.setArguments(bundle);*/
        //if it didnt work put the bundle code here inside the interface
        // passdata();

/*
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                med medic= dataSnapshot.getValue(med.class);
                list.add(medic);
            }
            newAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

*/

        return root;
    }

    public void loadData() {
        daOmed.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //  ArrayList<med>medec = new ArrayList<>();

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){

                    med medic = dataSnapshot.getValue(med.class);
                    medic.setKey(dataSnapshot.getKey());
                    medec.add(medic);

                }

                newAdapter.setitem(medec);
                newAdapter.notifyDataSetChanged();

                //i will first call the function in this scope if it didnt work i will try the other scope in the bottom of this scope
            }//i can make a function out of this code and put it in the bottom and call it whenever i want
            //if it didnt work we can find another place where i can accsess the medec adapter freely
            //if it didnt work inside this scope we might try it in the oncreate scope under the load data functoin call

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void passdata(){
        FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        GalleryFragment fragment = new GalleryFragment();//if it didnt work try intiate and slideshowfragment object instead
        Bundle bundle = new Bundle();
        bundle.putSerializable("themed",medec.get(thepostion));
        fragment.setArguments(bundle);
        ft.replace(android.R.id.content, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

/*
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }*/
}