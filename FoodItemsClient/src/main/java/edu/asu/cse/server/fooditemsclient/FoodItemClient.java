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
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

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
    
    
    
    void addFoodItemToXML(String addItem) throws JAXBException {
        
        webResource = client.resource(BASE_URI).path("inventory");
        
        ClientResponse response = webResource.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).post(ClientResponse.class, addItem);
        
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

    

    void getFoodItemToXML(String item) throws JAXBException {
        webResource = client.resource(BASE_URI).path("inventory");
        
        ClientResponse response = webResource.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).post(ClientResponse.class, item);
        
         if (response.getStatus() == 200) {
            RetrievedFoodItems r = response.getEntity(RetrievedFoodItems.class);
            JAXBContext context = JAXBContext.newInstance(RetrievedFoodItems.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(r, System.out);
            
        }
        else{
            System.out.println("Failed :: "+response.getStatus()+"  " +response.toString());
        }
        
    }
    
    void inValidDemo(String invalidString) throws JAXBException{
        
        webResource = client.resource(BASE_URI).path("inventory");
        
        ClientResponse response = webResource.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).post(ClientResponse.class, invalidString);
        
         if (response.getStatus() == 200) {
            InvalidMessage r = response.getEntity(InvalidMessage.class);
            JAXBContext context = JAXBContext.newInstance(InvalidMessage.class);            
          
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(r, System.out);
            
        }
        else{
            System.out.println("Failed :: "+response.getStatus()+"  " +response.toString());
        }
        
    }

    
    
}
