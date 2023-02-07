/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tklin
 */
public class CartObject implements Serializable {

    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItemToCart(String sku, int quantity) {
        if (sku == null) {
            return;
        }
        if (sku.trim().isEmpty()) {
            return;
        }
        if (quantity == 0) {
            return;
        }
        if (this.items == null) {
            this.items = new HashMap<>();
        }//items have not existed
        if (this.items.containsKey(sku)) {
            quantity += this.items.get(sku);
        }//end item has existed
        //update items
        this.items.put(sku, quantity);
    }

    public void removeItemFromCart(String sku) {
        if (sku == null) {
            return;
        }
        if (sku.trim().isEmpty()) {
            return;
        }
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(sku)) {
            this.items.remove(sku);
            if (this.items.isEmpty()) {
                this.items = null; //không tồn tại thì gán bằng null
            }
        }

    }
}
