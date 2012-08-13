/*******************************************************************************************
*Source File Name: Item.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: This class is for handling items 
********************************************************************************************/

package database;

/*
 * Class Name:		Item
 * Description:		This class will be used to create Item objects
 * 					which will then be stored within the channelList
 */
public class Item 
{
	private String title = null;
	private String link = null;
	private String pubDate = null;         //TODO: discuss formatting of dates
	private String description = null; 
	private String category = null; 
	private String author = null;
	private String guid = null; 
	private String comments = null; 
	
	
	/*
	 * Method Name: DisplayAttributes
	 * Description: Returns formated output for console UI dependent on Item Attributes
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
		if (!pubDate.isEmpty())
		{
			Output += "\nPublish Date: " + pubDate;
		}
		if (!author.isEmpty())
		{
			Output += "\nAuthor: " + author;
		}
		if (!guid.isEmpty())
		{
			Output += "\nGuid: " + guid;
		}
		if (!comments.isEmpty())
		{
			Output += "\nComments: " + comments;
		}
		if (!category.isEmpty())
		{
			Output += "\nCategory: " + category;
		}
		
		return Output;
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
	 * @return the pubDate
	 */
	public String getPubDate() {
		return pubDate;
	}
	/**
	 * @param pubDate the pubDate to set
	 */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	

}
