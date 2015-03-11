/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.events;

import utilities.Event;

/**
 *
 * @author sdmiller2015
 */
public class RenameEvent implements Event{
    
    private String description, kind;
    private Object data;
    
    public RenameEvent(String d, String k, Object o){
        description = d;
        kind = k;
        data = o;
    }
    
     @Override
    public String getDescription(){
        return "This is a Rename Event";
    }
    
    @Override
    public String getKind(){
        return "rename";
    }
    
    @Override
    public String getData(){
        return "rename data";
    }
    
}
