package edu.asu.cse.server.fooditems;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.SAXException;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("inventory")
public class FoodItemsServer {

    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response FoodItemService(String request) throws JAXBException, FileNotFoundException, ParserConfigurationException, SAXException, IOException{
        
        if (request.contains("NewFoodItems")) {

            Response response=null;
            JAXBContext jaxbContext = JAXBContext.newInstance(NewFoodItems.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringBuffer xmlStr = new StringBuffer(request);
            NewFoodItems nfi = (NewFoodItems) jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader(xmlStr.toString())));

            List<FoodItem> obj = nfi.getFoodItem();

            for (int i = 0; i < obj.size(); i++) {
                
                FoodItem foodObj = new FoodItem();
                foodObj = obj.get(i);               
               
                try {

                    ItemXMLHandler foodItemXML = new ItemXMLHandler();

                    if (foodObj != null) {

                        String result = foodItemXML.addFood(foodObj);

                        if (result.equals("true")) {
                            FoodItemAdded added = new FoodItemAdded();
                            added.setId(foodObj.getID());
                            response=Response.status(201).entity(added).build();

                        } else if (!result.equals("false") && result != null) {
                            FoodItemExists exists = new FoodItemExists();
                            exists.setId(Integer.parseInt(result));
                            response=Response.status(200).entity(exists).build();
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;

        }else if(request.contains("SelectedFoodItems")){
            JAXBContext jaxbContext = JAXBContext.newInstance(SelectedFoodItems.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringBuffer xmlStr = new StringBuffer(request);
            SelectedFoodItems sfi = (SelectedFoodItems) jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader(xmlStr.toString())));

            List<Integer> foodItemId = sfi.getFoodItem();        

            ItemXMLHandler foodItemXML = new ItemXMLHandler();  

            RetrievedFoodItems retrievedFoodItems = foodItemXML.getFoodItem(foodItemId);
            //String r  = foodItemXML.getFoodItem(foodItemId);

            return Response.status(200).entity(retrievedFoodItems).build();
        }else{
            //invalid message            
           InvalidMessage invalidMsg = new InvalidMessage();
           
           return Response.status(200).entity(invalidMsg).build();
        }
        
        
    }
  


}
