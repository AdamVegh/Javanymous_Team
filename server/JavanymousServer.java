package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import commonenums.Command;
import mp3_joiner.MP3Joiner;

public class JavanymousServer
{
	private final static String	PATH_TO_SAVE	= "C:\\test\\";
	private final static String	JOINED_FILE		= "C:\\test\\kuka.mp3";

	@SuppressWarnings ("unchecked")
	public JavanymousServer ()
	{
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		ObjectInputStream ois = null;

			try
			{
				serverSocket = new ServerSocket(10022);
				System.out.println("Waiting for client...");
				clientSocket = serverSocket.accept();

				ois = new ObjectInputStream(clientSocket.getInputStream());

				Object command = ois.readObject();

				if (command.equals(Command.JOIN))
				{
					List< File > listFiles = (List< File >) ois.readObject();
					List< File > serverFiles = new ArrayList< File >();
					for (File file : listFiles)
					{
						File destination = new File(PATH_TO_SAVE + file.getName());
						serverFiles.add(destination);
						saveFile(clientSocket, destination, file.length());
					}
					File finalFile = new File(JOINED_FILE);
					MP3Joiner.joinFiles(serverFiles, finalFile);
					sendFile(finalFile, clientSocket);
				}
				else if (command.equals(Command.SORT))
				{
					// TODO
				}
				else if (command.equals(Command.EXIT))
				{
					System.out.println("SERVER SHUTDOWN!");
					ois.close();
					clientSocket.close();
					serverSocket.close();
				}

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
		while ((read = dis.read(buffer, 0, Math.min(buffer.length, (int) remaining))) > 0)
		{
			totalRead += read;
			remaining -= read;
			System.out.println("read " + totalRead + " bytes.");
			fos.write(buffer, 0, read);
		}

		fos.close();
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

		fis.close();
		// dos.close();
	}

	public static void main(String [ ] args)
	{
		new JavanymousServer();
	}
}
