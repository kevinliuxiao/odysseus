package ui.component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Terminator extends WindowAdapter {

	public Terminator() {
		
	}

	public void windowClosing(WindowEvent e) {
	    System.exit(0); 
	}


}
