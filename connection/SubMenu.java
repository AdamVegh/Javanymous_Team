package connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubMenu extends MenuItem {
	/**
	 * This class is one of the two subclasses of MenuItem.
	 * It represents (sub)menu.
	 * Instance objects of this class contain a list of further MenuItems.
	 * Each of these items can be either a MenuPoint or another sub-menu.
	 * Method launch() gives User the opportunity to choose from MenuItems on the list.
	 * When instantiating, you can also give a description as well as a label.
	 * In this case, description is also displayed to User by launch().
	 */

	// instance variables and constructors:
	private String description;
	private List<MenuItem> items = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	public SubMenu(String label) {
		super(label);
	}
	public SubMenu(String label, String description) {
		super(label);
		this.description = description;
	}
	
	// modifiers for list of MenuItems:
	public void addItem(MenuItem newItem) {
		items.add(newItem);
	}
	
	public void addItem(int index, MenuItem newItem) {
		items.add(index, newItem);
	}
	
	public MenuItem getItem(int index) {
		return items.get(index);
	}
	
	public int getIndexOfItem(MenuItem item) {
		return items.indexOf(item);
	}
	
	public void removeItem(int index) {
		items.remove(index);
	}
	
	public void removeItem(MenuItem item) {
		items.remove(item);
	}
	
	public void clearListOfItems() {
		items.clear();
	}
	
	public int getNumberOfItems() {
		return items.size();
	}
	
	// launcher:
	@Override
	public boolean launch() throws ClassCastException, ClassNotFoundException, IOException {
		int selection;
		do {  // while selected item returns true
			selection = makeSelection();
			if (selection == -1)
				return true;  // means previous level should keep going
			if (selection == -2)
				return false;  // means menu should close
		} while (items.get(selection).launch());
		return false;  // menu should close
	}
	
	// private assistant methods used by launcher:
	private int makeSelection() {
		String userAnswer;
		int selection = items.size();  // surely out of range 
		do {  // until selection is in correct range
			clearScreen();
			printMessage();
			listItems();
			userAnswer = scanner.nextLine();
			clearScreen();
			if (userAnswer.equals("back"))
				return -1;  // means previous level should keep going
			if (userAnswer.equals("quit"))
				return -2;  // means menu should close
			try {
				selection = Integer.parseInt(userAnswer) - 1;
			}
			catch (NumberFormatException e) {}
		} while (!(selection >= 0 && selection < items.size()));
		return selection;
	}
	
	private void printMessage() {
		System.out.println(label);
		if (description != null)
			System.out.println(description);
	}
	
	private void listItems() {
		int i = 0;
		for (MenuItem item: items) {
			System.out.println(++i + ": " + item.getLabel());
		}
	}
	
	private static void clearScreen() {
		System.out.println();
		System.out.println("--------------------------");
		System.out.println();
	}

}
