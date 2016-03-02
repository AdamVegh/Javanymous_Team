package connection;

import java.io.IOException;

public abstract class MenuItem {
	/**
	 * This class represents a menu-item.
	 * It can be either (sub)menu or menu-point.
	 * It cannot be instantiated without adding a label to it.
	 * It also has an instance-method called launch().
	 * If the instance is a (sub)menu, it gives User the opportunity to choose from preset menu-items.
	 * If the instance is a menu-point, launch() simply invokes preset method.  
	 */
	
	protected String label;
	public MenuItem(String label) {
		this.label = label;
	}
	
	public String getLabel() { return label; }
	
	public abstract boolean launch() throws ClassCastException, ClassNotFoundException, IOException;
	
}
