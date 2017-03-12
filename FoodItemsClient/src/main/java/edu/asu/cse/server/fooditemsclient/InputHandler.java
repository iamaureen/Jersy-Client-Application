/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse.server.fooditemsclient;

import java.util.Random;
import java.util.Scanner;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Ishrat Ahmed
 */
public class InputHandler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JAXBException {
        // TODO code application logic here
        
        Scanner input = new Scanner(System.in);
        System.out.println("Select: \n 1. Add Food Item \n 2. Get Food Item (Use Ids)" );
        int option = Integer.parseInt(input.next());
        
        FoodItem foodItem = new FoodItem();
        
        switch(option){
            //add food item
            case 1:{
                //take the input
                System.out.println(System.getProperty("user.dir"));
                System.out.println("Enter Country Name:");
		String country = input.next();
		System.out.println("Enter the Name:");
                String name = input.next();
                System.out.println("Enter Description:");
		String description = input.next();		
		System.out.println("Enter Category:");
		String category = input.next();		
		System.out.println("Enter Price:");
		String price = input.next();
                
                //check if valid xml input
                if(country!=null || name!=null || category!=null || description!=null || price!=null){
                    
                    //set the pojo
                    Random id = new Random();
                    
                    foodItem.setID(id.nextInt(500));
                    foodItem.setCountry(country);
                    foodItem.setName(name);
                    foodItem.setDescription(description);
                    foodItem.setCategory(category);
                    foodItem.setPrice(price);
                    
                    
                }else{
                    System.out.println("<InvalidMessage xmlns=”http://cse564.asu.edu/PoxAssignment”/>\n");
                }
                
                //call client and send the food object to add in the xml
                FoodItemClient client = new FoodItemClient();
                client.addFoodItemToXML(foodItem);
                
                break; 
            }               
            case 2:{
                break;
            }
                
            
        }
    }
    
}
