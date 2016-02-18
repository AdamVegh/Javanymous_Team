package menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.PossibleExceptions;

public class MainMenu
{
	public static void main(String[] args)
	{
		MainMenu subM = new MainMenu();
		while(true)
		{
			subM.printMenu();
			subM.selectMenuPoint();
		}
	}
	
	public void printMenu()
	{
//			Runtime.getRuntime().exec("cls");
			System.out.println();
			System.out.println("---MAIN MENU---");
			System.out.println("1. Sort");
			System.out.println("2. Modify");
			System.out.println("3. M3U");
			System.out.println("4. Exit");
			System.out.println("#-----------------------------------#");
			System.out.println();
	}
	
	
	public void selectMenuPoint()
	{
		int menuPoint = userInput();
		ModifySubmenu modifyPrintMenu = new ModifySubmenu();
		SorterSubMenu sorterPrintMenu = new SorterSubMenu();
		switch (menuPoint)
		{
		case 1:
			System.out.println("Sort submenu");
			sorterPrintMenu.printMenu();
			sorterPrintMenu.selectMenuPoint();
			break;
		case 2:
			System.out.println("Modify submenu");
			modifyPrintMenu.printMenu();
			modifyPrintMenu.selectMenuPoint();
			break;
		case 3:
			System.out.println("M3U submenu");
			break;
		case 4:
			System.exit(0);
			break;
		default:
			PossibleExceptions.menuPointException();
			this.printMenu();
			this.selectMenuPoint();
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
