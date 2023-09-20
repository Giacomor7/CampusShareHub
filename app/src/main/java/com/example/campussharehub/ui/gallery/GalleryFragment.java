package com.example.campussharehub.ui.gallery;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campussharehub.CustomAdapter;
import com.example.campussharehub.MyDatabaseHelper;
import com.example.campussharehub.R;
import com.example.campussharehub.databinding.FragmentGalleryBinding;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    Button addProductButton;

    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList<String> product_id, product_name, product_description, product_price, product_collection_information, product_image;
    CustomAdapter customAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        addProductButton = root.findViewById(R.id.add_product);
        addProductButton.setOnClickListener(view -> showAddProductDialog());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView = root.findViewById(R.id.yourProducts);

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

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        GradientDrawable drawable =
                new GradientDrawable(
                        GradientDrawable.Orientation.BOTTOM_TOP, new int[]{0xfff7f7f7, 0xfff7f7f7});
        drawable.setSize(8,24);
        dividerItemDecoration.setDrawable(drawable);

        recyclerView.addItemDecoration(dividerItemDecoration);

        return root;
    }

    private void showAddProductDialog() {
        final Dialog dialog = new Dialog(this.requireView().getContext());
        // We have added a title in the custom layout, so we disable the default title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // The user should be able to cancel the dialog by clicking anywhere outside it
        dialog.setCancelable(true);
        // See add_product.xml for layout
        dialog.setContentView(R.layout.add_product);

        // All dialog views must be initialised independently
        final EditText productName = dialog.findViewById(R.id.product_name);
        final EditText productDescription = dialog.findViewById(R.id.product_description);
        final EditText productPrice = dialog.findViewById(R.id.product_price);
        final EditText productCollectionInformation = dialog.findViewById(R.id.product_collection_information);
        ImageButton productSubmit = dialog.findViewById(R.id.product_submit);

        productSubmit.setOnClickListener(view -> {
            try (MyDatabaseHelper myDB = new MyDatabaseHelper(getContext())){
                myDB.addProduct(
                        productName.getText().toString().trim(),
                        productDescription.getText().toString().trim(),
                        productPrice.getText().toString().trim(),
                        productCollectionInformation.getText().toString().trim(),
                        null);
            }

//            product =
//                    new Product(
//                            productName.getText().toString(),
//                            new BigDecimal(productPrice.getText().toString()),
//                            null,
//                            productDescription.getText().toString(),
//                            productCollectionInformation.getText().toString());

            //File productFile = new File(getContext().getFilesDir(), product.Name);

//                try (FileOutputStream fos = getContext().openFileOutput(product.Name, Context.MODE_PRIVATE)){
//                    fos.write(product.);

            // This doesn't work because of lack of writing permissions
            // Find an alternative way to persist entire objects for a phone
//            try {
//                FileOutputStream out = new FileOutputStream(product.Name);
//                ObjectOutputStream os = new ObjectOutputStream(out);
//                os.writeObject(product);
//                os.flush();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

            dialog.dismiss();
        });

        dialog.show();
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
                product_price.add("£" + cursor.getString(3));
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