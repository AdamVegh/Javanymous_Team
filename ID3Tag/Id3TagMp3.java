package ID3Tag;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class Id3TagMp3 {
	
    private String title;
    private String artist;
    private String album;
    private String year;
    private String comment;
    private String genre;
    
    private static final String HEADER = "TAG";
    
    
    
    private Id3TagMp3() 
    {
    }
    
    private static byte[] readXBytes(byte[] byteArray, int fromPos, int toPos) 
    {
        byte[] resultArray = new byte[toPos - fromPos];
        for (int i = fromPos; i < toPos; i++) 
        {
            resultArray[i - fromPos] = byteArray[i];
        }
        return resultArray;
    }
    
    public static Id3TagMp3 parse(File file) 
    {
        byte[] last128 = tail(file);
        byte[] baTitle = readXBytes(last128, 3, 33);
        String title = new String(baTitle).trim();
        byte[] baArtist = readXBytes(last128, 33, 63);
        String artist = new String(baArtist).trim();
        byte[] baAlbum = readXBytes(last128, 63, 93);
        String album = new String(baAlbum).trim();
        byte[] baYear = readXBytes(last128, 93, 97);
        String year = new String(baYear).trim();
        byte[] baComment = readXBytes(last128, 97, 125);
        String comment = new String(baComment).trim();
        byte[] baGenre = readXBytes(last128, 127, 128);
        String genre = new String(baGenre).trim();
        Id3TagMp3 tag = new Id3TagMp3();
        tag.setTitle(title);
        tag.setArtist(artist);
        tag.setAlbum(album);
        tag.setYear(year);
        tag.setComment(comment);
        tag.setGenre(genre);
        return tag;
    }
    public static byte[] tail(File file) 
    {
        try {
            RandomAccessFile fileHandler = new RandomAccessFile(file, "r");
            long fileLength = fileHandler.length() - 1;
            byte[] buffer = new byte[128];
            for (int i = 0; i < buffer.length; i++) {
                fileHandler.seek(fileLength - 127 + i);
                buffer[i] = fileHandler.readByte();
            }
            fileHandler.close();
            return buffer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
    	this.title = title;
    }
    
    public String getArtist(){
        return artist;
    }
    public void setArtist(String artist){
    	this.artist = artist;
    }
    
    public String getAlbum() 
    {
        return album;
    }
    public void setAlbum(String album) 
    {
      this.album = album;
    }
    
    public String getYear() 
    {
        return year;
    }
    public void setYear(String year){
    	this.year = year;
    }

    public String getComment() 
    {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getGenre() 
    {
        return genre;
    }
    public void setGenre(String genre) 
    {
        this.genre = genre;
    }
    
    @Override
    public boolean equals(Object o) 
    {
        Id3TagMp3 tag = (Id3TagMp3) o;
        return ((title == null && tag.getTitle() == null) || title.equals(tag.getTitle()))
                && ((artist == null && tag.getArtist() == null) || artist.equals(tag.getArtist()))
                && ((album == null && tag.getAlbum() == null) || album.equals(tag.getAlbum())) && (year == tag.getYear())
                && ((comment == null && tag.getComment() == null) || comment.equals(tag.getComment())) && (genre == tag.getGenre());
    }
    
    @Override
    public int hashCode() 
    {
        return -1;
    }
    
    @Override
    public String toString() 
    {
        return "Artist: " + (artist == null ? "NULL" : artist).trim() + "\nAlbum: " + album + "\nTitle: " + title.trim()
                + "\nYear: " + year + "\nComment: " + comment + "\nGenre: " + genre;
    }
    
    public static boolean testFileExistance(String pathname) 
    {
    	File file = new File(pathname);
    	if (!file.exists()) 
    	{
			System.out.println("File doesnt exists.");
			return false;
		}
    	else
    	{
	    	System.out.println("File exists.");
	    	return true;
	    }
	}
    
    public static File getPathnameFromUser() throws IOException 
    {
    	BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Give the absolute path of the file u want to modify or check the id3tag:");
        String s = bufferRead.readLine();
        if (testFileExistance(s)) {
        	File file = new File(s);
        	return file;
        }
        getPathnameFromUser();
        return null;
	}
    
    public String getNewId3Tag() {
//    	CheckAndFixLenghts.fixingId3TagMp3Tags(this);
		StringBuilder builder = new StringBuilder();
		builder.append(HEADER);
		builder.append(getTitle());
		builder.append(getArtist());
		builder.append(getAlbum());
		builder.append(getYear());
		builder.append(getComment());
//		builder.append("\u0000");
//		builder.append("\u0000");
		builder.append(getGenre());
		String newID3Tag = builder.toString();
		return newID3Tag;
		}
    
    public void writeNewId3ToMp3(String path){
    	RandomAccessFile raff;
    	try 
    	{
    		raff = new RandomAccessFile(path, "rw");
    		raff.seek(raff.length()-128);
    		raff.writeBytes(getNewId3Tag());
    		raff.close();
		}
    	catch (IOException e) 
    	{
			System.out.println(e.toString());
		}
    }
    
    
    public static void main(String[] args) throws IOException 
    {	
    	File testFile = new File("C:\\Test\\test.mp3");
    	Id3TagMp3 testId3Tag = Id3TagMp3.parse(testFile);
    	testId3Tag.setTitle("HEYAYAYAAYAY");
    	testId3Tag.setYear("1994");
    	testId3Tag.getNewId3Tag();
    	System.out.println(testId3Tag.toString());
    	testId3Tag.writeNewId3ToMp3("C:\\Test\\test.mp3");
    }
}