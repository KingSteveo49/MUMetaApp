/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import mumetaapp.*;

/**
 *
 * @author bmbolen2017
 */
public class Factory {
    private static Controller cr;
    private static GUI gui;
    private static ModelManager mng;

    public static Controller getController() {
        if(cr==null)
        {
            cr = new Controller();
        }
        return cr;
    }

    public static GUI getGui() {
        if(gui==null)
        {
            gui = new GUI();
        }
        return gui;
    }

    public static ModelManager getModelManager() {
        if(mng==null)
        {
            mng = new ModelManager();
        }
        return mng;
    }
    
}
