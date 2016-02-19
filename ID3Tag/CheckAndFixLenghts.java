package ID3Tag;

public class CheckAndFixLenghts {
	
	private static final int MAXLENGTH = 30;
    private static final int MAXLENGTHYEAR = 4;
    
	
	
	protected static Id3TagMp3 fixingId3TagMp3Tags(Id3TagMp3 id3) 
	{
		id3.setTitle(fixTagLen(id3.getTitle()));
		id3.setArtist(fixTagLen(id3.getArtist()));
		id3.setAlbum(fixTagLen(id3.getAlbum()));
		id3.setYear(fixYearLen(id3.getYear()));
		id3.setComment(fixTagLen(id3.getComment()));
		return id3;
	}
	
	protected static String fixTagLen(String tag) 
	{
		String newString = tag;
		if (tag.length() > 30) {
			newString = tag.substring(0,MAXLENGTH);
			return newString;
		}
		else if (tag.length() < 30) {
			int diff = 30 - tag.length();
			while (diff > 0) {
				newString += '\u0000';
				diff -= 1;
			}
			return newString;
		}
		return tag;
	}
	
    public static String fixYearLen(String year) 
    {
    	String newYear = String.valueOf(year);
    	if (year.length() > 4) {
    		newYear = year.substring(0,MAXLENGTHYEAR);
			return newYear;
		}
    	else if (year.length() < 4) {
			int diff = 4 - year.length();
			while (diff > 0) {
				newYear += '\u0000';
				diff -= 1;
			}
			return newYear;
		}
		return year;
	}
}
