package fxmlexample;
 
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utilities.Action;
 
public class GUIController {
    
    private static final GUIController instance = new GUIController();
 
    public static GUIController getInstance(){
        return instance;
    }
    
    @FXML 
    TextArea feedBack;
    @FXML
    GridPane optionsGridPane;
//    @FXML
//    private GridPane rootElement; // this is my root element in FXML (fx:id="borderPane")
    
    @FXML
    protected void handleKeyInput(ActionEvent event){
        
    }
    
    @FXML
    protected void handleNewAction(ActionEvent event){
        
    }
    
    @FXML
    protected void handleOpenAction(ActionEvent event) throws IOException{
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
    protected void displayFeedback(String s) {
        feedBack.appendText(s+"\n");
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
    @FXML
    private void displayFile() throws SAXException, IOException{
        System.out.println("The GUI is now attempting to display the file");
        
        DOMParser parser = new DOMParser();
        parser.parse("C:\\Users\\Stephen\\Desktop\\books.xml");
        Document dom = parser.getDocument();
        walkDOM(dom);
        
    }
    
    private void walkDOM(Document dom) {
        NodeList e = dom.getElementsByTagName("tape");
        
        for (int i = 0; i < e.getLength(); i++) {
      Node aNode = e.item(i);
      if (aNode.hasChildNodes())
        walkNode(aNode);
      else
        displayFeedback(aNode.getTextContent());
    }
     
  }
     private void walkNode(Node theNode) {
    NodeList children = theNode.getChildNodes();
   //printNode(theNode);
    for (int i = 0; i < children.getLength(); i++) {
      Node aNode = children.item(i);
      if (aNode.hasChildNodes())
        walkNode(aNode);
      else
        displayFeedback(aNode.getTextContent());
    }
  }
    
    private int row = 0;
    
    @FXML
    protected void testyTest(){
        final Label l = new Label("This was added dynamically: " + row);
        l.setId("label"+row);
        l.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                displayFeedback(l.getId()+ " was pressed adding new label");
                testyTest();
            }
        });
    
        optionsGridPane.add(l, 0, row);
        row++;
        
    }
    
    public void manageAction(Action a) throws IOException{
        
        String eventKind = a.getKind();
        switch(eventKind)
        {
            case "displayFile":
               // displayFile(a);
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