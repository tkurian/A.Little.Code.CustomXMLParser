/*******************************************************************************************
*Source File Name: RssFeedListCellRenderer.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: 
********************************************************************************************/

package windows;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import utilities.RssFeed;


/*
 * Class Name:		RssFeedListCellRenderer
 * Description:		This class is used for displaying a feed list on the screen.
 */
public class RssFeedListCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 3163287636995960656L;
	final ImageIcon defaultIcon = new ImageIcon ( "images/rss.gif" );

    /* This is the only method defined by ListCellRenderer.  We just
     * reconfigure the Label each time we're called.
     */
    public Component getListCellRendererComponent ( JList list, Object value, int index, boolean iss, boolean chf ) {
        /* The DefaultListCellRenderer class will take care of
         * the JLabels text property, it's foreground and background
         * Colours, and so on.
         */
        super.getListCellRendererComponent ( list, value, index, iss, chf );
        
        // Get the current feed being displayed
        RssFeed currentFeed = (RssFeed)value;
        
        // If a name is too long reduce it's size
        if ( currentFeed.getName ( ).length ( ) > 30 ) {
        	// Substring the value and add the ellipses at the end
        	setText ( currentFeed.getName ( ).substring ( 0, 29 ).concat ( "..." ) );
        }
        
        // Display the default icon
        setIcon ( defaultIcon );

        // Return the new image
        return this;
    }
}
