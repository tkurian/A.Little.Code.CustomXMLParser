/*******************************************************************************************
*Source File Name: XMLValidation.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 29th, 2012

*Class Description: This class is for handling the validation of an xml file
********************************************************************************************/

package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Class Name:		XMLValidation
 * Description:		This class will validate the xml file
 * 					
 */
public class XMLValidation 
{
	/*
	 * Method Name: ValidateXML
	 * Description: Validates the xml file which is passed in as a String. 
	 * Parameters: String version.
	 * Return: boolean indicating whether the file is a valid xml file or not. 
	 */
	public boolean ValidateXML(String version)
	{
		boolean isXml = false; 
		
		Pattern rssPattern = Pattern.compile ( "<( *)rss(.*?)>", Pattern.CASE_INSENSITIVE );
		Pattern versionPattern = Pattern.compile ( "version( *)=( *)\"2.0\"", Pattern.CASE_INSENSITIVE );
		
		Matcher matcher = rssPattern.matcher ( version );
		
		if ( matcher.find( ) ) 
		{
			 Matcher versionMatcher = versionPattern.matcher ( matcher.group ( ) );
			 
			 if ( versionMatcher.find ( ) ) {
				 isXml  = true;
			 }
		}
		
		
		return isXml; 
	}
}
