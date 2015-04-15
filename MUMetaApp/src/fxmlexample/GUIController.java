package fxmlexample;
 
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.File;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
    TreeView contentTreeView;
    @FXML
    GridPane workAreaGridPane;
    
    
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
    protected void handleTestOpenAction(ActionEvent event) throws IOException, SAXException{
        displayFile();
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
        feedBack.appendText(s);
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
        //--This is test code used to initialize a fake xml document that will
        //--be used to resemble the one that would be passed in
        DOMParser parser = new DOMParser();
        parser.parse("H:\\books.xml");
        Document dom = parser.getDocument();
        //----------------------------------------------------------------------
        TreeItem rootItem = new TreeItem("Categories");
        rootItem.setExpanded(true);
        
        contentTreeView.setRoot(rootItem);
        contentTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

                @Override
                public void changed(ObservableValue<? extends TreeItem<String>> observable,TreeItem<String> old_val, TreeItem<String> new_val) {
                    TreeItem<String> selectedItem = new_val;
                    displayFeedback("Selected Text : " + selectedItem.getValue()+"\n");
                    // do what ever you want
                }

            });
        
        walkNode(dom.getDocumentElement(), contentTreeView.getRoot());
    }
    
    private void walkDOM(Document dom) {
        Element n = dom.createElement("Thinker");
        n.setAttribute("id", "unique");
        System.out.println(n.getAttribute("id"));
        
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
    
//    protected void testTreeView(){
//        final Node rootIcon = new ImageView(new Image(getClass().getResourceAsStream("root.png")));
//        final Image depIcon = new Image(getClass().getResourceAsStream("department.png"));
//        List<Employee> employees = Arrays.<Employee>asList(
//            new Employee("Ethan Williams", "Sales Department"),
//            new Employee("Emma Jones", "Sales Department"),
//            new Employee("Michael Brown", "Sales Department"),
//            new Employee("Anna Black", "Sales Department"),
//            new Employee("Rodger York", "Sales Department"),
//            new Employee("Susan Collins", "Sales Department"),
//            new Employee("Mike Graham", "IT Support"),
//            new Employee("Judy Mayer", "IT Support"),
//            new Employee("Gregory Smith", "IT Support"),
//            new Employee("Jacob Smith", "Accounts Department"),
//            new Employee("Isabella Johnson", "Accounts Department"));
//        TreeItem<String> rootNode = new TreeItem<String>("MyCompany Human Resources", rootIcon);
//
//    }
    
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
        //optionsGridPane.add(l, 0, row);
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