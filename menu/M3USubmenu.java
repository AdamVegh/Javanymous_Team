package menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.PossibleExceptions;
import mp3_joiner.MP3JoinerUI;

public class M3USubmenu
{
	public void printMenu()
	{
			MP3JoinerUI mp3Joiner = new MP3JoinerUI();
//			Runtime.getRuntime().exec("cls");
			System.out.println();
			mp3Joiner.mp3JoinerGetInputFromUser();
			System.out.println("1. Back");
			System.out.println("#-----------------------------------#");
			System.out.println();
	}
	
	public void selectableMenuPoints()
	{
		int menuPoint = userInput();
		MainMenu mM = new MainMenu();
		
		switch (menuPoint)
		{
		case 1:
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
