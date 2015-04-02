/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import org.w3c.dom.Document;

/**
 *
 * @author bmbolen2017
 */
public class Action {
    
    private String kind;
    private String content;
    private Document doc;

    public Action(String k, String c)
    {
        kind = k;
        content = c;
    }
    public Action(String k, String c, Document d)
    {
        kind = k;
        content = c;
        doc = d;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }
    
    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKind() {
        return kind;
    }

    public String getContent() {
        return content;
    }
    
    
}
