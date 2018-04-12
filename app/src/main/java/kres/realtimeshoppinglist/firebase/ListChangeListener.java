package kres.realtimeshoppinglist.firebase;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

import kres.realtimeshoppinglist.model.Product;
import kres.realtimeshoppinglist.util.ProductListAdapter;

public class ListChangeListener implements ChildEventListener {

    private List<String> keys;
    private ProductListAdapter productListAdapter;

    public ListChangeListener(ProductListAdapter productListAdapter) {
        this.keys = new ArrayList<>();
        this.productListAdapter = productListAdapter;
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
        Log.d("LIST_CHANGE", "Child Added");

        Product product = dataSnapshot.getValue(Product.class);
        product.setID(dataSnapshot.getKey());

        int insertionIndex = (previousChildName == null) ? 0 : keys.indexOf(previousChildName) + 1;
        keys.add(insertionIndex, dataSnapshot.getKey());

        productListAdapter.insertItem(insertionIndex, product);
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
        Log.d("LIST_CHANGE", "Child Added");

        Product updatedProduct = dataSnapshot.getValue(Product.class);

        int insertionIndex = (previousChildName == null) ? 0 : keys.indexOf(previousChildName) + 1;

        productListAdapter.updateItem(insertionIndex, updatedProduct);
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        Log.d("LIST_CHANGE", "Child Added");
    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
        Log.d("LIST_CHANGE", "Child Added");
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.d("LIST_ERROR", "" + databaseError.getMessage());
    }
}
