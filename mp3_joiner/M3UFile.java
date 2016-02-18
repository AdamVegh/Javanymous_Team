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
	private File m3u;
	private File destination;
	private List<File> source;
	
	// constructor:
	M3UFile(File m3u) throws IOException {
		if (!checkIfValidM3U(m3u))
			throw new IOException("Inappropriate m3u-file");
		String m3uName = m3u.getName();
		this.m3u = m3u;
		String destinationName = m3uName.substring(0, m3uName.length() - ".m3u".length()) + ".mp3"; 
		this.destination = new File(destinationName);
		BufferedReader reader = new BufferedReader(new FileReader(m3u));
		this.source = new ArrayList<File>();
		{
			String line = null;
			String nonComment = null;
			File actualSourceFile = null;
			while ((line = reader.readLine()) != null) {
				nonComment = line.startsWith("#") ? "" : line.split("#")[0];
				if (nonComment.length() > 0) {
					actualSourceFile = new File(nonComment);
					if (! MP3Joiner.checkIfValidMP3(actualSourceFile))
						throw new IOException("Wrong file reference in m3u file");
					this.source.add(actualSourceFile);
				}
			}
		}	
		reader.close();
	}
	
	// m3u file-checker used by constructor:
	static boolean checkIfValidM3U(File m3u) {
		if (!m3u.isFile())
			return false;
		String name = m3u.getName();
		return name.endsWith(".m3u") || name.endsWith(".M3U");
	}
	
	// processor method:
	File process() throws IOException {
		MP3Joiner.join(source, destination);
		return destination;
	}
	
}
