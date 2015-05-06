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
