/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

/**
 * This class takes a string of xml, call the injection, then places the xml in 
 * the victim file proceeding the first occurrence of the dead-give-away string.
 * 
 * @author bmbolen2017
 */
public class Export 
{
	static final String deadGiveAway = "String xml = \"";
	static final String absAppsPath = "C:\\Users\\bmbolen2017\\AndroidStudioProjects\\"; //System.getProperty("user.dir");
        static final String relativeTargetPath = "MyApplication\\app\\src\\main\\java\\com\\example\\bmbolen2017\\myapplication\\";
        static final String targetFile = "XMLParser.java";
        static final String victimFile = absAppsPath+relativeTargetPath+targetFile;
        static final String encodeWith = "UTF-8";
        
        /**
         * This takes the new innards of the victim and injects
         * them back into the victim
         * 
         * @param injection Injection is the XML for the app
         */
	public static void inject(String injection)
	{
      	String newInnards = copy(injection);
      	if(newInnards!=null)
      	{
			try {
                            Writer out = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream(victimFile), encodeWith));
			    out.write(newInnards);
			    out.close();
			} 
			catch(Exception e){ }
		}
	}
        
        /**
         * Creates App Directory 'title' and takes the 'injection' 
         * XML and splices it into the app
         * 
         * @param injection Injection is the XML for the app
         * @param title Title of the app dir
         */
        public static void inject(String injection, String title)
        {
            mkDir(title);
            inject(injection);
        }

        /**
         * This rips the original innards out of the victim file 
         * (quite forcibly) and returns the bloody mess as a String
         * 
         * @return the innards of victim
         */
	private static String dissect()
	{
            File filename = new File(victimFile);
            Scanner scan = null;
            String bloodyMess = "";
            try 
            {
                scan = new Scanner(filename);
                while(scan.hasNextLine())
                {
                    bloodyMess += scan.nextLine()+"\n";
                }
            }
            catch(FileNotFoundException e)
            {
                bloodyMess = "ERROR: Your path was crap...";
                System.out.println("ERROR: Trying to read text from file");
            }
            return bloodyMess;
	}

        /**
         * This creates the new innards for the victim by finding the 
         * dead-give-away inside the bloody mess. It cuts the bloody mess
         * in half at that point, places the injection in the middle.
         * 
         * @param injection Injection is the XML for the app
         * @return the new innards to be replaced in the victim file
         */
	private static String copy(String injection)
	{
		String newInnards = dissect();
		int xmlIndexMarker = newInnards.indexOf(deadGiveAway);
		if(xmlIndexMarker!=-1)
		{ //We know where we are going to inject out xml!!
			int xmlIndex = xmlIndexMarker + deadGiveAway.length();
			newInnards = newInnards.substring(0,xmlIndex)+injection+newInnards.substring(xmlIndex);
			System.out.println(newInnards);
			return newInnards;

		}
		else
		{
			System.out.println("Your marker was crap");
		}
		return null;
	}
        
        /**
         * Copies the default app directory into a new
         * directory 'title'
         * 
         * @param title Title of the new directory
         */
        private static void mkDir(String title){
            new File(absAppsPath+"\\"+title).mkdir();
        }
}