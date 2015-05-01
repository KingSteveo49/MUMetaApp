package fxmlexample;

import java.io.IOException;
import utilities.Action;
import utilities.Project;
import utilities.Tools;

/**
 * The Controller is the middle man that
 * communicates with the ModelManager via Projects
 * and with the GUI via Actions
 * 
 * @author sdmiller2015
 */
public class Controller {
    
    private Project currentProject;

    /** [Deprecated] */
    private static final Controller instance = new Controller();
     

    /**
     * [Deprecated]
     * private constructor to avoid client applications to use constructor
     */
    Controller(){}
 
    /**
     * [Deprecated]
     * Returns the singleton for the Controller
     * @return 
     */
    public static Controller getInstance(){
        return instance;
    }

    private String status = "waiting";
    
    /**
     * This method handles all incoming Actions or passes
     * them to a method that will handle the Action
     * 
     * @param a Action to handle
     */
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
//                factory.getGUI().manageEvent(e);
                status = "waiting";
                return;
                
            case "fileLocationChosen":
                System.out.println("This is the controller about to send the file location to the model");
                factory.getModelManager().manageProject(new Project(actionContent,null,"getXML"));
                return;
                
            case "save":
                save(a);
                return;
                
            default:
                return;
        }
    }
    
    /**
     * This method handles all incoming Projects or
     * passes them to a method that handles the Project
     * 
     * @param p Incoming Project
     */
     public void manageProject(Project p){
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
    
    /**
     * That method takes a project that contains feedback information
     * pertaining to a save
     * 
     * @param p Project with save results
     */
    public void saveFeedback(Project p){
        String content = p.getContent();
        if("success".equals(content))
        { 
            factory.getGUI().manageAction(new Action("feedback","Save Successful! Great Job!"));
        }
        else
        {
            factory.getGUI().manageAction(new Action("feedback","Save Failed! I am so sorry."));
        }
    }
     
    /**
     * This methods sends a project's content and document to the GUI 
     * to be displayed
     * 
     * @param p Project with Contents and Document
     */
    public void returnedProject(Project p){
        if("opening".equals(status))
        {
            System.out.println("This is the controller returning the project to the gui");
            currentProject = p;
            factory.getGUI().manageAction(new Action("displayFile",p.getContent(),p.getDoc()));
            status = "waiting";
        }
    }
    
    /**
     * This method tells the GUI to display a file chooser
     * 
     * @param a [Deprecated?] Action for something
     */
    public void open(Action a){
        if( "waiting".equals(status) )
        {
            status = "opening";
            factory.getGUI().manageAction(new Action("displayFileChooser",null));
        }
    }
      
    /**
     * [Deprecated]
     * Close the currently opened project
     * 
     * @param e Aciton
     * @return 
     */
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
    
    /**
     * [Deprecated]
     * Creates a new project
     * 
     * @param e
     * @return 
     */
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
    
    /**
     * Saves the project contained in the action to 
     * currentProject if it differs form the currentProject
     * 
     * @param a Action containing a project
     */
    public void save (Action a){
        if(status.equals("waiting")){
            status = "saving";
        }
        
        if(currentProject==null || Tools.compare( currentProject.getDoc(), a.getDoc()))
        {
            factory.getGUI().manageAction(new Action("feedback","Nothing new to save! Good job being careful!"));
        }
        else
        {
            factory.getModelManager().manageProject(new Project(currentProject.getPath(), null,"setXML", a.getDoc()));
            currentProject.setContent(a.getContent());
            
        }
        status = "waiting";
    } 
    
    /**
     * [Deprecated]
     * Deletes the currentProject
     * 
     * @param e
     * @return 
     */
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
