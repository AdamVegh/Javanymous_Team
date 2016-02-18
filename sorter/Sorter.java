package sorter;

import java.io.File;
import java.util.Comparator;


public abstract class Sorter implements Comparator<File> {
	private boolean ascending = true;

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}
}




