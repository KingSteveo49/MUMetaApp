package fxmlexample;
 
import java.io.File;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utilities.Action;
 
public class GUIController {
    
    @FXML TextArea feedBack, selectedNameTextArea, selectedDescTextArea;
    @FXML GridPane workAreaGridPane, rootElement;
    @FXML Document currentDoc;
    @FXML Label mainTitle, selectedNameLabel, selectedDescLabel;
    @FXML TreeView contentTreeView;
    @FXML TreeItem previouslySelectedTreeItem;
    @FXML Scene scene;
    @FXML MenuBar menuBar;
    
    
    @FXML
    public void initialize(){
        factory.setGUIController(this);
    }
    
    @FXML
    protected void handleKeyInput(ActionEvent event){
        
    }
    @FXML
    protected void handleNewAction(ActionEvent event){
        
    }
    @FXML
    protected void handleOpenAction(ActionEvent event){
        factory.getController().manageAction(new Action("open", null));
    }
    @FXML
    protected void handleSaveAction(ActionEvent event){
        factory.getController().manageAction(new Action("save", null, currentDoc));
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
        feedBack.appendText(s);
    }
    @FXML
    protected void displayFileChooser(){
        try{

            final FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Project");
            File file;
            
            file = fileChooser.showOpenDialog(null);
            System.out.println(file.getPath());
            factory.getController().manageAction(new Action("fileLocationChosen", file.getCanonicalPath()));
            
        }catch(IOException e){
            System.out.println("There was a problem while trying to display the file chooser: "+e.toString());
        }
}
    
    @FXML
    private void displayFile(Action a){
       
        System.out.println("The GUI is now attempting to display the file");
        
        currentDoc = a.getDoc();
        System.out.println(contentTreeView.getId());
        
        contentTreeView.setRoot(new TreeItem("My App"));
        
        contentTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable,TreeItem<String> old_val, TreeItem<String> new_val) {
                TreeItem<String> selectedItem = new_val;
                if(previouslySelectedTreeItem==null){
                    previouslySelectedTreeItem = selectedItem;
                }
                try{
                if(!"".equals(selectedNameTextArea.getText())){
                    String newTreeValue = selectedNameTextArea.getText();
                    String oldtreeValue = previouslySelectedTreeItem.getValue().toString();
                    if(!previouslySelectedTreeItem.getValue().equals(newTreeValue)){
//                        previouslySelectedTreeItem.set
                        previouslySelectedTreeItem.setValue(newTreeValue);
                        displayFeedback("Changed: "+oldtreeValue+" to: "+ newTreeValue+"\n");
                    }else{
                        displayFeedback("Nothing to update \n");
                    }
                    
                    
                    
                    
                    XPath xpath = XPathFactory.newInstance().newXPath();
                    Node node = (Node) xpath.evaluate("//*[@id='header']", currentDoc, XPathConstants.NODE);
                } else {
                    displayFeedback("did nothing\n");
                }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    
                }
                
                mainTitle.setText(selectedItem.getValue());
                
                if(!selectedNameLabel.isVisible()){
                    toggleItemNameDescVisable();
                }
                // do what ever you want
                selectedNameTextArea.setText(selectedItem.getValue());
                previouslySelectedTreeItem = selectedItem;
            }

        });

        walkNode(a.getDoc().getDocumentElement(), contentTreeView.getRoot());
        
        mainTitle.setText(contentTreeView.getRoot().getValue().toString());
        mainTitle.setFont(new Font("Cambria", 32));
        mainTitle.setVisible(true);
    }
    
    private void toggleItemNameDescVisable(){
        if(selectedNameLabel.isVisible()){
            selectedNameLabel.setVisible(false);
            selectedNameTextArea.setVisible(false);
            selectedDescLabel.setVisible(false);
            selectedDescTextArea.setVisible(false);
        }else{
            selectedNameLabel.setVisible(true);
            selectedNameTextArea.setVisible(true);
            selectedDescLabel.setVisible(true);
            selectedDescTextArea.setVisible(true);
        }
    }
    
    private void walkNode(Node theNode, TreeItem rootItem) {
    NodeList children = theNode.getChildNodes();
    TreeItem item = null;
    String value = "NOT INITILIZED";
    for (int i = 0; i < children.getLength(); i++) {
        Node aNode = children.item(i);
        if (aNode.hasChildNodes()){
            
            value = aNode.getAttributes().getNamedItem("id").toString();
            String delims = "[\"]+";
            String[] splitString = value.split(delims);
            value = splitString[1];
            
            item = new TreeItem(value);
            //item = new TreeItem(aNode.getAttributes().getNamedItem("id").toString());
            rootItem.getChildren().add(item);
            rootItem.setExpanded(true);
            
            walkNode(aNode, item);
      }
        else{
            displayFeedback(aNode.getTextContent());
      }
    }
    
  }

    public void setScene(Scene scene) { 
        this.scene = scene; 
    }
    @FXML
    public void resizeElements(){
        double w = scene.getWidth();
        double h = scene.getHeight();
        Window window = scene.getWindow();
        
//        if(w<1117){
//            window.setWidth(1117);
//        }
//        if(h<752){
//            window.setHeight(790);
//        }
        
        
        w = scene.getWidth();
        h = scene.getHeight();
        System.out.println("test:" + feedBack.getHeight());
        
        
       
        
    }
    
    private int row = 0;
    
    @FXML
    protected void testyTest(){
        final Label l = new Label("This was added dynamically: " + row);
        l.setId("label"+row);
        l.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                displayFeedback(l.getId()+ " was pressed, adding new label \n");
                testyTest();
            }
        });
        row++;
    }
    
    public void manageAction(Action a){
        
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