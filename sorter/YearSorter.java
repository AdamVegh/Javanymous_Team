package sorter;

import java.io.File;

import ID3Tag.Id3TagMp3;

public class YearSorter extends Sorter{
	public YearSorter() {
	}
	
	public YearSorter(boolean ascending) {
		super();
	}

	@Override
	public int compare(File o1, File o2) {
		Id3TagMp3 tag = Id3TagMp3.parse(o1);
		Id3TagMp3 tag2 = Id3TagMp3.parse(o2);
		if (ascending) {
			return tag.getYear() < tag2.getYear() ? -1 : tag.getYear() == tag2.getYear() ? 0 : 1;
		}
		return tag.getYear() < tag2.getYear() ? 1 : tag.getYear() == tag2.getYear() ? 0 : -1;

	}
}
