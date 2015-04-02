/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.Serializable;
import org.w3c.dom.Document;

/**
 *
 * @author bmbolen2017
 */
public class Project implements Serializable{
    
    private String path;
    private String content;
    private Document doc;
    // Set/Get
    private String action;

    public Project(String p, String c, String a)
    {
        path = p;
        content = c;
        action = a;
    }
    
    public Project(String p, String c, String a, Document d)
    {
        path = p;
        content = c;
        action = a;
        doc = d;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }
    
    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public String getContent() {
        return content;
    }
    
    
}
