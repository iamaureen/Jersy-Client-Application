/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse.server.fooditemsclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Ishrat Ahmed
 */
public class FoodItemClient {
    
    Client client;
    String BASE_URI = "http://localhost:8080/FoodItems/webapi";
    WebResource webResource = null;
    
    //setup the client
    public FoodItemClient(){
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
    }
    
    //add food item 
    public void addFoodItemToXML(FoodItem foodObj) throws JAXBException{
        //set the path to post method
        webResource = client.resource(BASE_URI).path("inventory/addFoodItem");
        
        ClientResponse response = webResource.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).post(ClientResponse.class, foodObj);
        
//        String output = response.getEntity(String.class);
//        System.out.println("Server response : ");
//        System.out.println(output);
        
        if (response.getStatus() == 200) {
            FoodItemExists exists = response.getEntity(FoodItemExists.class);
            JAXBContext context = JAXBContext.newInstance(FoodItemExists.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(exists, System.out);
            
        } else if (response.getStatus() == 201) {
            FoodItemAdded added = response.getEntity(FoodItemAdded.class);

            JAXBContext context = JAXBContext.newInstance(FoodItemAdded.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(added, System.out);

        }
        
    }

    void getFoodItemFromXML(SelectedFoodItems selectedItems) throws JAXBException {
       //set the path to post method for get
        webResource = client.resource(BASE_URI).path("inventory/getFoodItem");
        
        ClientResponse response = webResource.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).post(ClientResponse.class, selectedItems);
        
//        String output = response.getEntity(String.class);
//        System.out.println("Server response : ");
//        System.out.println(output);

        if (response.getStatus() == 200) {
            RetrievedFoodItems r = response.getEntity(RetrievedFoodItems.class);
            JAXBContext context = JAXBContext.newInstance(RetrievedFoodItems.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(r, System.out);
            
        }
        else{
            System.out.println("Failed :: "+response.getStatus());
        }
    }

    
}
