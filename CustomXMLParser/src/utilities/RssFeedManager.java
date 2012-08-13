/*******************************************************************************************
*Source File Name: RssFeedManager.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: This class is for handling the channelList
********************************************************************************************/

package utilities;

import java.util.ArrayList;
import java.util.List;
import utilities.RssFeed;
import java.io.*;

public class RssFeedManager 
{
	private final String ConfigFile = "configFile/config.txt";
	private final String Delimiter = "<!DELIMTER!>";
	
	private List<RssFeed> ListOfFeeds = new ArrayList<RssFeed>();
	BufferedReader FileIn = null;
	BufferedWriter FileOut = null;
	File configFile = null;
	
	
	/*
	 * Method Name: RssFeedManager
	 * Description: Constructor
	 * Parameters: No parameters.
	 * Return: N/A
	 */

	public RssFeedManager ()
	{
		
		try
		{
			configFile = new File(ConfigFile);
			
			if ( configFile != null ) {
				LoadFromFile();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Method Name: LoadFromFile
	 * Description: This method loads the config file to the list of RSS feeds
	 * Parameters: No parameters.
	 * Return: Void.
	 */
	private void LoadFromFile()
	{
		try 
		{
			FileIn = new BufferedReader(new FileReader(configFile));
		} 
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		try 
		{
			while(FileIn.ready())
			{
				String Line = null;
				try
				{
					Line = FileIn.readLine();
				}
				catch (Exception e)
				{
					e.printStackTrace();
					break;
				}
				String[] FeedInfo = Line.split(Delimiter);
				if (FeedInfo.length == 3)
				{
					ListOfFeeds.add(new RssFeed(FeedInfo[0],FeedInfo[1],FeedInfo[2]));
				}
				else if (FeedInfo.length == 2)
				{
					ListOfFeeds.add(new RssFeed(FeedInfo[0],FeedInfo[1],"NO ICON"));
				}
				else if (FeedInfo.length == 1)
				{
					ListOfFeeds.add(new RssFeed("BAD URL","NO URL","NO ICON"));
				}		
				else
				{
					ListOfFeeds.add(new RssFeed("BAD URL","NO URL","NO ICON"));
				}					
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			FileIn.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Method Name: UpdateFile
	 * Description: Destroys and creates a new config file with the current list
	 * Parameters: No parameters.
	 * Return: Returns an int indicating the status; -1 upon error; 1 on success.
	 */
	public int UpdateFile()
	{
		int updateStatus = 0;
		configFile.delete();
		configFile = new File(ConfigFile);
		try
		{
			configFile.createNewFile();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			FileOut = new BufferedWriter(new FileWriter(configFile));
			for(int i = 0; i < ListOfFeeds.size();i++)
			{
				updateStatus = WriteFeedToFile(ListOfFeeds.get(i));
				if (updateStatus == -1 || updateStatus == 0)
				{
					break;
				}
			}
			FileOut.flush();
			FileOut.close();
		}
		catch(Exception e)
		{
			e.fillInStackTrace();
		}
		
		return updateStatus;
	}
	
	/*
	 * Method Name: WriteFeedToFile
	 * Description: This method will write the rss feed to a file.
	 * Parameters: RssFeed FeedToAdd. 
	 * Return: Returns an int indicating the status of the write; -1 upon error; 1 uppon success.
	 */
	private int WriteFeedToFile(RssFeed FeedToAdd)
	{
		int writeStatus = 0;
		
		try 
		{
			FileOut.write(FeedToAdd.getName()+ Delimiter + FeedToAdd.getUrl() + Delimiter + FeedToAdd.getIcon() + "\n");
			writeStatus = 1;
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
			writeStatus = -1;
		}
		
		return writeStatus;
	}
	
	/*
	 * Method Name: addRssFeed
	 * Description: Adds an RSSfeed Object to the List of RssFeeds.
	 * Parameters: RssFeed NewFeed.
	 * Return: Returns an int indicating the status of the add; -1 upon error; 1 upon success.
	 */
	public int addRssFeed(RssFeed NewFeed)
	{
		int addStatus = 0;
		
		if (NewFeed != null)
		{
			try
			{
				ListOfFeeds.add(NewFeed);
				addStatus = 1;
			}
			catch (Exception e)
			{
				addStatus = -1;
			}
		}
		else
		{
			addStatus = -1;
		}
		return addStatus;
	}
	
	/*
	 * Method Name: removeRssFeed
	 * Description: Removes a RssFeed from the list of RssFeeds
	 * Parameters: RssFeed RemoveFeed.
	 * Return: Returns an int indicating the status of removing a feed; -1 upon error; 1 upon success.
	 */
	public int removeRssFeed(int removeIndex)
	{
		int removeStatus = 0;
		
		try
		{
			ListOfFeeds.remove(removeIndex);
			removeStatus = 1;
		}
		catch (Exception e)
		{
			removeStatus = -1;
		}
		
		return removeStatus;
	}
	
	
	
	
	
	/*
	 * Method Name: GetSize
	 * Description: This method will return the size of the list containing the feeds.
	 * Parameters: No parameters.
	 * Return: Returns an int, indicating the size of the feed list. 
	 */
	public int GetSize()
	{
		return ListOfFeeds.size();
	}
	
	/*
	 * Method Name: GetIndex
	 * Description: This method returns an rss feed based on the index it is given.
	 * Parameters: int index.
	 * Return: Returns an RssFeed.
	 */
	public RssFeed GetIndex(int index)
	{
		return ListOfFeeds.get(index);
	}
	
}