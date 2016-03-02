package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client /*Implements Runnable #OR# Extends Thread*/ {
	private static final String IP = "localhost";
	private static final int TCP = 8000;
	
	private String iP;
	private int tCP;
	private Socket socket;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private SubMenu menu;
	private boolean running = false;
	
	public Client(String iP, int tCP) throws IOException {
		this.iP = IP;
		this.tCP = TCP;
		socket = new Socket(iP, tCP);
	}

	private void setupConnection() throws IOException {
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		System.out.println("Connection established");
	}
	
	private void createMenu() {
		menu = new SubMenu("Main menu", "Please, choose what you would like to do.");
		menu.addItem(new MenuItem("Exit") {
			
			@Override
			public boolean launch() throws ClassCastException, ClassNotFoundException, IOException {
				finish();
				return false;
			}
		});
	}
	
	public void run() {
		try {
			setupConnection();
			createMenu();
			running = true;
			while (running) {
				menu.launch();
			}
		} catch (ClassCastException e) {
			ErrorPrinter.printErrorMessage("an Object cannot be casted to the appropriate Class", e, false, true);
		} catch (ClassNotFoundException e) {
			ErrorPrinter.printErrorMessage("Class of an Object cannot be found", e, false, true);
		} catch (IOException e) {
			ErrorPrinter.printErrorMessage("IOException occoured", e, true, true);
		} finally {
			if (running) {
				tryToCloseConnection();
			}
		}
	}
	
	private void finish() throws IOException {
		System.out.println("Shutting down");
		closeConnection();
		running = false;
	}
	
	private void closeConnection() throws IOException {
		oos.close();
		ois.close();
		socket.close();
	}
	
	private void tryToCloseConnection() {
		System.out.println("an Error has occoured");
		System.out.println("Shutting down");
		try {
			oos.close();
		} catch (IOException e) {
			ErrorPrinter.printErrorMessage("RESOOURCE-LEAKAGE: Unable to close ObjectOutputStream to Server", e, true, true);
		}
		try {
			ois.close();
		} catch (IOException e) {
			ErrorPrinter.printErrorMessage("RESOOURCE-LEAKAGE: Unable to close ObjectInputStream from Server", e, true, true);
		}
	}
	
	public static void main(String[] args) {
		try {
			Client client = new Client(IP, TCP);
			client.run();
		} catch (IOException e) {
			ErrorPrinter.printErrorMessage("IOException occoured", e, true, true);
		}
	}

}
