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
        return "Hello";
    }
    
    @Override
    public String getKind(){
        return "Hello";
    }
    
    @Override
    public String getData(){
        return "Hello";
    }
    
}
