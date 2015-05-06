/*
 * The MIT License
 *
 * Copyright 2015 sdmiller2015.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
