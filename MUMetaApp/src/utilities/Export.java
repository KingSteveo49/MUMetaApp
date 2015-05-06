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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * This class takes a string of xml, call the injection, then places the xml in 
 * the victim file proceeding the first occurrence of the dead-give-away string.
 * 
 * @author bmbolen2017
 */
public class Export 
{
	static final String deadGiveAway = "String xml = \"";
        static final String rootDir = "Z:\\Meta-App\\";
	static final String defaultAppDir = rootDir+"Default App\\";
        static final String projectsDir = rootDir+"Projects\\";
        static final String relativeTargetPath = "app\\src\\main\\java\\com\\example\\bmbolen2017\\myapplication\\";
        static final String targetFile = "XMLParser.java";
        static final String encodeWith = "UTF-8";
        //String victimFile = projectsDir+"Title"+relativeTargetPath+targetFile;
        
        /**
         * This takes the new innards of the victim and injects
         * them back into the victim
         * 
         * @param injection Injection is the XML for the app
         */
	private static void inject(String injection, String victumFile)
	{
      	String newInnards = copy(injection,victumFile);
      	if(newInnards!=null)
      	{
			try {
                            Writer out = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream(victumFile), encodeWith));
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
        public static void inject(Document injection)
        {
            String title = injection.getDocumentElement().getAttribute("name");
            String victumFile = projectsDir+title+"\\"+relativeTargetPath+targetFile;
            cpDefaultApp(title);
            inject(Tools.docToString(injection), victumFile);
        }

        /**
         * This rips the original innards out of the victim file 
         * (quite forcibly) and returns the bloody mess as a String
         * 
         * @return the innards of victim
         */
	private static String dissect(String victumFile)
	{
            File filename = new File(victumFile);
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
                System.out.println("ERROR: Export: dissect(): Trying to read text from file faied");
                e.printStackTrace();
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
	private static String copy(String injection, String victumFile)
	{
		String newInnards = dissect(victumFile);
		int xmlIndexMarker = newInnards.indexOf(deadGiveAway);
		if(xmlIndexMarker!=-1)
		{ //We know where we are going to inject out xml!!
			int xmlIndex = xmlIndexMarker + deadGiveAway.length();
			newInnards = newInnards.substring(0,xmlIndex)+injection+newInnards.substring(xmlIndex);
			return newInnards;

		}
		else
		{
			System.out.println("Export: DeadGiveAway didn't work...");
		}
		return null;
	}
        
        /**
         * Copies the default app directory into a new
         * directory 'title'
         * 
         * @param title Title of the new directory
         */
        private static void cpDefaultApp(String title){
            File destiny = new File(projectsDir+title);
            System.out.println(projectsDir+title);
            File source = new File(defaultAppDir);
            try
            {
                copy(source, destiny);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
  
        public static void copy( File source, File destination ) throws IOException {
          if( source.isDirectory() ) {
            copyDirectory( source, destination );
          } else {
            copyFile( source, destination );
          }
        }

        private static void copyDirectory( File source, File destination ) throws IOException {
          if( !source.isDirectory() ) {
            throw new IllegalArgumentException( "Source (" + source.getPath() + ") must be a directory." );
          }

          if( !source.exists() ) {
            throw new IllegalArgumentException( "Source directory (" + source.getPath() + ") doesn't exist." );
          }

          if( destination.exists() ) {
            throw new IllegalArgumentException( "Destination (" + destination.getPath() + ") exists." );
          }

          destination.mkdirs();
          File[] files = source.listFiles();

          for( File file : files ) {
            if( file.isDirectory() ) {
              copyDirectory( file, new File( destination, file.getName() ) );
            } else {
              copyFile( file, new File( destination, file.getName() ) );
            }
          }
        }

        private static void copyFile( File source, File destination ) throws IOException {
          FileChannel sourceChannel = new FileInputStream( source ).getChannel();
          FileChannel targetChannel = new FileOutputStream( destination ).getChannel();
          sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
          sourceChannel.close();
          targetChannel.close();
        }
      }
