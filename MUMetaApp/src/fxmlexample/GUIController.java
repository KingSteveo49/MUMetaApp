package fxmlexample;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import utilities.Action;
 
public class GUIController {
    
    private static final GUIController instance = new GUIController();
 
    public static GUIController getInstance(){
        return instance;
    }
    
    @FXML 
    private Text actiontarget;
    
    @FXML 
    protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }
    
    @FXML
    protected void handleKeyInput(ActionEvent event){
        
    }
    
    @FXML
    protected void handleNewAction(ActionEvent event){
        
    }
    
    @FXML
    protected void handleOpenAction(ActionEvent event){
        
    }
    
    @FXML
    protected void handleSaveAction(ActionEvent event){
        
    }
    
    @FXML
    protected void handleSaveAsAction(ActionEvent event){
        
    }
    
    @FXML
    protected void handleExitAction(ActionEvent event){
        
    }
    
    public void manageAction(Action a){
        
        String eventKind = a.getKind();
        switch(eventKind)
        {
            case "open":
//                displayFileChooser();
                
            case "create":
                return;
                
            case "rename":
                return;
                
            case "filelocationchosen":
                return;
                
            case "displayFile":
                //displayFile(a);
                return;
            
            case "displayFileChooser":
                //displayFileChooser();
                return;
                
            case "delete":
                return;
                
            case "save":
                return;
                
            case "feedback":
                //displayFeedback(a);
                return;
                
            default:
                return;
        }
    }

}