package ui.component;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class ActionTypeBoxListener implements ActionListener {

	private JComboBox<String> box;
	public ActionTypeBoxListener(JComboBox<String> box) {
		this.box = box;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Donation".equals(box.getSelectedItem())) {
			for (Component c : box.getParent().getComponents()) {
				if (!(c instanceof JComboBox<?>)) {
					c.setVisible(true);
				}
			}	
		} else {
			int cur = 0;
			for (Component c : box.getParent().getComponents()) {
				if (cur > box.getParent().getComponentCount() - 4) {
					c.setVisible(false);
				}
				cur ++;
			}	
		}
	}

}
