/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.events;
import interfaces.*;

/**
 *
 * @author bmbolen2017
 */
public class OpenEvent implements Event{
    
    private String description, kind;
    private Object data;
    
    public OpenEvent(String d, String k, Object o){
        description = d;
        kind = k;
        data = o;
    }
    
    @Override
    public String getDescription(){
        return "This is an Open Event";
    }
    
    @Override
    public String getKind(){
        return "open";
    }
    
    @Override
    public String getData(){
        return "data";
    }
    
    public Object getDatas(){
        return data;
    }
    
}
