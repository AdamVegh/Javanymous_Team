package sorter;

import java.io.File;

import id3tag.Id3TagMp3;

public class TitleSorter extends Sorter {

	public TitleSorter(boolean ascending) {
		super();
		setAscending(ascending);
	}
	
	@Override
	public int compare(File o1, File o2) {
		Id3TagMp3 tag = Id3TagMp3.parse(o1);
		Id3TagMp3 tag2 = Id3TagMp3.parse(o2);
		if (isAscending()) {
			return tag.getTitle().compareToIgnoreCase(tag2.getTitle());
		}
		return -tag.getTitle().compareToIgnoreCase(tag2.getTitle());
	}

}
