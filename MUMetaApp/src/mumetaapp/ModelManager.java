/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mumetaapp;

import interfaces.InfoType;
import interfaces.infotypes.ProjectInfoType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdmiller2015
 */

public class ModelManager 
{
	 
	
	// Call this when you need to set information in the
    // model, including the root dir, info type and
	// the new info and it will return with an affirmative
	public static String setInfo(String root, String infoType, String neoInfo)
	{
		return "setInfo works!";
	}
	
	// Call this when you need to delete information in the
    // model, including the root dir and info type 
    // it will return with an affirmative
	public static String deleteInfo(String root, String infoType)
	{
		return "deleteInfo works!";
	}
        
    // Compares ghost info to the info type in the root, looking for dirty bits
    public static String dirtyCheck(String root, String infoType, String ghostInfo)
    {
            return "checked for dirty";
    }
        
    private static String readFromFile(String path)
    {
        File filename = new File(path);
        Scanner scan = null;
        String contents = "";
        try 
        {
            scan = new Scanner(filename);
            while(scan.hasNextLine())
            {
                contents += scan.nextLine();
                // System.out.println(scan.nextLine());
            }
        }
        catch(FileNotFoundException e)
        {
            contents = "ERROR: Your path was crap...";
            e.printStackTrace();
        }
        return contents;
    }
    
    private static boolean writeToFile(String path, String info)
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
            e.printStackTrace();
        }
        
        return true;
    }

    private static boolean createNewProjectStructure(String path)
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
