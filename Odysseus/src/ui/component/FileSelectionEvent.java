package ui.component;

import java.io.File;
import java.util.Collection;
import java.util.EventObject;

public class FileSelectionEvent extends EventObject {
	
	Collection<File> files;

	public FileSelectionEvent(Object source, Collection<File> files) {
		super(source);
		this.files = files;
	}
	
	public Collection<File> getFiles() {
		return this.files;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
