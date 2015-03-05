/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.events;

import interfaces.Event;

/**
 *
 * @author sdmiller2015
 */
public class DeleteEvent implements Event{
    
    private String description, kind;
    private Object data;
    
    public DeleteEvent(String d, String k, Object o){
        description = d;
        kind = k;
        data = o;
    }
    
     @Override
    public String getDescription(){
        return "This is a Delete Event";
    }
    
    @Override
    public String getKind(){
        return "delete";
    }
    
    @Override
    public String getData(){
        return "delete data";
    }
}
