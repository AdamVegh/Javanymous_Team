package menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.PossibleExceptions;
import userinput.UserInput;

public class M3USubmenu
{
	public void printMenu()
	{
//			Runtime.getRuntime().exec("cls");
			System.out.println();
			System.out.println("Which M3U need to be progressed?");
			System.out.println("M3U file path:" + UserInput.getInputFromUser());
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
