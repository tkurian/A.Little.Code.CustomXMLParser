/*******************************************************************************************
*Source File Name: FeedListListener.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: This class handles the mouse events for the Feed list from the WindowsGUI
********************************************************************************************/

package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


/*
 * Class Name:		FeedListListener
 * Description:		This class handles the mouse events for the Feed list
 * 					from the WindowsGUI. 
 */
public class FeedListListener extends MouseAdapter {
	
	
	private WindowsGUI mainWindow;
	
	
	/**
	 * @Constructor for FeedListListener
	 */
	public FeedListListener ( WindowsGUI mainWindow ) {
		this.mainWindow = mainWindow;
	}
	
	
	/*
	 * Method Name: mouseClicked
	 * Description: This function is called when there is a mouse click on the feed list.  It
	 * 				handles the right click and will show a pop up menu when the currently
	 * 				selected element is right clicked.  This menu will allow the user to view
	 * 				channel information.
	 * Parameters: MouseEvent e
	 * Return: N/A
	 */
	@Override
    public void mouseClicked ( MouseEvent e ) {
    	// Ensure that it was the right mouse button that was clicked
        if ( e.getButton() == MouseEvent.BUTTON3 ) {
        	// Get the JList that fired this event and the index that was clicked on
        	JList clickSource = ( JList ) e.getSource ( );
        	int index = clickSource.locationToIndex ( e.getPoint ( ) );
        	
        	// Ensure that the clicked area was on the index
        	if ( clickSource.getSelectedIndex ( ) == index ) {
        		JPopupMenu newMenu = new JPopupMenu ( );
        		
        		// Create a menu item for the menu
	        	JMenuItem viewChannelData = new JCheckBoxMenuItem ( "View Channel Data" );
	        	
	        	// Add the action listener for the menu item being pressed
	        	viewChannelData.addActionListener ( new ActionListener ( ) {
	        		public void actionPerformed ( ActionEvent event ) {
	        			mainWindow.createChannelInfo ( );
	        		}
	            });
	            
	        	// Add the menu button to the menu item and display the menu item
	            newMenu.add ( viewChannelData );
	        	newMenu.show ( ( ( JList ) e.getSource ( ) ), e.getX ( ), e.getY ( ) );
        	}
         }
    }
}
