/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author bmbolen2017
 */
public class Action {
    
    private String kind;
    private String content;

    public Action(String k, String c)
    {
        kind = k;
        content = c;
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
