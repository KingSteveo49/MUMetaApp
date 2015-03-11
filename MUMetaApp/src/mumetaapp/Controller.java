package mumetaapp;

import utilities.Action;
import utilities.Project;

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

    private static final Controller instance = new Controller();
     
    //private constructor to avoid client applications to use constructor
    private Controller(){}
 
    public static Controller getInstance(){
        return instance;
    }
    
//    public GUI gui = GUI.getInstance();
    
//    public ModelManager mm = ModelManager.getInstance();
    private String status = "waiting";
    
    public void manageAction(Action a){
        
        String actionKind = a.getKind();
        
        switch(actionKind)
        {
            case "open":
                open(a);
                return;
            case "displayFile":
                status = "feeding";
//                GUI.getInstance().manageEvent(e);
                status = "waiting";
                return;
                
            case "fileLocationChosen":
                ModelManager.getInstance().manageProject(new Project(a.getContent(),null,"get"));
//                status = "waiting";
                return;
                
            case "save":
                return;
                
            default:
                return;
        }
    }
    
     public void manageProject(Project p)
        {
            String path = p.getPath(); //i.getPath()
            String content = p.getContent();
            String action = p.getAction();
            
            switch(action)
            {
                case "returningProject":
                    returnedProject(p);
                    break;
                default:
                    break;
            }
            
        }
     
    public void returnedProject(Project p)
    {
        if(status=="opening")
        {
            GUI.getInstance().manageAction(new Action("displayFile",p.getContent()));
            status = "waiting";
        }
    }
    
    public void open(Action a)
    {
//        if(status=="opening")
//        {
//            OpenEvent oe = (OpenEvent) e;
//            ModelManager.getInstance().manageInfoType(new ProjectInfoType("","",oe.getDatas(),""));
//            status = "waiting";
//        }
        /*else*/ if( status=="waiting" )
        {
            status = "opening";
            GUI.getInstance().manageAction(new Action("displayFileChooser",null));
        }
    }
      
    public Action close (Action e){
        
          if (status== "closing"){ 
             
          Action ce = new Action("File selected passsing path to model", "close");
                    
//          ModelManager.manageEvent(ce);
                return null;
         }
        else {
              Action ce = new Action(null,null);
              
              return ce;
     }       
    }
    
    public Action create (Action e){
        if (status=="waiting") {
            
          status="creating";
          
          Action cc = new Action("create", null);
          
          return cc;
        }
        
          if (status== "creating"){ 
             
          Action cc = new Action("File selected passsing path to model", "create");
                    
//          ModelManager.manageEvent(cc);
                
         }
        else {
              Action cc = new Action(null,null);
              
              return cc;
         } 
          
          return null;
    }
    
    public Action save (Action e) {
         if (status=="waiting") {
            
         status="saving";
          
         Action se = new Action("save", null);
          
          return se;
        }
        
          if (status== "saving"){ 
             
          Action se = new Action("File saved passsing path to model", "save");
                    
//          ModelManager.manageInfo(se);
                
         }
        else {
              Action se = new Action(null,null);
              
              return se;
         } 
          return null;
    } 
    
    public Action delete (Action e) {
        
          if (status== "deleting"){ 
             
           Action se = new Action("File selected passsing path to model", "delete");
                    
//          ModelManager.manageEvent(se);
                
         }
        else {
              Action se = new Action(null,null);
              
              return se;
         } 
          return null;
    } 
    
}
