/*******************************************************************************************
*Source File Name: XMLParser.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: This class manages parsing the XML file/RSS feed
********************************************************************************************/

package utilities;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import database.Channel;
import database.DBase;
import database.Image;
import database.Item;





/*
 * Class Name:		XMLParser
 * Description:		This class will take in the read RSS feed/XML file and parse it appropriately  
 * 					It will populate the database with a channel and items
 * 					
 */
public class XMLParser {

	

	/************************************************************************************************
 	* 										BASE RSS PARSING
 	************************************************************************************************/
	
	
	/*
	 * Method Name: parseXML
	 * Description: parses the xml file starting with the channel.  
	 * Parameters: String rss, DBase rssDBase.
	 * Return: N/A. 
	 */
	public static void parseXML ( String rss, DBase rssDBase ) {
		Pattern pattern = Pattern.compile ( "<( *)channel(.*?)>(.*?)<( *)/channel( *)>", Pattern.CASE_INSENSITIVE );

		Matcher matcher = pattern.matcher ( rss );
		
		while ( matcher.find ( ) ) {
			rssDBase.addChannelToDatabase ( parseChannel ( matcher.group ( ) ) );
		}
	}

	
	
	
	/************************************************************************************************
	 * 										CHANNEL PARSING
	************************************************************************************************/
	
	
	/*
	 * Method Name: parseChannel
	 * Description: parses the channel tag for item and image tags. 
	 * Parameters: String channelXML.
	 * Return: Channel object. 
	 */

	private static Channel parseChannel ( String channelXML ) {
		// Load all channel data
		Channel newChannel = getChannelWithData ( channelXML.replaceAll( "<( *)item(.*?)>(.*?)<( *)/item( *)>", "" ) );
		
		// Create a pattern for grabbing all items
		Pattern itemPattern = Pattern.compile ( "<( *)item(.*?)>(.*?)<( *)/item( *)>", Pattern.CASE_INSENSITIVE );
		Pattern imagePattern = Pattern.compile("<( *)image(.*?)>(.*?)<( *)/image( *)>", Pattern.CASE_INSENSITIVE); 
		// Search through channel for all items
		Matcher itemMatcher = itemPattern.matcher ( channelXML );
		Matcher imageMatcher = imagePattern.matcher(channelXML); 
		// Loop through all item
		while ( itemMatcher.find ( ) ) {
			// Create item and add it to the channel
			newChannel.addItemToChannel ( parseItem ( itemMatcher.group ( ) ) );
		}
		
		while (imageMatcher.find()) {
			newChannel.addImageToChannel(parseImage (imageMatcher.group ( ) ) ); 
		}
		// Return the newly created channel
		return newChannel;
	}
	
	
	/*
	 * Method Name: getChannelWithData
	 * Description: gets the channel attributes. 
	 * Parameters: String channelString.
	 * Return: Channel Object. 
	 */
	private static Channel getChannelWithData ( String channelString ) {
		Channel createdChannel = new Channel ( );
		
		// Set all channel elements
		createdChannel.setTitle( findAttribute ( "title", channelString ) );
		createdChannel.setLink ( findAttribute ( "link", channelString ) );
		createdChannel.setDecription ( findAttribute ( "description", channelString ) );
		createdChannel.setLanguage ( findAttribute ( "language", channelString ) );
		createdChannel.setLastBuildDate ( findAttribute ( "lastBuildDate", channelString ) );
		createdChannel.setCopyright ( findAttribute ( "copyright", channelString ) );
		createdChannel.setDocs ( findAttribute ( "docs", channelString ) );
		createdChannel.setCategory ( findAttribute ( "category", channelString ) );
		createdChannel.setRating ( findAttribute ( "rating", channelString ) );
		
		// Return the new channel
		return createdChannel;
	}
	
	
	
	/************************************************************************************************
	 * 										ITEM PARSING
	************************************************************************************************/
	
	/*
	 * Method Name: parseItem
	 * Description: finds elements which are associated with item for parsing purposes. 
	 * Parameters: String itemString.
	 * Return: Item Object. 
	 */
	private static Item parseItem ( String itemString ) {
		Item createdItem = new Item ( );
		
		// Set all item elements
		createdItem.setTitle ( findAttribute ( "title", itemString ) );
		createdItem.setLink ( findAttribute ( "link", itemString ) );
		createdItem.setDescription ( findAttribute ( "description", itemString ) );
		createdItem.setPubDate ( findAttribute ( "pubDate", itemString ) );
		createdItem.setCategory ( findAttribute ( "category", itemString ) );
		createdItem.setAuthor ( findAttribute ( "author", itemString ) );
		createdItem.setGuid ( findAttribute ( "guid", itemString ) );
		createdItem.setComments ( findAttribute ( "comments", itemString ) );

		
		// Return created item
		return createdItem;
	}
	
	
	/************************************************************************************************
	 * 										IMAGE PARSING
	************************************************************************************************/
	
	/*
	 * Method Name: parseImage
	 * Description: finds elements associated with image for parsing purposes. 
	 * Parameters: String imageString.
	 * Return: Image Object. 
	 */
	private static Image parseImage ( String imageString ) {
		Image createdImage = new Image ( );
		
		// Set all item elements
		createdImage.setTitle ( findAttribute ( "title", imageString ) );
		createdImage.setLink ( findAttribute ( "link", imageString ) );
		createdImage.setDescription ( findAttribute ( "description", imageString ) );
		createdImage.setUrl ( findAttribute ( "url", imageString ) );
		
		
		// Return created item
		return createdImage;
	}
	
	
	/************************************************************************************************
	 * 									ATTRIBUTE PARSING
	************************************************************************************************/
	
	
	/*
	 * Method Name: findAttribute
	 * Description: locates the attributes within the specified tags. 
	 * Parameters: String attributeName, String searchString.
	 * Return: String findAttribute. 
	 */
	private static String findAttribute ( String attributeName, String searchString ) {
		String foundAttribute = "";
		String searchPattern = "<( *)" + attributeName + "(.*?)>(.*?)<( *)/" + attributeName + "( *)>";
		Pattern attributeSearcher = Pattern.compile ( searchPattern, Pattern.CASE_INSENSITIVE );
		Matcher matchedAttribute = attributeSearcher.matcher ( searchString );
		
		if ( matchedAttribute.find ( ) ) {
			foundAttribute = matchedAttribute.group ( );
			foundAttribute = foundAttribute.replaceAll ( "<( *)" + attributeName + "(.*?)>", "" );
			foundAttribute = foundAttribute.replaceAll ( "<( *)/" + attributeName + "(.*?)>", "" );
			

			// Random stupid occurrence
			foundAttribute = foundAttribute.replaceAll( "\\<\\!\\[CDATA\\[", "" );
			foundAttribute = foundAttribute.replaceAll( "\\]\\]\\>", "" );
		}
		
		return foundAttribute;
	}
	
}