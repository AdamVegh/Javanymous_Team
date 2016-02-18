package mp3_joiner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class M3UFile {

	// member variables:
	private File m3uFile;
	private File destinationFile;
	private List<File> sourceFileList;
	
	// constructor:
	M3UFile(File m3uFile) throws IOException {
		if (!checkIfValidM3U(m3uFile))
			throw new IOException("Inappropriate m3u-file");
		this.m3uFile = m3uFile; 
		this.destinationFile = createDestinationFileObj(m3uFile);
		this.sourceFileList = parseM3UFile(m3uFile);
	}
	
	// getters:
	File getM3UFile() { return m3uFile; }
	File getDestinationFile() { return destinationFile; }
	List<File> getSourceFileList() { return sourceFileList; }
	
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
	
	// other assistant functions used by constructor:
	private static File createDestinationFileObj(File m3uFile) {
		String m3uPath = m3uFile.getAbsolutePath();
		String destinationPath = m3uPath.substring(0, m3uPath.length() - ".m3u".length()) + ".mp3";
		return new File(destinationPath);
	}
	
	private static List<File> parseM3UFile(File m3uFile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(m3uFile));
		List<File> sourceFileList = new ArrayList<>();
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
				sourceFileList.add(actualSourceFile);
			}
		}
		reader.close();
		return sourceFileList;
	}
	
}
