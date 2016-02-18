package sorter;

import java.io.File;

import ID3Tag.Id3TagMp3;

public class AlbumSorter extends Sorter {
	public AlbumSorter() {
	}
	
	public AlbumSorter(boolean ascending) {
		super();
	}
	
	@Override
	public int compare(File o1, File o2) {
		Id3TagMp3 tag = Id3TagMp3.parse(o1);
		Id3TagMp3 tag2 = Id3TagMp3.parse(o2);
		if (ascending) {
			return tag.getAlbum().compareToIgnoreCase(tag2.getAlbum());

		}

		return -tag.getAlbum().compareToIgnoreCase(tag2.getAlbum());
	}

}
