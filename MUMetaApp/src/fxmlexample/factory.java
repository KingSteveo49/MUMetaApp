/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

/**
 *
 * @author sdmiller2015
 */
public class factory {
    private static GUIController guicontroller = null;
    private static Controller controller =  null;
    private static ModelManager modelmanager = null;
    
    public static void setGUIController(GUIController g){
        guicontroller = g;
    }
    
    public static GUIController getGUI() {
      if(guicontroller == null) {
         guicontroller = new GUIController();
      }
      return guicontroller;
   }
    public static Controller getController() {
      if(controller == null) {
         controller = new Controller();
      }
      return controller;
   }
    public static ModelManager getModelManager() {
      if(modelmanager == null) {
         modelmanager = new ModelManager();
      }
      return modelmanager;
   }
}
