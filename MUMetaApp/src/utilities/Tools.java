/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

//Export Test
import java.nio.file.Files;

/**
 *
 * @author bmbolen2017
 */
public class Tools {
    
    public static final String defaultAppDir = "DefaultApp";
    
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
    
    public static void exportApp(String targetLocation)
    {
        Files.copy(defaultAppDir,targetLocation);
        // TODO: Also throw the DOM xml stuff into the new location
    }
}
