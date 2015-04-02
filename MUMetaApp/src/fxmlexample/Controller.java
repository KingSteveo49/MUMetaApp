package fxmlexample;

import utilities.Action;
import utilities.Project;
import utilities.Tools;

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
    
    private Project currentProject;

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
        String actionContent = a.getContent();
        
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
                ModelManager.getInstance().manageProject(new Project(actionContent,null,"getText"));
                return;
                
            case "save":
                save(a);
                return;
                
            default:
                return;
        }
    }
    
     public void manageProject(Project p)
        {
            String path = p.getPath();
            String content = p.getContent();
            String action = p.getAction();
            
            switch(action)
            {
                case "returningProject":
                    returnedProject(p);
                    break;
                    
                case "saveFeedback":
                    saveFeedback(p);
                    break;
                    
                default:
                    break;
            }
            
        }
    
    public void saveFeedback(Project p)
    {
        String content = p.getContent();
        if(content=="success")
        { 
            GUIController.getInstance().manageAction(new Action("feedback","Save Successful! Great Job!"));
        }
        else
        {
            GUIController.getInstance().manageAction(new Action("feedback","Save Failed! I am so sorry."));
        }
    }
     
    public void returnedProject(Project p)
    {
        if(status=="opening")
        {
            currentProject = p;
            GUIController.getInstance().manageAction(new Action("displayFile",currentProject.getContent()));
            status = "waiting";
        }
    }
    
    public void open(Action a)
    {
        if( status=="waiting" )
        {
            status = "opening";
            GUIController.getInstance().manageAction(new Action("displayFileChooser",null));
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
    
    public void save (Action a) {
        status = "saving";

        if(!Tools.compare( currentProject.getContent(), a.getContent() ))
        {
            ModelManager.getInstance().manageProject(new Project(currentProject.getPath(), a.getContent(),"setText"));
            currentProject.setContent(a.getContent());
        }
        else
        {
            GUIController.getInstance().manageAction(new Action("feedback","Nothing new to save! Good job being careful!"));
        }
        status = "waiting";
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
