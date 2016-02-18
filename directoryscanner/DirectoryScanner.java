package directoryscanner;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import userinput.UserInput;
import exceptions.NotDirectoryException;


public class DirectoryScanner
{
	public DirectoryScanner()
	{
		
	}
	private static String ext = "mp3";
	List<File> mp3Files;

	public DirectoryScanner(File directory, String extension) throws FileNotFoundException, NotDirectoryException
	{
		if (!directory.exists())
		{
			throw new FileNotFoundException();
		}
		if (directory.isFile())
		{
			throw new NotDirectoryException();
		}
		if (directory.isDirectory())
		{
			mp3Files = new ArrayList<File>();
			collect(directory);
		}
	}

	public List<File> getMP3Files()
	{
		return mp3Files;
	}

	public void collect(File directory)
	{
		File[] fileList = directory.listFiles(new FileFilter()
		{
			@Override
			public boolean accept(File pathname)
			{
				if (pathname.isFile())
				{
					String extension;
					int lastPointIndex = pathname.getName().lastIndexOf('.');
					if (lastPointIndex > -1)
					{
						extension = pathname.getName().substring(lastPointIndex + 1);
						return extension.toLowerCase().equals(ext);
					}
					else
					{
						return false;
					}
				}
				return true;
			}
		});
		if (fileList != null)
		{
			for (File file : fileList)
			{
				if (file.isDirectory())
				{
					collect(file);
				}
				else
				{
					mp3Files.add(file);
				}
			}
		}
	}
	
	
	public void listFiles()
	{
		try
		{
			DirectoryScanner ds = new DirectoryScanner(new File(UserInput.getInputFromUser()), ext);
			
			List<File> mp3Files = ds.getMP3Files();
			System.out.println("Collected file(s):");
			for (File mp3File : mp3Files)
			{
				System.out.println(mp3File);
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.toString());
		}
		catch (NotDirectoryException e)
		{
			System.out.println(e.toString());
		}
	}
}
