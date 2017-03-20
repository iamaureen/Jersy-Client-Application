/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse.server.fooditemsclient;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ishrat Ahmed
 */
@XmlRootElement (name="NewFoodItems", namespace="http://cse564.asu.edu/PoxAssignment")
public class NewFoodItems {
    
    List<FoodItem> foodItem;

    public List<FoodItem> getFoodItem() {
        return foodItem;
    }

    @XmlElement(name = "FoodItem")
    public void setFoodItem(List<FoodItem> foodItem) {
        this.foodItem = foodItem;
    }

    
    
    
}
