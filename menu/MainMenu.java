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
			subM.selectableMenuPoints();
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
			System.out.println("4. Directory scanner");
			System.out.println("5. Exit");
			System.out.println("#-----------------------------------#");
			System.out.println();
	}
	
	
	public void selectableMenuPoints()
	{
		String menuPoint = userInput();
		ModifySubmenu modifyPrintMenu = new ModifySubmenu();
		SorterSubMenu sorterPrintMenu = new SorterSubMenu();
		DirectoryScannerSubmenu directoryScanning = new DirectoryScannerSubmenu();
		M3USubmenu m3uSubmenu = new M3USubmenu();
		
		switch (menuPoint)
		{
		case "1":
			System.out.println("Sort submenu");
			sorterPrintMenu.printMenu();
			sorterPrintMenu.selectableMenuPoints();
			break;
		case "2":
			System.out.println("Modify submenu");
			modifyPrintMenu.printMenu();
			modifyPrintMenu.selectableMenuPoints();
			break;
		case "3":
			System.out.println("M3U submenu");
			m3uSubmenu.printMenu();
			m3uSubmenu.selectableMenuPoints();
			break;
		case "4":
			System.out.println("Directory scanner");
			directoryScanning.printMenu();
			directoryScanning.selectableMenuPoints();
			break;
		case "5":
			System.exit(0);
			break;
		default:
			PossibleExceptions.menuPointException();
			this.printMenu();
			this.selectableMenuPoints();
			break;
		}
	}
	
	public String userInput()
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
	      
		return s;
	}
}
