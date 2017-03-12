/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse.server.fooditemsclient;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Ishrat Ahmed
 */
@XmlRootElement (name="FoodItem")
@XmlType(propOrder = {"ID", "name", "description", "category","price"})
public class FoodItem {
    
    private int ID;
    private String name;
    private String description;
    private String category;
    private String price;
    private String country;
    
    @XmlAttribute(name="country")
    public String getCountry() {
        return country;
    }
    
     public void setCountry(String country) {
        this.country = country;
    }
    
    @XmlElement(name="ID")
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public int getID() {
        return ID;
    }
    
    @XmlElement(name="name")
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @XmlElement(name="description")
    public void setDescription(String description) {
        this.description = description;
    }
    
     public String getDescription() {
        return description;
    }
     
    @XmlElement(name="cateogry")
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    
    @XmlElement(name="price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
       

}
