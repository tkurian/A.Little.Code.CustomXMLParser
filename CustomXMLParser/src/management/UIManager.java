/*******************************************************************************************
*Source File Name: UIManager.java

*Programmer's Name: Tina Kurian, Fred Ulrich, Patrick Szafranko

*Date: March, 23rd, 2012

*Class Description: This class manages the user interface by either launching the console or
*graphical user interface based on the current OS being used.
********************************************************************************************/

package management;

import windows.WindowsGUI;
import linux.LinuxConsole;



/*
 * Class Name:		UIManager
 * Description:		This class launches either the console or GUI based on the OS.
 */
public class UIManager 
{
	
	/*
	 * Method Name: main
	 * Description: this method will determine whether to run the Linux Console version or
	 * 				the windows GUI version of the application.
	 * Parameters: String[] args.
	 * Return: no return value.
	 */
	public static void main(String[] args) 
	{
		String whichOS = null;
		
		//gets the OS property name
		whichOS = System.getProperty("os.name").toLowerCase();
		
		try
		{
			//if Windows, launch GUI
			if(whichOS.startsWith("win"))
			{
				WindowsGUI.startWindowsGui ( );				
			}
			else //else, launch Console (Linux)
			{
				LinuxConsole console = new LinuxConsole();
				console.runLinuxConsole(); 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
