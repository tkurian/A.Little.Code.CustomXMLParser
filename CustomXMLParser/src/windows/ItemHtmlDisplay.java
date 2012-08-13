/*******************************************************************************************
*Source File Name: ItemHtmlDisplay.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: 
********************************************************************************************/

package windows;

import database.Item;


/*
 * Class Name:		ItemHtmlDisplay
 * Description:		This function is used to created HTML displays
 */
public class ItemHtmlDisplay {
	
	/*
	 * Method Name: createItemHtmlString
	 * Description: This function creates a display string for displaying a RSS item
	 * 				to the screen.
	 * Parameters: Item itemToDisplay.
	 * Return: 
	 */
	public static String createItemHtmlString ( Item itemToDisplay ) {
		String htmlDisplayString = "<html><body style='width:100%;'>";
		htmlDisplayString += "<div " + headerCss ( ) + ">";
		htmlDisplayString += itemToDisplay.getTitle ( );
		htmlDisplayString += "<br>";
		htmlDisplayString += "<div style='font-weight: normal;font-size: 8px;color: #555;'>";
		htmlDisplayString += "by <i>Unknown</i> on " + itemToDisplay.getPubDate ( ) + "</div>";
		htmlDisplayString += "</div>";
		htmlDisplayString += "<div><br><br>";
		htmlDisplayString += itemToDisplay.getDescription ( );
		htmlDisplayString += "<br><br>";
		htmlDisplayString += "Full story at: ";
		htmlDisplayString += "<a href='" + itemToDisplay.getLink ( ) + "'>" + itemToDisplay.getLink ( )  + " </a>";
		htmlDisplayString += "</div>";
		htmlDisplayString += "</body></html>";
		
		// Return the created html string
		return htmlDisplayString;
	}
	
	
	/*
	 * Method Name: headerCss
	 * Description: This function will create a CSS style that can be added
	 * 				to an element being added to a JEditorPane.
	 * Parameters: no parameters.
	 * Return:
	 */
	private static String headerCss ( ) {
		String headerCss = "style='";
		headerCss += "border-bottom-color: #DADADB;";
		headerCss += "background-color: #F1F2F4;";
		headerCss += "border-bottom-width: 1px;";
		headerCss += "border-bottom-style: solid;";
		headerCss += "display: block;";
		headerCss += "background-attachment: scroll;";
		headerCss += "background-clip: border-box;";
		headerCss += "background-color: transparent;";
		headerCss += "color: #222;";
		headerCss += "display: block;";
		headerCss += "font-family: tahoma, verdana, arial, sans-serif;";
		headerCss += "font-size: 12px;";
		headerCss += "font-style: normal;";
		headerCss += "font-variant: normal;";
		headerCss += "font-weight: bold;";
		headerCss += "height: 14px;";
		headerCss += "'";
		
		// Return the addable CSS
		return headerCss;
	}
}
