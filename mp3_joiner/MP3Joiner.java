package mp3_joiner;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class MP3Joiner {
	
	private static final int SIZE_OF_BUFFER = (1 << 20);  // 2^20, in other words 1M
	private static RandomAccessFile writerOfDestinationFile = null;
	private static RandomAccessFile readerOfSourceFile = null;

	// checker used for mp3 files:
	public static boolean checkIfValidMP3(File mp3File) {
		return mp3File.isFile() && mp3File.getName().toLowerCase().endsWith(".mp3");
	}

	// joiner function:
	public static void joinFiles(List<File> sourceFileList, File destinationFile) throws IOException {
		writerOfDestinationFile = new RandomAccessFile(destinationFile, "rws");
		writerOfDestinationFile.setLength(0);
		for (File sourceFile: sourceFileList) {
			if (! checkIfValidMP3(sourceFile))
				throw new IOException("Invalid mp3-file object");
			appendSourceToDestination(sourceFile);
		}
		writerOfDestinationFile.close();
		writerOfDestinationFile = null;
	}
	
	// private assistant procedures:
	private static void appendSourceToDestination(File sourceFile) throws IOException {
		
		// initializing data-dumping:
		readerOfSourceFile = new RandomAccessFile(sourceFile, "r");
		if (writerOfDestinationFile.length() > 128 && checkIfDestinationIsID3Tagged())
			writerOfDestinationFile.setLength(writerOfDestinationFile.length() - 128);
		writerOfDestinationFile.seek(writerOfDestinationFile.length());
		readerOfSourceFile.seek(0);
		
		// dumping data:
		{
			byte[] bytesToCopy = new byte[SIZE_OF_BUFFER];  // 1MB of data
			int numberOfBytesRead;
			while (readerOfSourceFile.getFilePointer() < readerOfSourceFile.length()) {
				numberOfBytesRead = readerOfSourceFile.read(bytesToCopy, 0, SIZE_OF_BUFFER);
				writerOfDestinationFile.write(bytesToCopy, 0, numberOfBytesRead);
			}
		}
		
		// closing steps:
		readerOfSourceFile.close();
		readerOfSourceFile = null;
	}
	
	private static boolean checkIfDestinationIsID3Tagged() throws IOException {
		byte[] beginningOfTail = getBytesFromDestinationFile(writerOfDestinationFile.length() - 128, "TAG".length());
		return new String(beginningOfTail).equals("TAG");
	}
	
	private static byte[] getBytesFromDestinationFile(long offset, int len) throws IOException {
		long currentPosition = writerOfDestinationFile.getFilePointer();
		writerOfDestinationFile.seek(offset);
		byte[] bytes = new byte[len];
		writerOfDestinationFile.read(bytes, 0, len);
		writerOfDestinationFile.seek(currentPosition);
		return bytes;
	}
	
}
