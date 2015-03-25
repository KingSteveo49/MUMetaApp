/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mumetaapp;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.Project;

/**
 *
 * @author sdmiller2015
 */

public class ModelManager {

	private static final ModelManager instance = new ModelManager();
     
    //private constructor to avoid client applications to use constructor
    private ModelManager(){}
 
    public static ModelManager getInstance(){
        return instance;
    }
    
//    public Controller cr = Controller.getInstance();
    
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
                //Expecting Path and Content in Data
                case "setText":
                    writeToFile(path,content);
                    break;
                    
                // At path, writes serializable content    
                case "setSerial":
                    writeToFile(path,content);
                    break;
                    
                // Reads string at path
                case "getText":
                    data = readFromFile(path);
                    Controller.getInstance().manageProject(new Project(path,data,"returningProject"));
                    break;
                    
                case "getSerial":
                    readSerializable(path);
                    //Controller.getInstance().manageProject(new Project(path,data,"returningProject"));
                    
                default:
                    break;
            }
            return;
        }
           
        // Compares ghost file to filepath, looking for dirty bits
        public String dirtyCheck(String filepath, String ghostfile)
        {
                return "checked for dirty";
        }
        
        // Reads string from file
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
        
        // Read a Serializable from file
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
        
        // Writes a Serializable to a file
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
        
        private void writeToFile(String path, String info)
        {
            File filename = new File(path);
            try 
            {
                PrintWriter outputStream = new PrintWriter(filename);
                outputStream.println(info);
                outputStream.flush();
                outputStream.close();
                Controller.getInstance().manageProject(new Project(null,"success","saveFeedback"));
            }
            catch (FileNotFoundException e) 
            {
                Controller.getInstance().manageProject(new Project(null,"fail","saveFeedback"));
            }
            
        }
        
        private boolean createNewProjectStructure(String path)
        {
        path += "/root";
        String root = path;
        String src = path+"/src";
        String config = path+"/config";

        new File(root).mkdirs();
        new File(src).mkdirs();
        new File(config).mkdirs();

        writeToFile(root+"/README.txt","This is Meta-App! Use it as you wish...");
        writeToFile(config+"/design.txt",""
        +"{\n"
        +"    Background: Black;\n"
        +"    Font: Comic-Sans;\n"
        +"    FontSize: 12;\n"
        +"}\n");
        return true;
    }
}