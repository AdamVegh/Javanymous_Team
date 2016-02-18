package sorter;

import java.io.File;

public class AlbumSorter extends Sorter {
	boolean ascending = true;

	public AlbumSorter() {
	
	}
	
	public AlbumSorter(boolean ascending) {
		this.ascending = ascending;
	}
	
	
	@Override
	public int compare(File o1, File o2) {
		ID3Tag tag = ID3Tag.parse(ID3Tag.tail(o1));
		ID3Tag tag2 = ID3Tag.parse(ID3Tag.tail(o2));
		if (ascending) {
			return tag.getAlbum().compareToIgnoreCase(tag2.getAlbum());

		}

		return -tag.getAlbum().compareToIgnoreCase(tag2.getAlbum());
	}

}
