package menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import userinput.*;

import directoryscanner.DirectoryScanner;
import sorter.*;
import exceptions.NotDirectoryException;

public class SorterSubMenu
{
	public void printMenu()
	{
//			Runtime.getRuntime().exec("cls");
			System.out.println();
			System.out.println("Sorted according by:");
			System.out.println("1. Title");
			System.out.println("2. Artist");
			System.out.println("3. Album");
			System.out.println("4. Year");
			System.out.println("5. Genre");
			System.out.println("6. Back");
			System.out.println("#-----------------------------------#");
			System.out.println();
	}
	
	
	public void selectableMenuPoints() throws FileNotFoundException, NotDirectoryException
	{
		File f = new File(UserInput.getInputFromUser());
		DirectoryScanner ds = new DirectoryScanner(f , ".mp3");
		SorterMenu.sorterMain(ds.listFiles());
//		int key;
		String menuPoint = userInput();
		MainMenu mM = new MainMenu();
		mM.printMenu();
		mM.selectableMenuPoints();
		
//		switch (menuPoint)
//		{
//		case "1":
//			System.out.println("Title submenu");
//			this.printMenu();
//			this.selectableMenuPoints();
//			break;
//		case "2":
//			System.out.println("Artist submenu");
//			this.printMenu();
//			this.selectableMenuPoints();
//			break;
//		case "3":
//			System.out.println("Album submenu");
//			this.printMenu();
//			this.selectableMenuPoints();
//			break;
//		case "4":
//			System.out.println("Year submenu");
//			this.printMenu();
//			this.selectableMenuPoints();
//			break;
//		case "5":
//			System.out.println("Genre submenu");
//			this.printMenu();
//			this.selectableMenuPoints();
//			break;
//		case "6":
//			mM.printMenu();
//			mM.selectableMenuPoints();
//			break;
//		default:
//			PossibleExceptions.menuPointException();
//			this.printMenu();
//			this.selectableMenuPoints();
//			break;
//		}
	}
	
	public String userInput()
	{
		BufferedReader bufferRead;
		System.out.println();
    	System.out.print("Choose: ");
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
	      
		return s;
	}
}
