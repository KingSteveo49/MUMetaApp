/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.File;
import java.io.IOException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

//Export Test
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * This class contains all static independent methods 
 * that are or may become of some use to multiple classes
 * 
 * @author bmbolen2017
 */
public class Tools {
    
    /**
     * Compares to see if 2 strings are the same [case sensitive]
     * and returns the result.
     * 
     * @param s1 First string
     * @param s2 Second string
     * @return 
     */
    public static boolean compare(String s1,String s2)
    {
        return s1.equals(s2);
    }
    
    /**
     * [WIP]
     * Compares to see if 2 Projects are the same
     * and returns the result.
     * 
     * @param p1 First project
     * @param p2 Second project
     * @return 
     */
    public static boolean compare(Project p1, Project p2)
    { //ToDo
        return true;
    }
    
    /**
     * [WIP]
     * Compares to see if 2 Actions are the same
     * and returns the result.
     * 
     * @param a1 First action
     * @param a2 Second action
     * @return 
     */
    public static boolean compare(Action a1, Action a2)
    { //ToDo
        return true;
    }
    
    /**
     * [WIP]
     * Compares to see if 2 Documents are the same
     * and returns the result.
     * 
     * @param d1 First Document
     * @param d2 Second Document
     * @return 
     */
    public static boolean compare(Document d1, Document d2)
    {//ToDo
        return false;
    }
    
    /**
     * Creates a Document from the XML located at Path
     * @param path Path to some XML
     * @return 
     */
    public static Document getDoc(String path){
        try{
            DOMParser parser = new DOMParser();
            parser.parse(path);
            Document dom = parser.getDocument();
            
            return dom;
        }catch(SAXException | IOException e){
            System.out.println("There was a problem while trying to get the document: "+e.getMessage());
            return null;
        }
    }
    
    /**
     * Saves document object as an XML file in path
     * @param document Document to save
     * @param path Path for the resulting XML file
     */
    public static void saveDoc(Document document, String path)
    {
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      
      DOMSource source = new DOMSource(document);
      StreamResult result = new StreamResult(new File(path));
      
      transformer.transform(source, result);
      
      System.out.println("File saved!");
    }
    catch(ParserConfigurationException | TransformerException exc)
    {
      System.out.println("error");
    }
  }
    
}
