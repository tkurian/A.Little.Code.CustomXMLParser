/*******************************************************************************************
*Source File Name: RssItemListModel.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: 
********************************************************************************************/

package windows;

import javax.swing.AbstractListModel;

import database.Channel;



/*
 * Class Name:		RssItemListModel
 * Description:		This class links channel data to a JList display
 */
public class RssItemListModel extends AbstractListModel {
	private static final long serialVersionUID = 1L;
	
	Channel listChannel;
	
	/*
	 * Method Name: createItemHtmlString
	 * Description: Creates a new RssItemListModel given a specific channel.
	 * Parameters: Item itemToDisplay.
	 * Return: 
	 */
	public RssItemListModel ( Channel newChannel ) {
		listChannel = newChannel;
	}
	
	/*
	 * Method Name: createItemHtmlString
	 * Description: This function determines the number of RSS feeds.
	 * Parameters: Item itemToDisplay.
	 * Return: 
	 */
    public int getSize ( ) { 
    	return listChannel.getNumberOfItems ( ); 
    }
    
    /*
	 * Method Name: createItemHtmlString
	 * Description: This function will get a RSS feed element value.
	 * Parameters: Item itemToDisplay.
	 * Return: 
	 */
    public Object getElementAt ( int index ) { 
    	return listChannel.getItem ( index );
    }
    
    /*
	 * Method Name: createItemHtmlString
	 * Description: Get the channel that this model is linking.
	 * Parameters: Item itemToDisplay.
	 * Return: 
	 */
    public Channel getChannel ( ) {
    	return listChannel;
    }
};
