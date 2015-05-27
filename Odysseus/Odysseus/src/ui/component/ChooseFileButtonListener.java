package ui.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import runner.TestRunner;

public class ChooseFileButtonListener implements ActionListener {
	
	private JPanel currentPanel;
	private final String NEW_LINE = "\n";
	
	public ChooseFileButtonListener(JPanel currentPanel) {
		this.currentPanel = currentPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TestRunner runner = new TestRunner();
		File[] results = TestRunner.chooseFiles(null);
		JTextArea textArea;
		if (currentPanel.getComponent(0) instanceof JScrollPane) {
			textArea = (JTextArea) ((JScrollPane) currentPanel.getComponent(0)).getViewport().getView();
		} else {
			return;
		}
		textArea.setText("");
		for (File f : results) {
			textArea.append(f.getName() + NEW_LINE);
		}
		
		return;
	}

}
