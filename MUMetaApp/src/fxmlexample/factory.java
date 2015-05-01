/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

/**
 * Provides Singletons for everyone to play with
 * 
 * @author sdmiller2015
 */
public class factory {
    private static GUIController guicontroller = null;
    private static Controller controller =  null;
    private static ModelManager modelmanager = null;
    
    /**
     * Sets the Singleton for the GUI
     * 
     * @param g GUI Controller
     */
    public static void setGUIController(GUIController g){
        guicontroller = g;
    }
    
    /**
     * Gets the Singleton for the GUI
     * 
     * @return 
     */
    public static GUIController getGUI() {
      if(guicontroller == null) {
         guicontroller = new GUIController();
      }
      return guicontroller;
    }
    
    /**
     * Gets the Singleton for the Controller
     * 
     * @return 
     */
    public static Controller getController() {
      if(controller == null) {
         controller = new Controller();
      }
      return controller;
    }
    
    /**
     * Gets the Singleton for the ModelManager
     * 
     * @return 
     */
    public static ModelManager getModelManager() {
      if(modelmanager == null) {
         modelmanager = new ModelManager();
      }
      return modelmanager;
   }
}
