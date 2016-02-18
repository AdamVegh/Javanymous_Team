package menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import directoryscanner.DirectoryScanner;
import exceptions.PossibleExceptions;
import userinput.UserInput;

/*
system asks which folder need to be investigated
system warns the user if the given folder not exists
system asks the user if he/she would collect files recursively
system log out the relative path of mp3 files
 */

public class DirectoryScannerSubmenu
{
	public DirectoryScanner ds = new DirectoryScanner();
	public void printMenu()
	{
			System.out.println();
			System.out.println("Do you want to collect files at a given path?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			System.out.println("#-----------------------------------#");
			System.out.println();
	}
	
	
	public void selectableMenuPoints()
	{
		int menuPoint = userInput();
		MainMenu mM = new MainMenu();
		System.out.println();
		switch (menuPoint)
		{
		case 1:
			ds.listFiles();
			break;
		case 2:
			mM.printMenu();
			mM.selectableMenuPoints();
			break;
		default:
			PossibleExceptions.menuPointException();
			this.printMenu();
			this.selectableMenuPoints();
			break;
		}
	}
	
	public int userInput()
	{
		BufferedReader bufferRead;
		System.out.println();
    	System.out.print("Choose: ");
		System.out.println();
	    String s = "";
	    
		try
		{
			bufferRead = new BufferedReader(new InputStreamReader(System.in));
			s = bufferRead.readLine();
		}
		catch (IOException e)
		{
			System.out.println(e.toString());
		}
	      
		return Integer.parseInt(s);
	}
}
