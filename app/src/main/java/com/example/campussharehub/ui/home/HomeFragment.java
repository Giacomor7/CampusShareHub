package com.example.campussharehub.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campussharehub.CustomAdapter;
import com.example.campussharehub.MyDatabaseHelper;
import com.example.campussharehub.R;
import com.example.campussharehub.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList<String> product_id, product_name, product_description, product_price, product_collection_information, product_image;
    CustomAdapter customAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView);

        myDB = new MyDatabaseHelper(getContext());
        product_id = new ArrayList<>();
        product_name = new ArrayList<>();
        product_description = new ArrayList<>();
        product_price = new ArrayList<>();
        product_collection_information = new ArrayList<>();
        product_image = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(getContext(), this.product_id, this.product_name, this.product_description, this.product_price, this.product_collection_information);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(), "No products to show...", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                product_id.add(cursor.getString(0));
                product_name.add(cursor.getString(1));
                product_description.add(cursor.getString(2));
                product_price.add(cursor.getString(3));
                product_collection_information.add(cursor.getString(4));
                product_image.add(cursor.getString(5));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}