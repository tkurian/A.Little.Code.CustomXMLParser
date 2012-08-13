/*******************************************************************************************
*Source File Name: LinuxConsole.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: This class manages the user interface for the linux version
********************************************************************************************/

package linux;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import database.Channel;
import database.DBase;
import database.Item;
import utilities.RssFeedManager;
import utilities.RssFeed;




/*
 * Class Name:		LinuxConsole
 * Description:		This class manages the linux console for the CustomXMLParser. 
 * 					It displays TODO: finish this!
 * 					
 */
public class LinuxConsole 
{
	private final String Q = "Q"; 
	private final String Quit = "QUIT"; 
	private final String U = "U";
	private final String Url = "URL";
	private final String C = "C";
	private final String Choose = "CHOOSE";
	
	private Boolean quit = false;
	private String option = null; 
	private InputStreamReader inConverter = new InputStreamReader(System.in);
	private BufferedReader userInput = new BufferedReader(inConverter);
	private RssFeedManager ConfigManager = null;
	private DBase DB = null;
	
	
	
	/**
	 * @Start LinuxConsole
	 */
	public void runLinuxConsole() 
	{
		LinuxConsole_Run();
	}
	
	
	/*
	 * Method Name: LinuxConsole_Run
	 * Description: this method handles the commands until the QUIT command is found.
	 * Parameters: no parameters.
	 * Return: no return value.
	 */
	private void LinuxConsole_Run()
	{
		
		while(!quit) //loop until the quit flag is set
		{
			System.out.print(DisplayMainMenu());
			System.out.print("Enter Command: ");
			
			GetInput();
			
			if(option.equals(Q) || option.equals(Quit)) //handle quit CustomXMLParser command
			{
				//set quit flag and display closing message
				quit = true;
				System.out.println("RSS XML Parser is shutting down. Goodbye!\n");
			}
			else if (option.equals(C) || option.equals(Choose))
			{
				int ListSize  =0;

				while ( (!option.equals("B")) || (!option.equals("BACK")) )
				{
					ConfigManager = new RssFeedManager();
					ListSize = ConfigManager.GetSize();
					
					System.out.println("\n**** List Of RSS Feeds In Config File ****");
					System.out.println("# - Enter a number of your choice");
					System.out.println("B or BACK - Go back to main menu");
					System.out.println("A or ADD - add RSS Feed to config File");
					System.out.println("R or REMOVE - remove RSS Feed from config file\n");

					for ( int i = 0 ; i < ListSize ; i++)
					{
						RssFeed TempFeed = ConfigManager.GetIndex(i);
						System.out.println(i +". " + TempFeed.getName());
					}
					System.out.print("\nEnter Command: ");
					GetInput();
					
					for (int i = 0; i < ListSize ; i++)
					{
						int intOption = 0;
						try
						{
							intOption = new Integer(option);
						}
						catch (Exception e)
						{
							intOption = -1;
						}
						
						if (intOption == i)
						{
							DB = DBase.fillRssDbase(ConfigManager.GetIndex(i).getUrl());
							if (DB != null)
							{
								DisplayUrlInfo(DB);
							}
						}
					}
					
					if (option.equals("A") || option.equals("ADD"))
					{
						AddRSSFeed();
					}
					
					if (option.equals("R") || option.equals("REMOVE"))
					{
						RemoveRSSFeed();
					}
					
					if (option.equals("B") || option.equals("BACK"))
					{
						break;
					}
					
					ConfigManager.UpdateFile();
				}
			}
			else if (option.equals(U) || option.equals(Url))
			{
				System.out.println("Enter URL of the RSS you would like to open: ");
				GetInput();
				DBase tempDB = DBase.fillRssDbase(option);
				if (tempDB != null)
				{
					DisplayUrlInfo(tempDB); 
				}
			}
			else //invalid command entered
			{
				System.out.println("Command entered is not supported.\n");
			}
		}
	}
	
	
	/*
	 * Method Name: AddRSSFeed
	 * Description: Adds an RSSfeed to the config file.
	 * Parameters: no parameters.
	 * Return: no return value.
	 */
	private void AddRSSFeed()
	{
		String URL = "";
		DBase tempD = null;
		
		System.out.println("Enter URL of the RSS you would like to Add to config File");
		
		//get command input from the user
		try
		{
			URL = userInput.readLine();
			tempD = DBase.fillRssDbase(URL);
		}
		catch(Exception e)
		{
			System.out.println("Error with console input. CustomXMLParser is shutting down.\n");
		}
		
		if (tempD != null)
		{
			Channel tempChan = tempD.getChannel(0);
			
			ConfigManager.addRssFeed(new RssFeed(tempChan.getTitle(),URL, tempChan.getLink()));
		}
	}
	
	/*
	 * Method Name: RemoveRSSFeed
	 * Description: removes an RSSfeed to the config file.
	 * Parameters: no parameters.
	 * Return: no return value.
	 */
	private void RemoveRSSFeed()
	{
		String Input = "";
		int intOption = 0;
		
		System.out.print("Number of RSS Feed you would like to remove: ");
		
		try
		{
			Input = userInput.readLine();
			intOption = new Integer(Input);
		}
		catch(Exception e)
		{
			System.out.println("Error with console input. CustomXMLParser is shutting down.\n");
			intOption = -1;
		}
		
		ConfigManager.removeRssFeed(intOption);
	}
	
	
	/*
	 * Method Name: GetInput
	 * Description: gets input from the user via the console.
	 * Parameters: no parameters.
	 * Return: no return value.
	 */
	private void DisplayUrlInfo(DBase DaBase)
	{
		int DbaseSize = DaBase.getNumberOfChannels();
		int ChannelSize = 0;
		
		for (int i = 0 ; i < DbaseSize;i++)
		{
			Channel TempChannel = DaBase.getChannel(i);
			ChannelSize = TempChannel.getNumberOfItems();
			
			System.out.println(TempChannel.DisplayAttributes());

			for(int y = 0; y < ChannelSize; )
			{
				System.out.println("\n**** ITEM DISPLAY ***");
				System.out.println("B or BACK - Back to prev menu");
				System.out.println("N or NEXT - Next Item");
				System.out.println("P or PREV - Previous Item");
				System.out.println("F or FIRST - First Item");
				System.out.println("L or LAST - Last Item");
				
				GetInput();
				if (option.equals("N") || option.equals("NEXT"))
				{
					y++;
				}
				if (option.equals("P") || option.equals("PREV"))
				{
					y--;
				}
			    if (option.equals("F") || option.equals("FIRST"))
			    {
			    	y = 0;
			    }
			    if (option.equals("L") || option.equals("LAST"))
			    {
			    	y = ChannelSize;
			    }
				if ( y < 0)
				{
					System.out.println("** Unable to go to previous, At First Item **");
					y = 0;
				}
				if (y == ChannelSize)
				{
					System.out.println("** Unable to go to next, At Last Item **");
					y = ChannelSize - 1;
				}
				
				Item TempItem = TempChannel.getItem(y);
				System.out.println(TempItem.DisplayAttributes());
				
			    if (option.equals("B") || option.equals("BACK"))
			    {
			    	option = "C";
			    	break;
			    }			
			}
		}
	}
	
	/*
	 * Method Name: GetInput
	 * Description: gets input from user and puts into class global option variable
	 * Parameters: no parameters.
	 * Return: no return value.
	 */
	private void GetInput()
	{
		//get command input from the user
		try
		{
			option = userInput.readLine();
			option = option.toUpperCase();
		}
		catch(Exception e)
		{
			System.out.println("Error with console input. CustomXMLParser is shutting down.\n");
			option = Q;
		}
	}
	
	
	/*
	 * Method Name: DisplayMainMenu
	 * Description: This method constructs the string to display the main menu in a readable fashion.
	 * Parameters: no parameters.
	 * Return: returns a string constructed of the available commands.
	 */
	private String DisplayMainMenu()
	{
		String mainMenu = null;
		
		//build the main menu, displaying the available commands
		mainMenu = "\n**** XML Parser ****\n";
		
		//TODO: finish menu
		mainMenu += "Q or QUIT - to Quit.\n";
		mainMenu += "C or CHOOSE - Choose a feed from a list of RSS feeds.\n";
		mainMenu += "U or URL - Enter your own URL to open a custom RSS feed.\n\n";
		
		return mainMenu;
	}
	
}
