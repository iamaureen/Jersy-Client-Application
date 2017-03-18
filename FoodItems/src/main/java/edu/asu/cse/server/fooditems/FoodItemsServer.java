package edu.asu.cse.server.fooditems;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
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
    @Path("/addFoodItem")
    @Consumes(MediaType.APPLICATION_XML)
    public Response addFoodItem(FoodItem foodItem) {
        
        try {

            ItemXMLHandler foodItemXML = new ItemXMLHandler();            
            
            if (foodItem != null) {

                String result = foodItemXML.addFood(foodItem);
                
                if (result.equals("true")) {
                    FoodItemAdded added = new FoodItemAdded();
                    added.setId(foodItem.getID());
                    return Response.status(201).entity(added).build();
                    
                } else if (!result.equals("false") && result != null) {
                    FoodItemExists exists = new FoodItemExists();
                    exists.setId(Integer.parseInt(result));
                    return Response.status(200).entity(exists).build();
                } else {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IllegalArgumentException();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    @POST
    @Path("/getFoodItem")
    @Produces(MediaType.APPLICATION_XML)
    public Response getFoodItem(String selectedFoodItems) throws UnsupportedEncodingException, ParserConfigurationException, SAXException, IOException, JAXBException{

        
        JAXBContext jaxbContext = JAXBContext.newInstance(SelectedFoodItems.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringBuffer xmlStr = new StringBuffer(selectedFoodItems);
        SelectedFoodItems sfi = (SelectedFoodItems) jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader(xmlStr.toString())));
               
        List<Integer> foodItemId = sfi.getFoodItem();        
        
        ItemXMLHandler foodItemXML = new ItemXMLHandler();  
        
        RetrievedFoodItems retrievedFoodItems = foodItemXML.getFoodItem(foodItemId);
        //String r  = foodItemXML.getFoodItem(foodItemId);
        
        return Response.status(200).entity(retrievedFoodItems).build();
    }

}
