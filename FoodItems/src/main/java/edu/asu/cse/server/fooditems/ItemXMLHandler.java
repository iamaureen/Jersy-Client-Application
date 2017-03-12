/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse.server.fooditems;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Ishrat Ahmed
 */
public class ItemXMLHandler {
    
    private static final Logger LOG = LoggerFactory.getLogger(ItemXMLHandler.class);

    public String addFood(FoodItem foodItem) throws FileNotFoundException, UnsupportedEncodingException, ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {


        String rs = null;
        String itemID = null;
        boolean itemFound = false;
        //System.properties("user.dir") gave the absolute path address for glassfish server instead of the current project location. so used this 
        //function to get the path to the project location and then the xml file
        String dir =  this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        String path = getFolderPath(dir);
        //String tempPath = "C:\\Users\\Ishrat Ahmed\\Documents\\NetBeansProjects\\FoodItems";
        String fullPath =  path;
        File file = new File(fullPath);
        if (file.exists()) {
            rs = "file found";
            InputStream inputStream= new FileInputStream(file);
	    Reader reader = new InputStreamReader(inputStream,"UTF-8");
	    InputSource src = new InputSource(reader);
            
            DocumentBuilderFactory builderFac = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFac.newDocumentBuilder();
            Document d = builder.parse(src);
            
            NodeList allNodes = d.getElementsByTagName("FoodItem");
            
            for (int i = 0; i < allNodes.getLength(); i++) {
                
                Element eachElement = (Element) allNodes.item(i);
                String category = eachElement.getElementsByTagName("category").item(0).getTextContent();
		String name = eachElement.getElementsByTagName("name").item(0).getTextContent();
                if (foodItem.getCategory().equals(category) && foodItem.getName().equals(name)) {
                    itemID = eachElement.getElementsByTagName("id").item(0).getTextContent();
                    itemFound = true;
                    break;
                }
            }
            
            if(itemFound){
                return itemID;
            }else{
                //add the new item
                Element eachElement = d.createElement("FoodItem");
                eachElement.setAttribute("country", foodItem.getCountry());

                //setting the ID of new Element
                Element elementID = d.createElement("id");
                elementID.setTextContent(String.valueOf(foodItem.getID()));
                
                //name
                Element elementName = d.createElement("name");
                elementName.setTextContent(foodItem.getName());
                
                //desecription
                Element elementDescription = d.createElement("description");
                elementDescription.setTextContent(foodItem.getDescription());

                //category
                Element elementCategory = d.createElement("category");
                elementCategory.setTextContent(foodItem.getCategory());

                //price
                Element elementPrice = d.createElement("price");
                elementPrice.setTextContent(foodItem.getPrice());
                
                //appending all the elements
		eachElement.appendChild(elementID);
		eachElement.appendChild(elementName);
		eachElement.appendChild(elementDescription);
		eachElement.appendChild(elementCategory);
		eachElement.appendChild(elementPrice);
                
                Element rootTag = (Element) d.getElementsByTagName("FoodItemData").item(0);
                rootTag.appendChild(eachElement);
                //saving the data to file
                Transformer xform = TransformerFactory.newInstance().newTransformer();
                Result output = new StreamResult(fullPath);
                Source input = new DOMSource(d);
                xform.transform(input, output);
                return "true";
            }     
            	    

                        
        } else {
            rs = fullPath+ " ::: File not found";
            return rs;
        }
        
        
    }

    private String getFolderPath(String dir) throws UnsupportedEncodingException {
        
            //dir = file:/C:/Users/Ishrat Ahmed/Documents/NetBeansProjects/FoodItems/target/FoodItems/WEB-INF/classes/edu/asu/cse/server/fooditems/ItemXMLHandler.class
            //first split against file:/, then first /FoodItems/
            //we get C:\Users\Ishrat Ahmed\Documents\NetBeansProjects\
            String fullPath = URLDecoder.decode(dir, "UTF-8");
            String pathArr[] = fullPath.split("file:/");
            fullPath = pathArr[1];
            String pathArr1[] = fullPath.split("/FoodItems/");
            fullPath = pathArr1[0];           
            String path = "";
            // to read a file from webcontent
            path = new File(fullPath).getPath() + File.separatorChar + "FoodItems\\FoodItemData.xml";
            return path;
            
    }
    
}
