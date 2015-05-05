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
import javafx.scene.control.Button;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utilities.Action;
 
public class GUIController {
    
    @FXML TextArea feedBack, selectedNameTextArea, selectedDescTextArea;
    @FXML GridPane workAreaGridPane, rootElement;
    @FXML Document currentDoc;
    @FXML Label mainTitle, selectedNameLabel, selectedDescLabel;
    @FXML TreeView contentTreeView;
    @FXML TreeItem previouslySelectedTreeItem, currentlySelectedTreeItem;
    @FXML Scene scene;
    @FXML MenuBar menuBar;
    @FXML String currentDescValue, previousDescValue;
    @FXML Button addMenuButton, addItemButton, removeSelectedbutton;
    //Include any other node types that are part of an item that should not be
    //displayed in the tree view
    final String[] excludedNodeTypes = {"description", "img"};
    final String characterToSeperateAttributes = "[\"]+";
    final String characterToSeperateUniqueLookupName = "_";
    final String characterToSeperateID = ".";
    final String uniqueIdentifier = "name";
    final String editRootElement = "rootElement";
    
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
        feedBack.appendText(s+"\n");
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
    protected void handleAddMenuAction(){
        TreeItem newMenu = new TreeItem("Menu "+ (getNumberOfChildren(contentTreeView.getRoot())+1));
        Element elementToAppend = currentDoc.createElement("menu");
        elementToAppend.setAttribute("id", "1."+(getNumberOfChildren(contentTreeView.getRoot())+1));
        elementToAppend.setAttribute("name", "Menu "+ (getNumberOfChildren(contentTreeView.getRoot())+1));
        contentTreeView.getRoot().getChildren().add(newMenu);
        currentDoc.getDocumentElement().appendChild(elementToAppend);
        
    }
    @FXML
    protected void handleAddItemAction(){
        int menuPosition = -1;
        TreeItem newItem;
        Element itemToBeAdded = currentDoc.createElement("item");
        Element imgElement = currentDoc.createElement("img");
        Element descriptionElement = currentDoc.createElement("description");
        
        itemToBeAdded.appendChild(imgElement);
        itemToBeAdded.appendChild(descriptionElement);
        
        if(determineMenuItemOrRoot(currentlySelectedTreeItem).equalsIgnoreCase("menu")){
            menuPosition = getMenuPosition(currentlySelectedTreeItem);
            newItem = new TreeItem("Item "+(getNumberOfChildren(currentlySelectedTreeItem)+1));
            itemToBeAdded.setAttribute("name", "Item "+(getNumberOfChildren(currentlySelectedTreeItem)+1));
            itemToBeAdded.setAttribute("id", "1."+ menuPosition +"."+ (getNumberOfChildren(currentlySelectedTreeItem.getParent())+1));
            currentlySelectedTreeItem.getChildren().add(newItem);
        }
        if(determineMenuItemOrRoot(currentlySelectedTreeItem).equalsIgnoreCase("item")){
            menuPosition = getMenuPosition(currentlySelectedTreeItem.getParent());
            newItem = new TreeItem("Item "+(getNumberOfChildren(currentlySelectedTreeItem.getParent())+1));
            itemToBeAdded.setAttribute("name", "Item "+(getNumberOfChildren(currentlySelectedTreeItem.getParent())+1));
            itemToBeAdded.setAttribute("id", "1."+ menuPosition +"."+ (getNumberOfChildren(currentlySelectedTreeItem.getParent())+1));
            currentlySelectedTreeItem.getParent().getChildren().add(newItem);
        }
        
        
        currentDoc.getDocumentElement().getElementsByTagName("menu").item(menuPosition).appendChild(itemToBeAdded);
        
        
    }
    @FXML
    protected void handleRemoveSelectedAction(){
        
    }
    @FXML
    private void displayFile(Action a){
       
        System.out.println("The GUI is now attempting to display the file");
        toggleAddRemoveItemButtons();
        
        currentDoc = a.getDoc();
        
        contentTreeView.setRoot(new TreeItem("My App"));
        
        contentTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable,TreeItem<String> old_val, TreeItem<String> new_val) {
                TreeItem<String> selectedItem = new_val;
                currentlySelectedTreeItem = selectedItem;
                determineMenuItemOrRoot(currentlySelectedTreeItem);
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
                        displayFeedback(lookupName);
                        currentDoc = updateDocumentName(currentDoc, lookupName, newTreeValue);
                        
                        //This is where the value in the tree should be updated
                        previouslySelectedTreeItem.setValue(newTreeValue);
                        displayFeedback("Changed: "+oldtreeValue+" to: "+ newTreeValue);
                        
                    }
                } else {
                    displayFeedback("did nothing");
                }
                if(!"".equals(selectedDescTextArea.getText())){
                    
                    if(!previousDescValue.equals(selectedDescTextArea.getText())){
                        
                        currentDoc = updateDocumentDesc(currentDoc, lookupName, selectedDescTextArea.getText());
                    }
                } else {
                    displayFeedback("did nothing");
                }
                }catch(Exception e){
                    System.out.println(e.toString());
                    
                }
                
                mainTitle.setText(selectedItem.getValue());
                
                if(!selectedNameLabel.isVisible()){
                    toggleItemNameVisable();
                }
                displayFeedback(lookupDesc);
                if(lookupDesc.split(characterToSeperateUniqueLookupName).length == 1){
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
    
    
    //Method that takes in a tree item, generally the currently selected and determines if that item i the root, a menu, or an item
    //There are three return values respectively
    //-root:if the selected tree item is the root
    //-menu:if the selected tree item is a menu in the tree view
    //-item:if the selected tree item is an item under a menu
    private String determineMenuItemOrRoot(TreeItem ti){
        String menuItemroot = "";
        if(ti.equals(contentTreeView.getRoot())){
            menuItemroot = "root";
            return menuItemroot;
        }
        if(ti.getParent().equals(contentTreeView.getRoot())){
            menuItemroot = "menu";
        }
        if(!ti.getParent().equals(contentTreeView.getRoot())){
            menuItemroot = "item";
        }
        
        return menuItemroot;
    }
    
    private Document updateDocumentName(Document doc, String lookupName, String newValue){
        
        String[] splitLookupName = lookupName.split(characterToSeperateUniqueLookupName);
        String[] uniqueValue;
        
        //There are 3 different possibilities that could happen
        //The uniqueValue could be of length 1 - In which case the IF will be triggered
        //The uniqueValue could be of length 1 AND unique value is rootElement - in which case only the root item of the document will be renamed without looking for anything else
        //The uniqueValue could be of length 2 - In which case the ESLE will be triggered
        
        if(splitLookupName.length == 1){
            if(lookupName.equalsIgnoreCase(editRootElement)){
                
            }
            NodeList possibleUpdates = doc.getElementsByTagName("menu");
            
            for(int i = 0; i < possibleUpdates.getLength(); i++){
                Node n = possibleUpdates.item(i);
                uniqueValue = n.getAttributes().getNamedItem(uniqueIdentifier).toString().split(characterToSeperateAttributes);
                if(uniqueValue[1].equalsIgnoreCase(splitLookupName[0])){
                    doc.getElementsByTagName("menu").item(i).getAttributes().getNamedItem(uniqueIdentifier).setTextContent(newValue);
                    displayFeedback(doc.getElementsByTagName("menu").item(i).getAttributes().getNamedItem(uniqueIdentifier).toString());
                    displayFeedback("Line above should be new doc value");
                }
            }
        
        }else{
            NodeList possibleUpdates = doc.getElementsByTagName("item");
            
            
            for(int i = 0; i<possibleUpdates.getLength(); i++){
                Node n = possibleUpdates.item(i);
                uniqueValue = n.getAttributes().getNamedItem(uniqueIdentifier).toString().split(characterToSeperateAttributes);
                if(uniqueValue[1].equalsIgnoreCase(splitLookupName[0])){
                    String parent = n.getParentNode().getAttributes().getNamedItem(uniqueIdentifier).toString().split(characterToSeperateAttributes)[1];
                    if(parent.equalsIgnoreCase(splitLookupName[1])){
                        doc.getElementsByTagName("item").item(i).getAttributes().getNamedItem(uniqueIdentifier).setTextContent(newValue);
                        displayFeedback(doc.getElementsByTagName("item").item(i).getAttributes().getNamedItem(uniqueIdentifier).toString());
                        displayFeedback("Line above should be new doc value");
                    }
                }
            }
            
        }
        
        
        return doc;
    }
    
    private Document updateDocumentDesc(Document doc, String lookupName, String newValue){
        
        String[] uniqueValue;
        String[] splitLookupName = lookupName.split(characterToSeperateUniqueLookupName);
        
        NodeList possibleUpdates = doc.getElementsByTagName("item");
            
            
            for(int i = 0; i<possibleUpdates.getLength(); i++){
                Node n = possibleUpdates.item(i);
                uniqueValue = n.getAttributes().getNamedItem(uniqueIdentifier).toString().split(characterToSeperateAttributes);
                if(uniqueValue[1].equalsIgnoreCase(splitLookupName[0])){
                    String parent = n.getParentNode().getAttributes().getNamedItem(uniqueIdentifier).toString().split(characterToSeperateAttributes)[1];
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
                lookupName = lookupName+characterToSeperateUniqueLookupName+parentOfLookup.getValue().toString();
                parentOfLookup = parentOfLookup.getParent();
            }
        }else{
            lookupName = editRootElement;
        }
        return lookupName;
    }
    
    
    private String getItemDescription(Document doc, String lookupName){
        displayFeedback("Getting item description");
        String description = "";
        String[] uniqueValue;
        String[] splitLookupName = lookupName.split(characterToSeperateUniqueLookupName);
        
        NodeList possibleUpdates = doc.getElementsByTagName("item");
            
            
            for(int i = 0; i<possibleUpdates.getLength(); i++){
                Node n = possibleUpdates.item(i);
                uniqueValue = n.getAttributes().getNamedItem(uniqueIdentifier).toString().split(characterToSeperateAttributes);
                if(uniqueValue[1].equalsIgnoreCase(splitLookupName[0])){
                    String parent = n.getParentNode().getAttributes().getNamedItem(uniqueIdentifier).toString().split(characterToSeperateAttributes)[1];
                    if(parent.equalsIgnoreCase(splitLookupName[1])){
                        
                        description = doc.getElementsByTagName("description").item(i).getTextContent();
                        
                    }
                }
            }
        
        
        return description;
    }
    
    private int getNumberOfChildren(TreeItem t){
        ObservableList ol = t.getChildren();
        return ol.size();
    }
    
    private int getMenuPosition(TreeItem ti){
        ObservableList ol = contentTreeView.getRoot().getChildren();
        for(int i = 0; i< ol.size(); i++){
            if(ti.equals(ol.get(i))){
                System.out.println(i);
                return i;
            }
        }
        return -1;
    }
    
    private void toggleAddRemoveItemButtons(){
        if(!addMenuButton.isVisible()){
            addMenuButton.setVisible(true);
            addItemButton.setVisible(true);
            removeSelectedbutton.setVisible(true);
        }else{
            addMenuButton.setVisible(false);
            addItemButton.setVisible(false);
            removeSelectedbutton.setVisible(false);
        }
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
        int count = 0;
        if(!newValue.equals(oldValue)){
            TreeItem parent = ti.getParent();
            ObservableList children = parent.getChildren();
            for (int i = 0; i<children.size(); i++) {
                String tempString = "TreeItem [ value: "+newValue+" ]";
                if(children.get(i).toString().equalsIgnoreCase(tempString)&&!ti.equals(children.get(i))){
                    count++;
                }
            }
            if(count==0){
                return true;
            }else{
                displayFeedback("Sorry but there is already an entry under that is named: "+newValue);
                return false;
            }
        }else{
            displayFeedback("Nothing to update");
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
                String[] splitString = value.split(characterToSeperateAttributes);
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
                displayFeedback(l.getId()+ " was pressed, adding new label");
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