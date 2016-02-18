package sorter;

import java.io.File;
import sorter.ID3Tag;

public class ArtistSorter extends Sorter {
	boolean ascending = true;

	public ArtistSorter() {
	
	}
	
	public ArtistSorter(boolean ascending) {
		this.ascending = ascending;
	}
	
	
	@Override
	public int compare(File o1, File o2) {
		ID3Tag tag = ID3Tag.parse(ID3Tag.tail(o1));
		ID3Tag tag2 = ID3Tag.parse(ID3Tag.tail(o2));
		if (ascending) {
			return tag.getArtist().compareToIgnoreCase(tag2.getArtist());

		}

		return -tag.getArtist().compareToIgnoreCase(tag2.getArtist());
	}

}
