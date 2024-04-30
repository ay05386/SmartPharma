package com.example.pharmacyapp.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacyapp.DAOmed;
import com.example.pharmacyapp.R;
import com.example.pharmacyapp.databinding.FragmentHomeBinding;
import com.example.pharmacyapp.lowQuantityAdapter;
import com.example.pharmacyapp.med;
import com.example.pharmacyapp.mostSoldAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    RecyclerView recyclerView;
    ArrayList<med> list =new ArrayList<>();
    ArrayList<med> list2= new ArrayList<>();
    RecyclerView recyclerView2;
    DAOmed daOmed;
    mostSoldAdapter mostSoldAdapter;
    lowQuantityAdapter lowQuantityAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView= root.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         mostSoldAdapter = new mostSoldAdapter(list, getActivity());
        recyclerView.setAdapter(mostSoldAdapter);
        daOmed = new DAOmed();
        loadData();

        recyclerView2= root.findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
         lowQuantityAdapter = new lowQuantityAdapter(list2,getActivity());
        recyclerView2.setAdapter(lowQuantityAdapter);


        mostSoldAdapter.notifyDataSetChanged();
        lowQuantityAdapter.notifyDataSetChanged();
        return root;
    }
    public void loadData() {
        daOmed.get().addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //  ArrayList<med>medec = new ArrayList<>();

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){

                    med medic = dataSnapshot.getValue(med.class);
                    medic.setKey(dataSnapshot.getKey());
                    list.add(medic);
                    list2.add(medic);

                }

//                public void onBindViewHolder(@NonNull lowQuantityHolder holder, int position){
//                    med medic = list.get(position);

//                Collections.sort(list.get(med.sold));
//                Collections.reverse(list);

//                }
//                list.sort(Comparator.comparing(ClassName::getFieldName).reversed());
               // list.sort(Comparator.comparing(med::getSold).reversed());
                list.sort(Comparator.comparingInt(med::getSold).reversed());
                list2.sort(Comparator.comparingInt(med::getQuantity));
                mostSoldAdapter.setitem(list);
                mostSoldAdapter.notifyDataSetChanged();
                lowQuantityAdapter.setitem(list2);
                lowQuantityAdapter.notifyDataSetChanged();


                //i will first call the function in this scope if it didnt work i will try the other scope in the bottom of this scope
            }//i can make a function out of this code and put it in the bottom and call it whenever i want
            //if it didnt work we can find another place where i can accsess the medec adapter freely
            //if it didnt work inside this scope we might try it in the oncreate scope under the load data functoin call

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}