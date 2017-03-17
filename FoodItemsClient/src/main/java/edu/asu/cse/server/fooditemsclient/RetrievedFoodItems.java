/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse.server.fooditemsclient;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ishrat Ahmed
 */
@XmlRootElement(name = "RetrievedFoodItems")
public class RetrievedFoodItems {
    List<FoodItem> foodItem;
     List<Integer> invalidFoodItem;

    @XmlElement(name = "FoodItem")
    public void setFoodItem(List<FoodItem> foodItem) {
        this.foodItem = foodItem;
    }
    
    public List<FoodItem> getFoodItem() {
        return foodItem;
    }
    
    @XmlElementWrapper(name = "InvalidFoodItems")
    @XmlElement(name = "FoodItemId")
    public void setInvalidFoodItem(List<Integer> invalidFoodItem) {
        this.invalidFoodItem = invalidFoodItem;
    }

    public List<Integer> getInvalidFoodItem() {
        return invalidFoodItem;
    }
 
    
}
