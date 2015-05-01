/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.Serializable;
import org.w3c.dom.Document;

/**
 * This class acts as an interface that is passed
 * between the ModelManager and the Controller. It contains 
 * all information necessary for the ModelManager and 
 * Controller to live in harmony.
 * 
 * @author bmbolen2017
 */
public class Project implements Serializable{
    
    private String path;
    private String content;
    private Document doc;
    private String action;

    /**
     * This constructor initializes the path of this 
     * project, content for this project, and the 
     * action needed to be taken on this project.
     * 
     * @param p Path of this project
     * @param c Content of this project
     * @param a Action on this project
     */
    public Project(String p, String c, String a)
    {
        path = p;
        content = c;
        action = a;
    }
    
    /**
     * This constructor initializes the path of this 
     * project, content for this project, the 
     * action needed to be taken on this project and 
     * the document with this project.
     * 
     * @param p Path of this project
     * @param c Content of this project
     * @param a Action on this project
     * @param d Document with this project
     */
    public Project(String p, String c, String a, Document d)
    {
        path = p;
        content = c;
        action = a;
        doc = d;
    }

    /**
     * Gets this project's Document
     * 
     * @return 
     */
    public Document getDoc() {
        return doc;
    }

    /**
     * Sets this project's Document
     * 
     * @param doc Document for this project
     */
    public void setDoc(Document doc) {
        this.doc = doc;
    }
    
    /**
     * Sets the action to be taken on this project
     * 
     * @param action Action on this project
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Gets the action for this project
     * 
     * @return 
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the path for this project
     * 
     * @param path Path of this project
     */
    public void setPath(String path) {
        this.path = path;
    }
    
    /**
     * Sets the content of this project
     * 
     * @param content Content for this project
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the path of the project
     * 
     * @return 
     */
    public String getPath() {
        return path;
    }

    /**
     * Gets the content of the project
     * 
     * @return 
     */
    public String getContent() {
        return content;
    }
    
    
}
