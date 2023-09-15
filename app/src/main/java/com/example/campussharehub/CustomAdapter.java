package com.example.campussharehub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<String> product_id, product_name, product_description, product_price, product_collection_information;

    public CustomAdapter(
            Context context,
            ArrayList<String> product_id,
            ArrayList<String> product_name,
            ArrayList<String> product_description,
            ArrayList<String> product_price,
            ArrayList<String> product_collection_information){
        this.context = context;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
        this.product_collection_information = product_collection_information;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.product_id_txt.setText(String.valueOf(product_id.get(position)));
        holder.product_name_txt.setText(String.valueOf(product_name.get(position)));
        holder.product_description_txt.setText(String.valueOf(product_description.get(position)));
        holder.product_price_txt.setText(String.valueOf(product_price.get(position)));
        holder.product_collection_information_txt.setText(String.valueOf(product_collection_information.get(position)));
    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView product_id_txt, product_name_txt, product_description_txt, product_price_txt, product_collection_information_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id_txt = itemView.findViewById(R.id.product_id_txt);
            product_name_txt = itemView.findViewById(R.id.product_name_txt);
            product_description_txt = itemView.findViewById(R.id.product_description_txt);
            product_price_txt = itemView.findViewById(R.id.product_price_txt);
            product_collection_information_txt = itemView.findViewById(R.id.product_collection_information_txt);
        }
    }
}
