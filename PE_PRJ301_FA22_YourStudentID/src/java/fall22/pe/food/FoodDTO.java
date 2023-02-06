/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates. Created on : 30-10-2022
 * and open the template in the editor.
 */
package fall22.pe.food;

import java.io.Serializable;

/**
 *
 * @author hd
 */
public class FoodDTO implements Serializable{
    private String id;
    private String name;
    private String description;
    private float price;
    private int cookingTime;
    private int status;

    public FoodDTO() {
    }

    public FoodDTO(String id, String name, String description, float price, int cookingTime, int status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.cookingTime = cookingTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
