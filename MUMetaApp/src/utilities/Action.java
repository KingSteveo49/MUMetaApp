/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import org.w3c.dom.Document;

/**
 * This class acts as an interface that is passed
 * between the GUI and the Controller. It contains 
 * all information necessary for the GUI and Controller
 * to live in harmony.
 * 
 * @author bmbolen2017
 */
public class Action {
    
    private String kind;
    private String content;
    private Document doc;

    /**
     * This constructor takes in what kind of
     * action this is and what content is needed
     * for said action
     * 
     * @param k the kind of action
     * @param c the content necessary for this action
     */
    public Action(String k, String c)
    {
        kind = k;
        content = c;
    }
    
    /**
     * This constructor takes in what kind of
     * action this is and what content is needed
     * for said action and a DOM
     * 
     * @param k the kind of action
     * @param c the content necessary for this action
     * @param d the DOM associated with this action
     */
    public Action(String k, String c, Document d)
    {
        kind = k;
        content = c;
        doc = d;
    }
    
    /**
     * Get the Document
     * 
     * @return 
     */
    public Document getDoc() {
        return doc;
    }
    
    /**
     * Sets this actions Document
     * 
     * @param doc the document for this action
     */
    public void setDoc(Document doc) {
        this.doc = doc;
    }
    
    /**
     * Set what kind of action this is
     * 
     * @param kind the kind of action
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * Set the content of this action
     * 
     * @param content the content of this action
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get the kind of action this is
     * 
     * @return 
     */
    public String getKind() {
        return kind;
    }

    /**
     * Get the content for this action
     * @return 
     */
    public String getContent() {
        return content;
    }
    
    
}
