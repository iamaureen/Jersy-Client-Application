package edu.asu.cse.server.fooditems;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("inventory")
public class FoodItemsServer {

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

  
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

}
