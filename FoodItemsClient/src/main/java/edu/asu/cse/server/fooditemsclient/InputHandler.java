/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse.server.fooditemsclient;

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
        System.out.println("Please Note: All the inputs are hardcoded.\n"
                + "Select :\n 1.Add a new Item (If it is a new item, item will be added, id will be displayed."
                + " If the item exists, the existing id will be shown) \n "
                + "2.Get Items (one exists and one does not exist)\n "
                + "3.Get Items (both item exists)\n "
                + "4.Invalid Item (wrong xml) \n "
                + "5.Invalid Item (wrong xml for adding an Item) \n "
                + "6.Invalid Item (wrong xml for getting an Item)\n "
                + "Option:");
        int option = Integer.parseInt(input.next());
        System.out.println("You Entered :: " + option);

        FoodItem foodItem = new FoodItem();

        switch (option) {
            //add food item
            case 1: {
                String addItem = "<NewFoodItems xmlns=\"http://cse564.asu.edu/PoxAssignment\">"
                        + "<FoodItem country=\"GB\">"
                        + "<name>Pasty</name>"
                        + "<description>Tender cubes of steak, potatoes and swede wrapped in flakey short crust pastry.  Seasoned with lots of pepper.  Served with mashed potatoes, peas and a side of gravy</description>"
                        + "<category>Dinner</category> "
                        + "<price>15.95</price> "
                        + "</FoodItem>"
                        + "</NewFoodItems >";

                FoodItemClient client = new FoodItemClient();
                client.addFoodItemToXML(addItem);

                break;
            }
            case 2: {
                String getItem = "<SelectedFoodItems xmlns=\"http://cse564.asu.edu/PoxAssignment\">"
                        + "<FoodItemId>101</FoodItemId>"
                        + "<FoodItemId>156</FoodItemId>"
                        + "</SelectedFoodItems>";

                FoodItemClient client = new FoodItemClient();
                client.getFoodItemToXML(getItem);

                break;
            }
            case 3: {
                String getItem = "<SelectedFoodItems xmlns=\"http://cse564.asu.edu/PoxAssignment\">"
                        + "<FoodItemId>101</FoodItemId>"
                        + "<FoodItemId>102</FoodItemId>"
                        + "</SelectedFoodItems>";

                FoodItemClient client = new FoodItemClient();
                client.getFoodItemToXML(getItem);

                break;

            }
            case 4: {
                String invalidItem = "< xmlns=\"http://cse564.asu.edu/PoxAssignment\">"
                        + "<FoodItemId>101</FoodItemId>"
                        + "<FoodItemId>156</FoodItemId>"
                        + "</>";

                FoodItemClient client = new FoodItemClient();
                client.inValidDemo(invalidItem);
                break;

            }
            case 5: {

                String invalidAddItem = "<NewItems xmlns=\"http://cse564.asu.edu/PoxAssignment\">"
                        + "<FoodItem country=\"GB\">"
                        + "<name>Pasty</name>"
                        + "<description>Tender cubes of steak, potatoes and swede wrapped in flakey short crust pastry.  Seasoned with lots of pepper.  Served with mashed potatoes, peas and a side of gravy</description>"
                        + "<price>15.95</price> "
                        + "</FoodItem>"
                        + "</NewItems >";

                FoodItemClient client = new FoodItemClient();
                client.inValidDemo(invalidAddItem);
                break;

            }
            case 6: {

                String invalidGetItem = "<SelectedFood xmlns=\"http://cse564.asu.edu/PoxAssignment\">"
                        + "<Food>101</FoodItemId>"
                        + "<FoodItemId>156</FoodItemId>"
                        + "</SelectedFood>";

                FoodItemClient client = new FoodItemClient();
                client.inValidDemo(invalidGetItem);
                break;

            }

        }
    }

}
