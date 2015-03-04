package mumetaapp;
import interfaces.Event;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sdmiller2015
 */
public class Controller {
    private String status = "waiting";
    
    public void manageEvent(Event e){
        
        String eventKind = e.getKind();
        
        switch(eventKind)
        {
            case "open":
                //Check Status
                //Update Status
                //Send Open Event back to GUI
                //Steve Says Open Again w/ Path
                //Send Path to Model
                //Model return Info
                //Send Info to GUI
                return;
                
            case "delete":
                return;
                
            case "save":
                return;
                
            default:
                return;
        }
    }
}
