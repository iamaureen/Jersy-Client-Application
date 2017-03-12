/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse.server.fooditemsclient;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ishrat Ahmed
 */
@XmlRootElement(name="FoodItemAdded")
public class FoodItemAdded {
    private int id;

    @XmlElement(name="FoodItemId")
    public void setId(int id) {
            this.id = id;
    }
    public int getId() {
            return id;
    }

}
