/*******************************************************************************************
*Source File Name: ItemListListener.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: 
********************************************************************************************/

package windows;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import database.Item;



/*
 * Class Name:		ItemListListener
 * Description:		This class is the listener for the item list. 					
 */
public class ItemListListener implements ListSelectionListener {

	private static WindowsGUI mainWindow;
	
	/*
	 * Method Name: ItemListListener
	 * Description: This is the constructor for the ItemListListener that
	 * 				takes the main window the list is running on.
	 * Parameters: WindowsGUI listenerWindow.
	 * Return:
	 */
	public ItemListListener ( WindowsGUI listenerWindow ) {
		mainWindow = listenerWindow;
	}
	
	
	/*
	 * Method Name: valueChanged
	 * Description: This function listens for the value of the list changing.  It will load
	 * 				the newly chosen element on change.
	 * Parameters: ListSelectionEvent e.
	 * Return:
	 */
	@Override
	public void valueChanged ( ListSelectionEvent e ) {
		// Ensure the value is not changing before we get the new channel list
		if ( e.getValueIsAdjusting ( ) == false ) {
			mainWindow.createRssDisplayPanel ( ( ( Item ) ( ( JList ) e.getSource ( ) ).getSelectedValue ( ) ) );
		}
	}
	
	
	/*
	 * Method Name: MouseListener
	 * Description: This function is called when the item list is clicked.  It will launch the
	 * 				browser when an item is double clicked.
	 * Parameters: no parameters.
	 * Return:
	 */
	public static MouseListener mouseListener = new MouseAdapter() {
	    public void mouseClicked ( MouseEvent e ) {
	        if ( e.getClickCount ( ) == 2 ) {
	            int index = ( ( JList ) e.getSource ( ) ).locationToIndex(e.getPoint());
	            launchBrowser ( ( ( Item ) ( ( JList ) e.getSource ( ) ).getModel().getElementAt ( index ) ).getLink ( ) );
	         }
	    }
	};
	
	/*
	 * Method Name: launchBrowser
	 * Description: This function will launch user's default browser to a
	 * 				given url.  It will ensure that the user has Desktop support
	 * 				functionality before attempting to use it.
	 * Parameters: String url.
	 * Return:
	 */
	public static void launchBrowser ( String url ) {
		// Check if the desktop is supported
		if ( java.awt.Desktop.isDesktopSupported ( ) ) {
			// Get the desktop info
			java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
			
			// Attempt to launch the browser
			try {
                java.net.URI uri = new java.net.URI ( url );
                desktop.browse ( uri );
            }
			// Inform the user of a browser launch error
            catch ( Exception e ) {
            	JOptionPane.showMessageDialog ( mainWindow, "There was a problem loading the URL: " + url, 
    					"Load Error", JOptionPane.ERROR_MESSAGE );
            }
		}
		// Alert the user that the desktop features are not supported
		else {
			JOptionPane.showMessageDialog ( mainWindow, "Sorry, your computer does not support java Desktop features.", 
					"Not Supported", JOptionPane.ERROR_MESSAGE ); 
		}
	}
}
