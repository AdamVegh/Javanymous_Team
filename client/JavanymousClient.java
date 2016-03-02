package client;

import java.io.ObjectOutputStream;
import java.net.Socket;

import commonenums.Command;

public class JavanymousClient
{

	public JavanymousClient ()
	{
		Socket s = null;
		ObjectOutputStream oos = null;
		try
		{
			s = new Socket("localhost",10022);
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(Command.EXIT);
			oos.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String [ ] args)
	{
		new JavanymousClient();
	}
	
}
