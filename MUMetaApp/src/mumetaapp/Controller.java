package mumetaapp;
import interfaces.Event;
import interfaces.events.*;

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
    
    public OpenEvent open(Event e) {
       
         if (status== "waiting"){ 
                    
         status="opening";
                    
          OpenEvent oe = new OpenEvent("intial open GUI needs to display file selector", "open", true);
                   
          return oe;
          
         }
         
         if (status== "opening"){ 
             
          ProjectInfoType oe = new ProjectInfoType("File selected passsing path to model", "open", e.getData());
                    
          ModelManager.manageInfoType(oe);
                
         }
         else {
              OpenEventsdc oe = new OpenEvent(null,null,null);
              
              return oe;
      } 
    }
    
    public CloseEvent close (Event e){
        
          if (status== "closing"){ 
             
          CloseEvent ce = new CloseEvent("File selected passsing path to model", "close", e.getDescription());
                    
          ModelManager.manageEvent(ce);
                
         }
        else {
              CloseEvent ce = new CloseEvent(null,null,null);
              
              return ce;
     }       
    }
    
    public CreateEvent create (Event e){
        if (status=="waiting") {
            
          status="creating";
          
          CreateEvent cc = new CreateEvent("intial create, send to GUI. GUI needs to display","create", true);
          
          return cc;
        }
        
          if (status== "creating"){ 
             
          CreateEvent cc = new CreateEvent("File selected passsing path to model", "create", e.getData());
                    
          ModelManager.manageEvent(cc);
                
         }
        else {
              CreateEvent cc = new CreateEvent(null,null,null);
              
              return cc;
         } 
    }
    
    public SaveEvent save (Event e) {
         if (status=="waiting") {
            
         status="saving";
          
         SaveEvent se = new SaveEvent("intial save, GUI needs to display the save options","save", true);
          
          return se;
        }
        
          if (status== "saving"){ 
             
          SaveEvent se = new SaveEvent("File saved passsing path to model", "save", e.getData());
                    
          ModelManager.manageEvent(se);
                
         }
        else {
              SaveEvent se = new SaveEvent(null,null,null);
              
              return se;
         } 
    } 
    
    public DeleteEvent delete (Event e) {
        
          if (status== "deleting"){ 
             
           DeleteEvent se = new DeleteEvent("File selected passsing path to model", "delete", e.getDescription());
                    
          ModelManager.manageEvent(se);
                
         }
        else {
              DeleteEvent se = new DeleteEvent(null,null,null);
              
              return se;
         } 
    } 
    
    
      
    public void manageEvent(Event e){
        
        String eventKind = e.getKind();
        
        switch(eventKind)
        {
            case "open":
                open(e);
               
                

                
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
            
            case "close":
                close (e);
                
                return;
                
            case "create":
                create (e);
                return;
                
            case "save":
                save(e);
                
                return;
                
            default:
                return;
        }
    }
}
