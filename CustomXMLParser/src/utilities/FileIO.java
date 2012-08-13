/*******************************************************************************************
*Source File Name: FileIO.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: This class manages parsing the FileIO of the RSS feed/XML
********************************************************************************************/

package utilities;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;



/*
 * Class Name:		XMLParser
 * Description:		This class will handle reading in the RSS feed/XML file
 * 					It will return back the contents of the xml file within a string.
 * 					
 */
public class FileIO 
{
	private String line = "";
	private String feed= null;
	private BufferedReader bufferedReader= null; 
	
	/**
	 * @return the BufferedReader
	 */
	public BufferedReader readRSSFeed(String url) {
		BufferedReader buffReader = null;
		
		try {
			buffReader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
		} catch (MalformedURLException e) {
		    System.out.println ( "Malformed URL" );
		} catch (IOException e) {
			System.out.println ( "IO Exception" );
		}

		return buffReader;
	}
	
	/*
	 * Method Name: main
	 * Description: loads an xml file into a String for parsing purposes. 
	 * Parameters: String filepath.
	 * Return: String; contains the file contents.
	 */
	public String loadRSSFeed(String filepath)
	{
		bufferedReader = readRSSFeed(filepath);
		
		if ( bufferedReader != null ) {
		
			while ( line != null ) 
			{
				try 
				{
					line = bufferedReader.readLine();
					if ( line != null ) {
						feed += line; 
					}
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		}
			
		return feed;
	}
}
