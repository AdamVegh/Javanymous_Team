package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import commonenums.Command;
import mp3_joiner.*;

public class JavanymousClient
{
	final String FILEPATH = "C:\\Users\\Kristof\\Desktop\\newMp3.mp3";
	public JavanymousClient ()
	{
		Socket s = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try
		{
			s = new Socket("localhost",10022);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			oos.writeObject(Command.JOIN);
			File m3uFile = new File("C:\\Users\\Kristof\\Desktop\\valami2.m3u");
			List<File> fileObjList = M3UHandler.getMP3FileListFromM3U(m3uFile);
			Thread.sleep(500);
			oos.writeObject(fileObjList);
			for (File file : fileObjList)
			{
				sendFile(file,s);
			}
			long fileSize = (long)ois.readObject();
			saveFile(s,new File(FILEPATH), fileSize);
			ois.close();
			oos.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void saveFile(Socket clientSock, File file, long fileSize) throws IOException
	{
		DataInputStream dis = new DataInputStream(clientSock.getInputStream());
		FileOutputStream fos = new FileOutputStream(file);
		byte [ ] buffer = new byte [ 4096 ];

		int read = 0;
		int totalRead = 0;
		long remaining = fileSize;
		double percentage;
		while ((read = dis.read(buffer, 0, Math.min(buffer.length, (int) remaining))) > 0)
		{
			totalRead += read;
			remaining -= read;
//			System.out.println("read " + totalRead + " bytes.");
			percentage = ((double)totalRead)/(double)(totalRead+remaining)*100;
			System.out.println(String.format("%.1f", percentage) + "Downloading  %");
			fos.write(buffer, 0, read);
		}
//		fos.close();
	}

	public void sendFile(File file, Socket s) throws IOException
	{
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		FileInputStream fis = new FileInputStream(file);
		byte [ ] buffer = new byte [ 4096 ];
		int read;
		while ((read = fis.read(buffer)) > 0)
		{
			dos.write(buffer, 0, read);
		}

//		fis.close();
		// dos.close();
	}
	
	public static void main(String [ ] args)
	{
		new JavanymousClient();
	}
	
	
}
