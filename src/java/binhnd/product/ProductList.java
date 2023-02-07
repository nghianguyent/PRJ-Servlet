/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.product;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author duybi
 */
public class ProductList extends ArrayList<ProductDTO> {

    public ProductList() {
        super();
    }

    public boolean updateRemainProduct(String sku, int quantity) {

        ProductDTO product = this.getProduct(sku);
        if (product == null) {
            return false;
        }
        if (product.getQuantity() - quantity < 0) {
            return false;
        }
        product.setQuantity(product.getQuantity() - quantity);
        return true;
    }

    public ProductDTO getProduct(String sku) {
        ProductDTO product = this.get(this.indexOf(new ProductDTO(sku)));
        return product;
    }

    public float calculateProductsPrice(Map<String, Integer> items) {
        float total = 0;

        for (Map.Entry<String, Integer> item : items.entrySet()) {
            ProductDTO product = getProduct(item.getKey());
            total = product.getPrice() * item.getValue();
        }
        return total;
    }
}
