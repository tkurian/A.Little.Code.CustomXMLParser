/*******************************************************************************************
*Source File Name: RssFeedListModel.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: 
********************************************************************************************/

package windows;



import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;

import utilities.RssFeed;
import utilities.RssFeedManager;



/*
 * Class Name:		RssFeedListModel
 * Description:		This class handles linking data between the RSS feed and the feedManager.
 */
public class RssFeedListModel extends DefaultListModel {
	private static final long serialVersionUID = 1L;

	RssFeedManager feedManager;
	
	
	/*
	 * Method Name: ItemListListener
	 * Description: This function constructs a new feedManager loading the data
	 * 				from the config file.
	 * Parameters: WindowsGUI listenerWindow.
	 * Return:
	 */
	public RssFeedListModel ( ) {
		feedManager = new RssFeedManager ( );
	}
	
	/*
	 * Method Name: ItemListListener
	 * Description: This function determines the number of RSS feeds.
	 * Parameters: WindowsGUI listenerWindow.
	 * Return:
	 */
    public int getSize ( ) { 
    	return feedManager.GetSize ( );
    }
    
    /*
	 * Method Name: ItemListListener
	 * Description: This function will get a RSS feed element value.
	 * Parameters: WindowsGUI listenerWindow.
	 * Return:
	 */
    public Object getElementAt ( int index ) { 
    	return feedManager.GetIndex ( index );
    }
    
    /*
	 * Method Name: ItemListListener
	 * Description: This function is used to add an RSS feed once the JList has
	 * 				been displayed
	 * Parameters: WindowsGUI listenerWindow.
	 * Return:
	 */
    public synchronized void addFeed ( RssFeed element ) {
    	// Ensure that the add succeeded
    	if ( feedManager.addRssFeed ( ( RssFeed ) element ) == 1 ) {
    		// Update the config file
    		feedManager.UpdateFile ( );
    	}
    	
    	// Fire the refresh interval
    	fireIntervalAdded ( ListDataEvent.INTERVAL_ADDED, getSize ( ), getSize ( ) );
    }
    
    /*
	 * Method Name: ItemListListener
	 * Description: This function is used to remove an RSS feed once the JList has been
	 * 				displayed on the screen.
	 * Parameters: WindowsGUI listenerWindow.
	 * Return:
	 */
    public synchronized void removeFeed ( int index ) {
    	// Ensure that the remove succeeded
    	if ( feedManager.removeRssFeed ( index ) == 1 ) {
    		// Update the config file
    		feedManager.UpdateFile ( );
    	}
    	
    	// Fire the refresh interval
    	fireIntervalAdded ( ListDataEvent.INTERVAL_REMOVED, getSize ( ), getSize ( ) );
    }
};
