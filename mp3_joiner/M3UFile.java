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
		String m3uName = m3u.getName();
		if (!m3u.isFile() || !m3uName.endsWith(".m3u"))
			throw new IOException("Inappropriate m3u-file");
		this.m3u = m3u;
		String destinationName = m3uName.substring(0, m3uName.length() - ".m3u".length()) + ".mp3"; 
		this.destination = new File(destinationName);
		BufferedReader reader = new BufferedReader(new FileReader(m3u));
		String line = null;
		String nonComment = null;
		this.source = new ArrayList<File>();
		File actualSourceFile = null;
		while ((line = reader.readLine()) != null) {
			nonComment = line.startsWith("#") ? "" : line.split("#")[0];
			if (nonComment.length() >= 0) {
				actualSourceFile = new File(nonComment);
				if (!actualSourceFile.isFile() || !nonComment.endsWith(".mp3"))
					throw new IOException("Wrong file reference in m3u file");
				this.source.add(actualSourceFile);
			}
		}
		reader.close();
	}
	
	// processor method:
	File process() {
		// processor algorythm comes here
		return new File("");
	}
	
}
