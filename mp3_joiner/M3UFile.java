package mp3_joiner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.ActionMapUIResource;

public class M3UFile {

	// member variables:
	private File m3uFile;
	private File destinationFile;
	private List<File> sourceFileList;
	
	// constructor:
	M3UFile(File m3uFile) throws IOException {
		if (!checkIfValidM3U(m3uFile))
			throw new IOException("Inappropriate m3u-file");
		String m3uName = m3uFile.getAbsolutePath();
		this.m3uFile = m3uFile;
		String destinationName = m3uName.substring(0, m3uName.length() - ".m3u".length()) + ".mp3"; 
		this.destinationFile = new File(destinationName);
		BufferedReader reader = new BufferedReader(new FileReader(m3uFile));
		this.sourceFileList = new ArrayList<File>();
		{
			String line = null;
			String nonComment = null;
			File actualSourceFile = null;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("#"))
					continue;
				nonComment = line.split("#")[0];
				actualSourceFile = new File(nonComment);
				if (! MP3Joiner.checkIfValidMP3(actualSourceFile))
					throw new IOException("Wrong file reference in m3u file");
				this.sourceFileList.add(actualSourceFile);
			}
		}	
		reader.close();
	}
	
	// m3u file-checker used by constructor:
	static boolean checkIfValidM3U(File m3uFile) {
		if (!m3uFile.isFile())
			return false;
		String name = m3uFile.getName();
		return name.endsWith(".m3u") || name.endsWith(".M3U");
	}
	
	// processor method:
	File process() throws IOException {
		MP3Joiner.join(sourceFileList, destinationFile);
		return destinationFile;
	}
	
}
