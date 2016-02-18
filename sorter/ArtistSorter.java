package sorter;

import java.io.File;

import ID3Tag.Id3TagMp3;

public class ArtistSorter extends Sorter {
	public ArtistSorter() {
	}
	
	public ArtistSorter(boolean ascending) {
		super();
	}
	
	@Override
	public int compare(File o1, File o2) {
		Id3TagMp3 tag = Id3TagMp3.parse(o1);
		Id3TagMp3 tag2 = Id3TagMp3.parse(o2);
		if (ascending) {
			return tag.getTitle().compareToIgnoreCase(tag2.getTitle());
		}
		return -tag.getTitle().compareToIgnoreCase(tag2.getTitle());
	}
}
