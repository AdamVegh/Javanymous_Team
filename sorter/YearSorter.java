package sorter;

import java.io.File;

import ID3Tag.Id3TagMp3;;

public class YearSorter extends Sorter {
	
	public YearSorter(boolean ascending) {
		super();
		setAscending(ascending);
	}

	@Override
	public int compare(File o1, File o2) {
		Id3TagMp3 tag = Id3TagMp3.parse(o1);
		Id3TagMp3 tag2 = Id3TagMp3.parse(o2);
		if (isAscending()) {
			return Integer.parseInt(tag.getYear()) < Integer.parseInt(tag2.getYear()) ? -1 : tag.getYear().equals(tag2.getYear()) ? 0 : 1;
		}
		return Integer.parseInt(tag.getYear()) < Integer.parseInt(tag2.getYear()) ? 1 : tag.getYear().equals(tag2.getYear()) ? 0 : -1;

	}
}
