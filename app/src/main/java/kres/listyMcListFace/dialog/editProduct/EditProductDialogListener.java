package kres.listyMcListFace.dialog.editProduct;

import kres.listyMcListFace.model.Product;

public interface EditProductDialogListener {

    void onProductNameUpdated(Product product);
    void onCancel();

}
