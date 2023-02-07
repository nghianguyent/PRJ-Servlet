/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.orderdetail;

import java.io.Serializable;

/**
 *
 * @author duybi
 */
public class OrderDetailDTO implements Serializable{
    private int id;
    private String sku;
    private int orderId;
    private int quantity;
    private float price;
    private float total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String sku, int orderId, int quantity, float price, float total) {
        this.sku = sku;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public OrderDetailDTO(String sku, int quantity, float price, float total) {
        this.sku = sku;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
    
}
