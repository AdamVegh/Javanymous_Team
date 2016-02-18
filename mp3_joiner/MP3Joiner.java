package mp3_joiner;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class MP3Joiner {
	
	private static final int SIZE_OF_BUFFER = (1 << 20);

	// checker used for mp3 files:
	public static boolean checkIfValidMP3(File mp3File) {
		return mp3File.isFile() && mp3File.getName().toLowerCase().endsWith(".mp3");
	}

	// joiner function:
	public static void joinFiles(List<File> sourceFileList, File destinationFile) throws IOException {
		RandomAccessFile writerOfDestinationFile = new RandomAccessFile(destinationFile, "rws");
		writerOfDestinationFile.setLength(0);
		for (File sourceFile: sourceFileList) {
			if (! checkIfValidMP3(sourceFile))
				throw new IOException("Invalid mp3-file object");
			append(sourceFile, writerOfDestinationFile);
		}
		writerOfDestinationFile.close();
	}
	
	private static void append(File sourceFile, RandomAccessFile writerOfDestinationFile) throws IOException {
		long lengthOfDestinationFile = writerOfDestinationFile.length();
		if (lengthOfDestinationFile > 128 && checkIfID3Tagged(writerOfDestinationFile)) {
			writerOfDestinationFile.setLength(lengthOfDestinationFile - 128);
			lengthOfDestinationFile -= 128;
		}
		writerOfDestinationFile.seek(lengthOfDestinationFile);
		RandomAccessFile readerOfSourceFile = new RandomAccessFile(sourceFile, "r");
		long lengthOfSourceFile = readerOfSourceFile.length();
		readerOfSourceFile.seek(0);
		{
			long positionInDestinationFile = lengthOfDestinationFile;
			long positionInSourceFile = 0;
			byte[] bytesToCopy = new byte[SIZE_OF_BUFFER];
			int numberOfBytesRead;
			while (positionInSourceFile < lengthOfSourceFile) {
				numberOfBytesRead = readerOfSourceFile.read(bytesToCopy, 0, SIZE_OF_BUFFER);
				positionInSourceFile += numberOfBytesRead;
				writerOfDestinationFile.write(bytesToCopy, 0, numberOfBytesRead);
				positionInDestinationFile += numberOfBytesRead;
			}
		}
		readerOfSourceFile.close();
	}
	
	private static boolean checkIfID3Tagged(RandomAccessFile writerOfFile) throws IOException {
		return getBeginningOfTail(writerOfFile).equals("TAG");
	}
	
	private static String getBeginningOfTail(RandomAccessFile writerOfFile) throws IOException {
		long currentPosition = writerOfFile.getFilePointer();
		writerOfFile.seek(writerOfFile.length() - 128);
		byte[] bytes = new byte[3];
		writerOfFile.read(bytes, 0, 3);
		writerOfFile.seek(currentPosition);
		return new String(bytes);
	}
	
}
