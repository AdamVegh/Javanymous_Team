package sorter;

import java.io.File;


public class YearSorter extends Sorter{
	
	boolean ascending = true;

	public YearSorter() {
	
	}
	
	public YearSorter(boolean ascending) {
		this.ascending = ascending;
	}

	@Override
	public int compare(File o1, File o2) {
		ID3Tag tag = ID3Tag.parse(ID3Tag.tail(o1));
		ID3Tag tag2 = ID3Tag.parse(ID3Tag.tail(o2));
		int intTag = Integer.parseInt(tag.getYear());
		int intTag2 = Integer.parseInt(tag2.getYear());
		if (ascending) {
		return intTag < intTag2 ? -1 : intTag == intTag2 ? 0 : 1;
	}
		return intTag < intTag2 ? 1 : intTag == intTag2 ? 0 : -1;

	}}
