package sorter;

import java.io.File;
import java.util.Comparator;

public class TitleSorter extends Sorter implements Comparator<File>{
	boolean ascending = true;

	public TitleSorter(){
	
	}
	
	public TitleSorter(boolean ascending) {
		this.ascending = ascending;
	}
	
	
	@Override
	public int compare(File o1, File o2) {
		ID3Tag tag = ID3Tag.parse(ID3Tag.tail(o1));
		ID3Tag tag2 = ID3Tag.parse(ID3Tag.tail(o2));
		if (ascending) {
			return tag.getTitle().compareToIgnoreCase(tag2.getTitle());

		}

		return -tag.getTitle().compareToIgnoreCase(tag2.getTitle());
	}

}
