package kres.realtimeshoppinglist.dialog;

import kres.realtimeshoppinglist.model.Product;

public interface NewProductDialogListener {

    void onProductAdded(Product product);
    void onCancel();

}
