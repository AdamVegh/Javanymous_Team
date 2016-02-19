package sorter;

import java.io.File;

import ID3Tag.Id3TagMp3;

public class AlbumSorter extends Sorter {

	public AlbumSorter(boolean ascending) {
		super();
		setAscending(ascending);
	}
	
	@Override
	public int compare(File o1, File o2) {
		Id3TagMp3 tag = Id3TagMp3.parse(o1);
		Id3TagMp3 tag2 = Id3TagMp3.parse(o2);
		if (isAscending()) {
			return tag.getAlbum().compareToIgnoreCase(tag2.getAlbum());
		}
		return -tag.getAlbum().compareToIgnoreCase(tag2.getAlbum());
	}

}
