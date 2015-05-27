package ui.component;

import java.util.EventListener;

public interface FileSelectionListener extends EventListener {
	/** Called whenever the sun changes position
	*   in a SunEvent source object 
	*/
	public void fileSelected(FileSelectionEvent e);
}
