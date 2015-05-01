package fxmlexample;
 
import java.io.File;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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
    @FXML String currentDescValue, previousDescValue;
    @FXML String uniqueIdentifier = "name";
    //Include any other node types that are part of an item that should not be
    //displayed in the tree view
    @FXML String[] excludedNodeTypes = {"description", "img"};
    
    
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
        
        contentTreeView.setRoot(new TreeItem("My App"));
        
        contentTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable,TreeItem<String> old_val, TreeItem<String> new_val) {
                TreeItem<String> selectedItem = new_val;
                if(previouslySelectedTreeItem==null){
                    previouslySelectedTreeItem = selectedItem;
                    
                }
                
                String newTreeValue = selectedNameTextArea.getText();
                String oldtreeValue = previouslySelectedTreeItem.getValue().toString();
                
                String lookupName = getUniqueLookupName(previouslySelectedTreeItem);
                String lookupDesc = getUniqueLookupName(selectedItem);
                
                try{
                    
                if(!"".equals(selectedNameTextArea.getText())){
                    
                    if(validateNameChange(newTreeValue, oldtreeValue, previouslySelectedTreeItem)){
                        
                        //This is where the dom should be edited to updete the newe value
                        displayFeedback(lookupName+"\n");
                        currentDoc = updateDocumentName(currentDoc, lookupName, newTreeValue);
                        
                        //This is where the value in the tree should be updated
                        previouslySelectedTreeItem.setValue(newTreeValue);
                        displayFeedback("Changed: "+oldtreeValue+" to: "+ newTreeValue+"\n");
                        
                    }
                } else {
                    displayFeedback("did nothing\n");
                }
                if(!"".equals(selectedDescTextArea.getText())){
                    
                    if(!previousDescValue.equals(selectedDescTextArea.getText())){
                        
                        currentDoc = updateDocumentDesc(currentDoc, lookupName, selectedDescTextArea.getText());
                    }
                } else {
                    displayFeedback("did nothing\n");
                }
                }catch(Exception e){
                    System.out.println(e.toString());
                    
                }
                
                mainTitle.setText(selectedItem.getValue());
                
                if(!selectedNameLabel.isVisible()){
                    toggleItemNameVisable();
                }
                displayFeedback(lookupDesc+"\n");
                if(lookupDesc.split("_").length == 1){
                    selectedDescLabel.setVisible(false);
                    selectedDescTextArea.setVisible(false);
                }else{
                    selectedDescLabel.setVisible(true);
                    selectedDescTextArea.setVisible(true);
                }
                
                
                // do what ever you want
                selectedNameTextArea.setText(selectedItem.getValue());
                String descValue = getItemDescription(currentDoc, lookupDesc);
                selectedDescTextArea.setText(descValue);
                previousDescValue = descValue;
                previouslySelectedTreeItem = selectedItem;
            }

        });

        walkNode(a.getDoc().getDocumentElement(), contentTreeView.getRoot());
        
        mainTitle.setText(contentTreeView.getRoot().getValue().toString());
        mainTitle.setFont(new Font("Cambria", 32));
        mainTitle.setVisible(true);
    }
    
    private Document updateDocumentName(Document doc, String lookupName, String newValue){
        
        String[] splitLookupName = lookupName.split("_");
        String[] uniqueValue;
        
        
        //There are only 2 options for length of the lookup name 1 or 2
        //The else will be triggered for ITEMS in the document
        //The if will be triggered for the MENUS in the document
        if(splitLookupName.length == 1){
            NodeList possibleUpdates = doc.getElementsByTagName("menu");
            String delims = "[\"]+";
            
            for(int i = 0; i < possibleUpdates.getLength(); i++){
                Node n = possibleUpdates.item(i);
                uniqueValue = n.getAttributes().getNamedItem(uniqueIdentifier).toString().split(delims);
                if(uniqueValue[1].equalsIgnoreCase(splitLookupName[0])){
                    doc.getElementsByTagName("menu").item(i).getAttributes().getNamedItem(uniqueIdentifier).setTextContent(newValue);
                    displayFeedback(doc.getElementsByTagName("menu").item(i).getAttributes().getNamedItem(uniqueIdentifier).toString()+"\n");
                    displayFeedback("Line above should be new doc value \n");
                }
            }
        
        }else{
            NodeList possibleUpdates = doc.getElementsByTagName("item");
            String delims = "[\"]+";
            
            
            for(int i = 0; i<possibleUpdates.getLength(); i++){
                Node n = possibleUpdates.item(i);
                uniqueValue = n.getAttributes().getNamedItem(uniqueIdentifier).toString().split(delims);
                if(uniqueValue[1].equalsIgnoreCase(splitLookupName[0])){
                    String parent = n.getParentNode().getAttributes().getNamedItem(uniqueIdentifier).toString().split(delims)[1];
                    if(parent.equalsIgnoreCase(splitLookupName[1])){
                        doc.getElementsByTagName("item").item(i).getAttributes().getNamedItem(uniqueIdentifier).setTextContent(newValue);
                        displayFeedback(doc.getElementsByTagName("item").item(i).getAttributes().getNamedItem(uniqueIdentifier).toString()+"\n");
                        displayFeedback("Line above should be new doc value \n");
                    }
                }
            }
            
        }
        
        
        return doc;
    }
    
    private Document updateDocumentDesc(Document doc, String lookupName, String newValue){
        String delims = "[\"]+";
        String[] uniqueValue;
        String[] splitLookupName = lookupName.split("_");
        
        NodeList possibleUpdates = doc.getElementsByTagName("item");
            
            
            for(int i = 0; i<possibleUpdates.getLength(); i++){
                Node n = possibleUpdates.item(i);
                uniqueValue = n.getAttributes().getNamedItem(uniqueIdentifier).toString().split(delims);
                if(uniqueValue[1].equalsIgnoreCase(splitLookupName[0])){
                    String parent = n.getParentNode().getAttributes().getNamedItem(uniqueIdentifier).toString().split(delims)[1];
                    if(parent.equalsIgnoreCase(splitLookupName[1])){
                        
                        doc.getElementsByTagName("description").item(i).setTextContent(newValue);
                        displayFeedback("Changed desc value");
                        
                    }
                }
            }
        return doc;
    }
    
    private String getUniqueLookupName(TreeItem lookupTreeItem){
        String lookupName = lookupTreeItem.getValue().toString();
        TreeItem parentOfLookup = lookupTreeItem.getParent();
        if(parentOfLookup!=null){
            while(!parentOfLookup.getValue().toString().equalsIgnoreCase(contentTreeView.getRoot().getValue().toString())){
                lookupName = lookupName+"_"+parentOfLookup.getValue().toString();
                parentOfLookup = parentOfLookup.getParent();
            }
        }else{
            lookupName ="rootElement";
        }
        return lookupName;
    }
    
    private String getItemDescription(Document doc, String lookupName){
        displayFeedback("Getting item description\n");
        String description = "";
        String delims = "[\"]+";
        String[] uniqueValue;
        String[] splitLookupName = lookupName.split("_");
        
        NodeList possibleUpdates = doc.getElementsByTagName("item");
            
            
            for(int i = 0; i<possibleUpdates.getLength(); i++){
                Node n = possibleUpdates.item(i);
                uniqueValue = n.getAttributes().getNamedItem(uniqueIdentifier).toString().split(delims);
                if(uniqueValue[1].equalsIgnoreCase(splitLookupName[0])){
                    String parent = n.getParentNode().getAttributes().getNamedItem(uniqueIdentifier).toString().split(delims)[1];
                    if(parent.equalsIgnoreCase(splitLookupName[1])){
                        
                        description = doc.getElementsByTagName("description").item(i).getTextContent();
                        
                    }
                }
            }
        
        
        return description;
    }
    
    private void toggleItemNameVisable(){
        if(selectedNameLabel.isVisible()){
            selectedNameLabel.setVisible(false);
            selectedNameTextArea.setVisible(false);
        }else{
            selectedNameLabel.setVisible(true);
            selectedNameTextArea.setVisible(true);
        }
    }
    
    private boolean validateNameChange(String newValue, String oldValue, TreeItem ti){
        System.out.println("Chacking name validity");
        int count = 0;
        if(!newValue.equals(oldValue)){
            TreeItem parent = ti.getParent();
            ObservableList children = parent.getChildren();
            for (int i = 0; i<children.size(); i++) {
                String tempString = "TreeItem [ value: "+newValue+" ]";
                if(children.get(i).toString().equalsIgnoreCase(tempString)){
                    count++;
                }
            }
            if(count==0){
                return true;
            }else{
                displayFeedback("Sorry but there is already an entry under that is named: "+newValue+"\n");
                return false;
            }
        }else{
            displayFeedback("Nothing to update \n");
            return false;
        }
    }
    private boolean validateNode(String nodeType){
        Boolean good = false;
        for (int i = 0; i<excludedNodeTypes.length; i++) {
            if(nodeType.equalsIgnoreCase(excludedNodeTypes[i])){
                break;
            }else{
                if(i == excludedNodeTypes.length - 1){
                    return true;
                }
            }
        }
        return good;
    }
    
    private void walkNode(Node theNode, TreeItem rootItem) {
    NodeList children = theNode.getChildNodes();
    TreeItem item = null;
    String value = "NOT INITILIZED";
    for (int i = 0; i < children.getLength(); i++) {
        Node aNode = children.item(i);
        if (aNode.hasChildNodes()){
            if(validateNode(aNode.getNodeName())){
                value = aNode.getAttributes().getNamedItem(uniqueIdentifier).toString();
                String delims = "[\"]+";
                String[] splitString = value.split(delims);
                value = splitString[1];

                item = new TreeItem(value);
                rootItem.getChildren().add(item);
                rootItem.setExpanded(true);

                walkNode(aNode, item);
            }
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