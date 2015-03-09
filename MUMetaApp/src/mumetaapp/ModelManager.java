/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mumetaapp;

import interfaces.InfoType;
import interfaces.events.FeedbackEvent;
import interfaces.events.OpenEvent;
import interfaces.infotypes.ProjectInfoType;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdmiller2015
 */

public class ModelManager 
{
    public Controller cr;
    
        public InfoType manageInfoType(InfoType i)
        {
            switch(i.getKind())
            {
                case "project":
                    return manageProjectInfoType( (ProjectInfoType) i );   
                default:
                    return null;
            }
        }
        
        public InfoType manageProjectInfoType(ProjectInfoType i)
        {
            String path = i.getData(); //i.getPath()
            String content = i.getData();
            Object data = "";
            switch(i.getType())
            {
                //Expecting Path and Content in Data
                case "update":
                //Expecting Path in Data
                case "delete":
                //Expecting Path and Content in Data
                case "set":
                    data = writeToFile(path,content.toString());
                    break;
                //Expecting Path in Data
                case "get":
                    data = readFromFile(path); //Object data = //When Serializable happens
                    cr.manageEvent(new FeedbackEvent("","",data));
                    break;
                default:
                    break;
            }
            
            return new ProjectInfoType("Return results","results",data, "set");
        }
        

        
        // Compares ghost file to filepath, looking for dirty bits
        public String dirtyCheck(String filepath, String ghostfile)
        {
                return "checked for dirty";
        }
        
        private Object readFromFile(String path)
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
                    contents += scan.nextLine();
//                    System.out.println(scan.nextLine());
                }
            }
            catch(FileNotFoundException e)
            {
                contents = "ERROR: Your path was crap...";
//                e.printStackTrace();
            }
            return contents;
        }
        
        // Read a Serializable from file
        private Object readSerializable(String path)
        {
            return null;
        }
        
        // Writes a Serializable to a file
        private Object writeToFile(String path, Serializable info)
        {
            try (
              OutputStream file = new FileOutputStream(path);
              OutputStream buffer = new BufferedOutputStream(file);
              ObjectOutput output = new ObjectOutputStream(buffer);
            ){
              output.writeObject(info);
            }  
            catch(IOException ex){
              
            }
            return null;
        }
        
        private Object writeToFile(String path, String info)
        {
            File filename = new File(path);
            try 
            {
                PrintWriter outputStream = new PrintWriter(filename);
                outputStream.println(info);
                outputStream.flush();
                outputStream.close();
            }
            catch (FileNotFoundException e) 
            {
                return e.getStackTrace();
            }
            
            return "File Successfully Written To";
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