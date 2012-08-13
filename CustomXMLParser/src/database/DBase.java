/*******************************************************************************************
*Source File Name: DBase.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: This class is for handling the databaseList
********************************************************************************************/

package database;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utilities.FileIO;
import utilities.XMLParser;
import utilities.XMLValidation;



/*
 * Class Name:		DatabaseHandler
 * Description:		This class will 
 * 					It displays TODO: finish this!
 * 					
 */
public class DBase 
{
	private final String Title = "TITLE"; 
	private final String Link = "LINK"; 
	private final String Description = "DESCRIPTION"; 
	private final String Language = "LANGUAGE"; 
	private final String LastBuildDate = "LASTBUILDDATE"; 
	private final String Copyright = "COPYRIGHT"; 
	private final String Docs = "DOCS"; 
	
	public String dBaseImageURL = null; 
	
	Iterator<Channel> Traverseitr = null;
	private List<Channel> databaseList = new ArrayList<Channel>(); 
	
	
	/*
	 * Method Name: fillRssDbase
	 * Description: used to populate the database with channels, items and images. 
	 * Parameters: String rssUrl.
	 * Return: DBase Object. 
	 */
	public static DBase fillRssDbase ( String rssUrl ) 
	{
		// Create a new database for storing rss data
		DBase rssDBase = new DBase ( );
		
		// Create a new FilIO and Validation class
		FileIO fo = new FileIO(); 
		XMLValidation xValidation = new XMLValidation ( );
		
		// Create a valid boolean and a string to hold the RSS file
		String file = fo.loadRSSFeed(rssUrl); 
		
		if ( file != null ) {
			// Check if the file is valid and parse if it is
			if(xValidation.ValidateXML(file)) {
				XMLParser.parseXML(file, rssDBase); 
			}
		}
		else {
			rssDBase = null;
		}
		
		// Return the newly loaded database
		return rssDBase;
	}
	
	
	
	/**
	* @Initialize Iter()
	* @Description Required to initialise traverse function.
	*/
	public void InitializeIter()
	{
		Traverseitr = databaseList.iterator();
	}
	
	/*
	 * Method Name: addChannelToDatabase
	 * Description: adds channels to the database. 
	 * Parameters: Channel channel.
	 * Return: N/A. 
	 */
	public void addChannelToDatabase(Channel channel)
	{
		databaseList.add(channel); 
	}
	
	/**
	 * @return Status
	 */
	@SuppressWarnings("unused")
	private int removeFromDatabase(Channel channel)
	{
		int removeStatus = 0;
		
		Iterator<Channel> itr = databaseList.iterator();
		
		if (itr != null)
		{
			removeStatus = -1;
			
			while (itr.hasNext())
			{
				if (itr.next().equals(channel))
				{
					itr.next();
					itr.remove();
					removeStatus = 1;
					break;
				}
				itr.next();
			}
		}
		
		return removeStatus;
	}
	
	/*
	 * Method Name: GetChannelByAttributes
	 * Description: gets a channel based on it's attributes. 
	 * Parameters: String attributeName, String AttributeValue. 
	 * Return: Channel channel. 
	 */
	@SuppressWarnings("unused")
	private Channel GetChannelByAttributes(String AttributeName, String AttributeValue)
	{
		Channel foundChannel = null;
		
		Iterator<Channel> itr = databaseList.iterator();
		
		if (itr != null)
		{
			while (itr.hasNext())
			{
				if (AttributeName == Description &&
					itr.next().getDescription().equals(AttributeValue))
				{
					foundChannel = itr.next();
					break;
				}
				else if (AttributeName == Link &&
						itr.next().getLink().equals(AttributeValue))
				{
					foundChannel = itr.next();
					break;
				}
				else if (AttributeName == Language &&
						itr.next().getLanguage().equals(AttributeValue))
				{
					foundChannel = itr.next();
					break;
				}
				else if (AttributeName == Title &&
						itr.next().getTitle().equals(AttributeValue))
				{
					foundChannel = itr.next();
					break;
				}
				else if (AttributeName == LastBuildDate &&
						itr.next().getLastBuildDate().equals(AttributeValue))
				{
					foundChannel = itr.next();
					break;
				}			
				else if (AttributeName == Copyright &&
						itr.next().getCopyright().equals(AttributeValue))
				{
					foundChannel = itr.next();
					break;
				}
				else if (AttributeName == Docs &&
						itr.next().getDocs().equals(AttributeValue))
				{
					foundChannel = itr.next();
					break;
				}
				
				itr.next();
			}
		}
		return foundChannel;	
	}
	
	/*
	 * Method Name: getDatabaseList
	 * Description: gets the list of channels. 
	 * Parameters: no parameters. 
	 * Return: List<Channel>. 
	 */
	public List<Channel> getDatabaseList() {
		return databaseList;
	}
	
	/*
	 * Method Name: getNumberOfChannels
	 * Description: gets the size of the database. 
	 * Parameters: no parameters. 
	 * Return: int; the size of the database. 
	 */
	public int getNumberOfChannels ( ) {
		return databaseList.size ( );
	}
	
	/*
	 * Method Name: getChannel
	 * Description: gets a specific channel based on the index given. 
	 * Parameters: int index. 
	 * Return: Channel object. 
	 */
	public Channel getChannel ( int index ) {
		return databaseList.get ( index );
	}


}
