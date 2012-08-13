/*******************************************************************************************
*Source File Name: RssFeed.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: 
********************************************************************************************/

package utilities;

public class RssFeed {
	private String feedName;
	private String feedUrl;
	private String iconUrl;
	
	/*
	 * Method Name: RssFeed
	 * Description: loads an xml file into a String for parsing purposes. 
	 * Parameters: String filepath.
	 * Return: String; contains the file contents.
	 */
	public RssFeed ( String name, String url, String icon ) {
		feedName = name;
		feedUrl = url;
		iconUrl = icon;
	}
	
	/*
	 * Method Name: getName
	 * Description: returns teh name of the feed. 
	 * Parameters: no parameters.
	 * Return: String; contains the feed name.
	 */
	public String getName ( ) {
		return feedName;
	}
	
	/*
	 * Method Name: getUrl
	 * Description: gets the url of the feed. 
	 * Parameters: no parameters.
	 * Return: String; contains the feed url.
	 */
	public String getUrl ( ) {
		return feedUrl;
	}
	
	/*
	 * Method Name: getIcon
	 * Description: gets the icon url of the feed. 
	 * Parameters: no parameters.
	 * Return: String; contains the feed icon url.
	 */
	public String getIcon ( ) {
		return iconUrl;
	}
	
	/*
	 * Method Name: toString
	 * Description: no parameters. 
	 * Parameters: no parameters.
	 * Return: String.
	 */
	@Override
	public String toString ( ) {
		return feedName;
	}
}
