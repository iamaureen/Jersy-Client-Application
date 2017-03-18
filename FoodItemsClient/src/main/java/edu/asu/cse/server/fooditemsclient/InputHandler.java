/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse.server.fooditemsclient;


import java.util.ArrayList;
import java.util.List;
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
        System.out.println("Select: 1.Add Items (Hard Coded)\n 2. Get Items(Hard Coded)\n 3. Add Food Item (dynamically)\n 4. Get Food Item (Use Ids)(Dynamically)\n Option:" );
        int option = Integer.parseInt(input.next());
        System.out.println("You Entered :: " + option);
        
        FoodItem foodItem = new FoodItem();
        
        switch(option){
            //add food item
            case 1:{
                
                break;
            }
            case 2:{
                String getItem = "<SelectedFoodItems xmlns=\"http://cse564.asu.edu/PoxAssignment\">"
                                    +"<FoodItemId>101</FoodItemId>"
                                    +"<FoodItemId>156</FoodItemId>"
                               + "</SelectedFoodItems>";
                
                FoodItemClient client = new FoodItemClient();
                client.getFoodItemToXML(getItem);
                
                
                break;
            }
            case 3:{
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
            case 4:{
                
                  //list to hold multiple ids of the food object
                  List<Integer> foodItems = new ArrayList<>();
                  
                  //take input of the ids
                  System.out.println("Enter the ids of the food object - separate with spaces");
                  input.nextLine();
                  String foodIDs = input.nextLine();
	    	  String[] selectedIDs = foodIDs.split(" ");
                  
                  //add the ids into the list
                  for(int i=0;i<selectedIDs.length;i++){
                      foodItems.add(Integer.parseInt(selectedIDs[i]));
                  }
                  
                  //arrange the ids list into specific xml format given in the doc
                  SelectedFoodItems selectedItems = new SelectedFoodItems();
                  selectedItems.setFoodItem(foodItems);
                  
                  //call the client and pass the xml object of ids
                  FoodItemClient client = new FoodItemClient();
                  client.getFoodItemFromXML(selectedItems);
                  
                break;
            }
                
            
        }
    }
    
}
