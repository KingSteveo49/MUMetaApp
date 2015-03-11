package mumetaapp;
import utilities.Event;
import interfaces.events.OpenEvent;
import interfaces.infotypes.ProjectInfoType;

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
    
    public GUI gui;
    
    public ModelManager mm;
  
    private String status = "waiting";
    
    public void manageEvent(Event e){
        
        String eventKind = e.getKind();
        
        switch(eventKind)
        {
            case "open":
                if(status=="opening")
                {
                    OpenEvent oe = (OpenEvent) e;
                    mm.manageInfoType(new ProjectInfoType("","",oe.getDatas(),""));
                    status = "waiting";
                }
                else if( status=="waiting" )
                {
                    status = "opening";
                    gui.manageEvent(new OpenEvent("","",""));
                }
                return;
            case "displayFile":
                status = "feeding";
                gui.manageEvent(e);
                status = "waiting";
                return;
                
            case "filelocationchosen":
                mm.manageInfoType(new ProjectInfoType("","",e.getData(),""));
                status = "waiting";
                return;
                
            case "save":
                return;
                
            default:
                return;
        }
    }
}
