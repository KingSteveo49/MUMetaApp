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
    
    public String getDescription(){
        return "Hello";
    }
    
    public String getKind(){
        return "Hello";
    }
    
    public String getData(){
        return "Hello";
    }
    
}
