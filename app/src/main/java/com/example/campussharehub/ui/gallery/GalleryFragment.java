package com.example.campussharehub.ui.gallery;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.campussharehub.Product;
import com.example.campussharehub.R;
import com.example.campussharehub.databinding.FragmentGalleryBinding;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    Button addProductButton;
    Product product;

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
        Button productSubmit = dialog.findViewById(R.id.product_submit);

        productSubmit.setOnClickListener(view -> {
            product =
                    new Product(
                            productName.getText().toString(),
                            new BigDecimal(productPrice.getText().toString()),
                            null,
                            productDescription.getText().toString(),
                            productCollectionInformation.getText().toString());

            //File productFile = new File(getContext().getFilesDir(), product.Name);

//                try (FileOutputStream fos = getContext().openFileOutput(product.Name, Context.MODE_PRIVATE)){
//                    fos.write(product.);

            // This doesn't work because of lack of writing permissions
            // Find an alternative way to persist entire objects for a phone
            try {
                FileOutputStream out = new FileOutputStream(product.Name);
                ObjectOutputStream os = new ObjectOutputStream(out);
                os.writeObject(product);
                os.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}