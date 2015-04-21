/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

//Export Test
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author bmbolen2017
 */
public class Tools {
    
    //public static final Path defaultAppDir = FileSystems.getDefault().getPath("DefaultApp", (String) null);
    
    public static boolean compare(String s1,String s2)
    {
        return s1.equals(s2);
    }
    
    public static boolean compare(Project p1, Project p2)
    { //ToDo
        return true;
    }
    
    public static boolean compare(Action a1, Action a2)
    { //ToDo
        return true;
    }
    
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
      System.out.println(exc);
    }
  }
    
//    public static void exportApp(Path targetLocation)
//    {
//        try{
//        Files.copy(defaultAppDir,targetLocation);
//        // TODO: Also throw the DOM xml stuff into the new location
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
}
