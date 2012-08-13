/*******************************************************************************************
*Source File Name: RssItemCellRenderer.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: 
********************************************************************************************/

package windows;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

import database.Item;


/*
 * Class Name:		RssItemListCellRenderer
 * Description:		This function is used for displaying the RSS items on the JList
 */
public class RssItemListCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 3163287636995960656L;
	final ImageIcon rssIcon = new ImageIcon ( "images/rss.gif" );

    /* This is the only method defined by ListCellRenderer.  We just
     * reconfigure the Jlabel each time we're called.
     */
    public Component getListCellRendererComponent ( JList list, Object value, int index, boolean iss, boolean chf ) {
        /* The DefaultListCellRenderer class will take care of
         * the JLabels text property, it's foreground and background
         * colors, and so on.
         */
        super.getListCellRendererComponent ( list, value, index, iss, chf );
        
        // Get the current item
        Item currentItem = ( Item ) value;
        
        // Create alternating colors
        if ( iss == false ) {
        	if ( index % 2 == 0 ) { 
        		setBackground ( Color.WHITE );
        	}
        	else {
        		setBackground ( new Color ( 233, 232, 252 ) );
        	}
        }
         
        // Create the HTML text for displaying an item
        setText ( "<html><table style='width:100%;'><tbody>" +
        				"<tr><td><b>" + currentItem.getTitle ( ) + "</b></td></tr>" +
        				"<tr><td style='font-size:10;padding-top:-50px;margin-top:-5px;'><i>" + currentItem.getPubDate ( ) + "</i></td></tr>" +
        				"</table>");

        // Return the new image
        return this;
    }
}
