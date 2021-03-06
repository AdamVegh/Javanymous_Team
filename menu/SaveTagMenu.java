package menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.PossibleExceptions;
import id3tag.Id3TagMp3;

public class SaveTagmenu
{


	public void printMenu()
	{
//			Runtime.getRuntime().exec("cls");
			System.out.println();
			System.out.println("Would you like to save modified tag?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			System.out.println();
	}
	
	
	public void selectableMenuPoints()
	{
		int menuPoint = userInput();
		ModifySubmenu backToModifiableTags = new ModifySubmenu();
		ModifyAnotherTag doYouWantToModify = new ModifyAnotherTag();
		
		switch (menuPoint)
		{
		case 1:
			ModifySubmenu.id3tag.writeNewId3ToMp3(ModifySubmenu.filePath);
			System.out.println("Tag has been saved!");
			doYouWantToModify.printMenu();
			doYouWantToModify.selectableMenuPoints();
			break;
		case 2:
			System.out.println("You didn't make changes on mp3 file tags.");
			backToModifiableTags.printMenu();
			backToModifiableTags.selectableMenuPoints();
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
