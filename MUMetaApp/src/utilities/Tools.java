/*
 * The MIT License
 *
 * Copyright 2015 sdmiller2015.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


package utilities;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

//Export Test
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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
      exc.printStackTrace();
    }
  }
    
    public static String docToString(Document doc)
        {
            try{
                StringWriter sw = new StringWriter();
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                
                transformer.transform(new DOMSource(doc), new StreamResult(sw));
                String xml = sw.toString();
                xml = xml.replace("\"".subSequence(0, 1), "\\\"".subSequence(0, 2));
                xml = xml.replace("\n".subSequence(0, 1), " ".subSequence(0, 1));
                xml = xml.replace("\r".subSequence(0, 1), " ".subSequence(0, 1));
                return xml;
                
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
        
}