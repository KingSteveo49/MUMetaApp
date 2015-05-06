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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;
import utilities.Project;
import utilities.Tools;
import utilities.Export;

/**
 * The ModelManager handles all of the data that Meta-App needs to store.
 * It does so, independent of the GUI. You interface with it through
 * Projects.
 * 
 * @author sdmiller2015
 */

public class ModelManager {

    /**
     * [Deprecated]
     */
    private static final ModelManager instance = new ModelManager();
     
    /**
     * [Deprecated]
     * private constructor to avoid client applications to use constructor
     */
    ModelManager(){}
 
    /**
     * [Deprecated]
     * Gets the instance of the ModelManager, so that duplicates don't
     * pop up.
     * 
     * @return 
     */
    public static ModelManager getInstance(){
        return instance;
    }
    
    /**
     * This handles all incoming projects or passes them to a function
     * that can handle them.
     * 
     * @param p Incoming project for the model to handle
     */
    public void manageProject(Project p)
    {
        String path = p.getPath(); //i.getPath()
        String content = p.getContent();
        String data;
        switch(p.getAction())
        {
            //Expecting Path and Content in Data
            case "update":
            //Expecting Path in Data
            case "delete":
                
            //Exports the Project into an actual App
            case "export":
                System.out.println("Exporting!!");
                Export.inject(p.getDoc());
                break;
                
            //Expecting Path and Content in Data
            case "setText":
                writeToFile(path,content);
                break;

            // At path, writes serializable content    
            case "setSerial":
                writeToFile(path,content);
                break;

            case "setXML":
                Tools.saveDoc(p.getDoc(), path);
                factory.getController().manageProject(new Project(null, "success", "saveFeedback"));
                break;

            // Reads string at path
            case "getText":
                data = readFromFile(path);
                factory.getController().manageProject(new Project(path,data,"returningProject"));
                break;
            case "getXML":
                System.out.println("Model retrieving file from: "+path);
                factory.getController().manageProject(new Project(path,null,"returningProject", Tools.getDoc(path)));
                break;
                
            case "getDefaultXML":
                factory.getController().manageProject(new Project(null,null,"returningProject", Tools.getDoc("src\\fxmlexample\\Default.xml")));
                break;

            case "getSerial":
                readSerializable(path);
                //Controller.getInstance().manageProject(new Project(path,data,"returningProject"));

            default:
                break;
        }
        return;
    }

    /**
     * [Deprecated]
     * Compares ghostfile to filepath, looking for dirty bits
     * @param filepath The original file
     * @param ghostfile The changed file
     * @return 
     */
    public String dirtyCheck(String filepath, String ghostfile)
    {
            return "checked for dirty";
    }

    /**
     * Reads string from file
     * 
     * @param path Path to file
     * @return 
     */
    private String readFromFile(String path)
    {
        System.out.println(path);
        File filename = new File(path);
        Scanner scan = null;
        String contents = "";
        try 
        {
            scan = new Scanner(filename);
            while(scan.hasNextLine())
            {
                contents += scan.nextLine()+"\n";
//                    System.out.println(scan.nextLine());
            }
        }
        catch(FileNotFoundException e)
        {
            contents = "ERROR: Your path was crap...";
            System.out.println("ERROR: Trying to read text from file");
        }
        return contents;
    }
        
    /**
     * Read a Serializable from file
     *  
     * @param path Path to serialized filed
     * @return 
     */
    private Object readSerializable(String path)
    {
        try
        {
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream inputStream = new ObjectInputStream(file);
            Project p = (Project) inputStream.readObject();
            return p;
        }
        catch(Exception e)
        {
            System.out.println("Error: Trying to read Serializable in "+path);
        }
        return null;
    }

    /**
     * Writes a Serializable to a file
     * 
     * @param path Path to where the Serializable should go
     * @param info A Serializable Object to write to path
     */
    private void writeToFile(String path, Serializable info)
    {
        try (
          OutputStream file = new FileOutputStream(path);
          OutputStream buffer = new BufferedOutputStream(file);
          ObjectOutput output = new ObjectOutputStream(buffer);
        ){
          output.writeObject(info);
        }  
        catch(IOException ex){
          System.out.println("Error: Writing serializable to "+path);
        }
    }

    /**
     * Write string to file
     * 
     * @param path Path to resulting file
     * @param info String to write
     */
    private void writeToFile(String path, String info)
    {
        File filename = new File(path);
        try 
        {
            PrintWriter outputStream = new PrintWriter(filename);
            outputStream.println(info);
            outputStream.flush();
            outputStream.close();
            factory.getController().manageProject(new Project(null,"success","saveFeedback"));
        }
        catch (FileNotFoundException e) 
        {
            factory.getController().manageProject(new Project(null,"fail","saveFeedback"));
        }

    }
        
    /**
     * Makes a new app structure at desired location
     * 
     * @param path Path to resulting folder structure
     */
    private void createNewApp(String title)
    {
        new File("../Projects/"+title).mkdir();
    }
}