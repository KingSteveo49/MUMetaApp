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
        return "open data";
    }
    
}
