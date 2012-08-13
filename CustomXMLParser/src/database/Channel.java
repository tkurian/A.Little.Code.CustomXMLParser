/*******************************************************************************************
*Source File Name: Channel.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: This class is for handling the channelList
********************************************************************************************/

package database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/*
 * Class Name:		ChannelHandler
 * Description:		This class will 
 * 					It displays TODO: finish this!
 * 					
 */
public class Channel
{
	private final String Description = "DESCRIPTION";
	private final String Title = "TITLE";
	private final String Link = "LINK";
	private final String PubDate = "PUBDATE";

	private String title = null;
	private String link = null;
	private String description = null;
	private String language = null;
	private String lastBuildDate = null;
	private String copyright = null;
	private String docs = null;
	private String category = null; 
	private String rating = null; 
	
	/*
	 * Method Name: DisplayAttributes
	 * Description: Returns formated output for console UI dependent on Channel Attributes
	 * Parameters: no parameters.
	 * Return: Formated Attribute Output for console
	 */
	public String DisplayAttributes()
	{
		String Output = "";
		if (!title.isEmpty())
		{
			Output += "\nTitle: " + title;
		}
		if (!link.isEmpty())
		{
			Output += "\nLink: " + link;
		}
		if (!description.isEmpty())
		{
			Output += "\nDescription: " + description;
		}
		if (!copyright.isEmpty())
		{
			Output += "\nCopyright: " + copyright;
		}
		if (!language.isEmpty())
		{
			Output += "\nLanguage: " + language;
		}
		if (!lastBuildDate.isEmpty())
		{
			Output += "\nLast Build Date: " + lastBuildDate;
		}
		if (!docs.isEmpty())
		{
			Output += "\nDocs: " + docs;
		}
		if (!category.isEmpty())
		{
			Output += "\nCategory: " + category;
		}
		if (!rating.isEmpty())
		{
			Output += "\nRating: " + rating;
		}
		
		return Output;
	}
	
	
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	Iterator<Item> Traverseitr = null;
	
	private List<Item> channelList = new ArrayList<Item>(); 
	private List<Image>imageChannelList = new ArrayList<Image>(); 
	

	public String getImageUrl ( ) {
		String imageUrl = "";
		
		if ( imageChannelList.size ( ) > 0 ) {
			imageUrl = imageChannelList.get ( 0 ).getUrl ( );
		}
		
		return imageUrl;
	}
	
	
	public void InitializeIter()
	{
		Traverseitr = channelList.iterator();
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param decription the description to set
	 */
	public void setDecription(String description) {
		this.description = description;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the lastBuildDate
	 */
	public String getLastBuildDate() {
		return lastBuildDate;
	}

	/**
	 * @param lastBuildDate the lastBuildDate to set
	 */
	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	/**
	 * @return the copyright
	 */
	public String getCopyright() {
		return copyright;
	}

	/**
	 * @param copyright the copyright to set
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	/**
	 * @return the docs
	 */
	public String getDocs() {
		return docs;
	}

	/**
	 * @param Set the docs to docs
	 */
	public void setDocs(String docs) {
		this.docs = docs;
	}

	/**
	 * @return the channelList
	 */
	public List<Item> getChannelList() {
		return channelList;
	}
	
	/*
	 * Method Name: addIaddItemToChannelmageToChannel
	 * Description: Adds an item to a channel.
	 * Parameters: Item item.
	 * Return: N/A
	 */
	public void addItemToChannel(Item item)
	{
		channelList.add(item); 
	}
	
	/*
	 * Method Name: addImageToChannel
	 * Description: Adds an image to a channel.
	 * Parameters: Image image.
	 * Return: N/A
	 */
	public void addImageToChannel(Image image)
	{
		imageChannelList.add(image); 
	}
	
	/*
	 * Method Name: GetItemByAttribute
	 * Description: Gets an item based on the attributes
	 * Parameters: String AttributeName, String AttributeValue.
	 * Return: Item
	 */
	public Item GetItemByAttribute(String AttributeName, String AttributeValue)
	{
		Item foundItem = null;
		
		Iterator<Item> itr = channelList.iterator();
		
		if (itr != null)
		{
			while (itr.hasNext())
			{
				if (AttributeName == Description &&
					itr.next().getDescription().equals(AttributeValue))
				{
					foundItem = itr.next();
					break;
				}
				else if (AttributeName == Link &&
						itr.next().getLink().equals(AttributeValue))
				{
					foundItem = itr.next();
					break;
				}
				else if (AttributeName == PubDate &&
						itr.next().getPubDate().equals(AttributeValue))
				{
					foundItem = itr.next();
					break;
				}
				else if (AttributeName == Title &&
						itr.next().getTitle().equals(AttributeValue))
				{
					foundItem = itr.next();
					break;
				}
				
				itr.next();
			}
		}
		return foundItem;	
	}
	
	/*
	 * Method Name: getNumberOfItems
	 * Description: gets the number of items within the list. 
	 * Parameters: No parameters.
	 * Return: int indicating the number of items within the channelList. 
	 */
	public int getNumberOfItems ( ) 
	{
		return channelList.size ( );
	}
	
	/*
	 * Method Name: getItem
	 * Description: gets an item based int the index which it is passed. 
	 * Parameters: int index.
	 * Return: item which was found at the index. 
	 */
	public Item getItem ( int index ) 
	{
		return channelList.get ( index );
	}
	
}
