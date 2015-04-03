package fxmlexample;
 
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.Action;
 
public class GUIController {
    
    private static final GUIController instance = new GUIController();
 
    public static GUIController getInstance(){
        return instance;
    }
    
    @FXML 
    TextArea feedBack;
//    @FXML
//    private GridPane rootElement; // this is my root element in FXML (fx:id="borderPane")
    
    @FXML
    protected void handleKeyInput(ActionEvent event){
        
    }
    
    @FXML
    protected void handleNewAction(ActionEvent event){
        
    }
    
    @FXML
    protected void handleOpenAction(ActionEvent event){
        Controller.getInstance().manageAction(new Action("open", null));
    }
    
    @FXML
    protected void handleSaveAction(ActionEvent event){
        displayFeedback(new Action(null, "User wants to save"));
    }
    
    @FXML
    protected void handleSaveAsAction(ActionEvent event){
    }
    
    @FXML
    protected void handleExitAction(ActionEvent event){
    }
    @FXML 
    protected void displayFeedback(Action a) {
        feedBack.appendText(a.getContent()+"\n");
    }
    
    @FXML
    protected void displayFileChooser() throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GUIFXML.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Open File");
        stage.setScene(new Scene(root1));  
        //stage.show();
        
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Project");
        final File file;
        file = fileChooser.showOpenDialog(stage);
        Controller.getInstance().manageAction(new Action("fileLocationChosen", file.getPath()));
}
    
    private void displayFile(Action a){
        System.out.println("this is the gui displaying the file");
    }
    
    public void manageAction(Action a) throws IOException{
        
        String eventKind = a.getKind();
        switch(eventKind)
        {
            case "displayFile":
                displayFile(a);
                return;
            case "displayFileChooser":
                displayFileChooser();
                return;
            case "feedback":
                displayFeedback(a);
                return;
            default:
                return;
        }
    }

}