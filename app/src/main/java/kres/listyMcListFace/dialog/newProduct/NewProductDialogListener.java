package kres.listyMcListFace.dialog.newProduct;

import kres.listyMcListFace.model.Product;

public interface NewProductDialogListener {

    void onProductAdded(Product product);
    void onCancel();

}
