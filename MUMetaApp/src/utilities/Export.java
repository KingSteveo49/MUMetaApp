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

package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
import org.w3c.dom.Document;

/**
 * This class takes a string of xml, call the injection, then places the xml in 
 * the victim file proceeding the first occurrence of the dead-give-away string.
 * 
 * @author bmbolen2017
 */
public class Export 
{
	static final String deadGiveAway = "String xml = \"";
	static final String absAppPath = "C:\\Users\\sdmiller2015\\Default App\\";
        static final String relativeTargetPath = "app\\src\\main\\java\\com\\example\\bmbolen2017\\myapplication\\";
        static final String targetFile = "XMLParser.java";
        static final String victimFile = absAppPath+relativeTargetPath+targetFile;
        static final String encodeWith = "UTF-8";
        
        /**
         * This takes the new innards of the victim and injects
         * them back into the victim
         * 
         * @param injection 
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
        
        public static void inject(Document injection)
        {
            inject(Tools.docToString(injection));
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
         * @param injection
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
			return newInnards;

		}
		else
		{
			System.out.println("Your marker was crap");
		}
		return null;
	}
}