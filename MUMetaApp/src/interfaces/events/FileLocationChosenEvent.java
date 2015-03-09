/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.events;

import interfaces.Event;

/**
 *
 * @author Stephen
 */
public class FileLocationChosenEvent implements interfaces.Event{
    
    private final String description, kind;
    private final Object data;
    
    public FileLocationChosenEvent(String d, String k, Object o){
        description = d;
        kind = k;
        data = o;
    }
    
     @Override
    public String getDescription(){
        return "This is an FileLocationChosen Event";
    }
    
    @Override
    public String getKind(){
        return "filelocationchosen";
    }
    
    @Override
    public String getData(){
        return (String) data;
    }
    
}
