/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.order;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author duybi
 */
public class OrderDTO implements Serializable{
    private int id;
    private Date dateBuy;
    private float total;

    public OrderDTO() {
    }

    public OrderDTO(int id, Date dateBuy, float total) {
        this.id = id;
        this.dateBuy = dateBuy;
        this.total = total;
    }

    public OrderDTO(Date dateBuy, float total) {
        this.dateBuy = dateBuy;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
