package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;

public class SpieleEditor extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public SpieleEditor() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblName = new JLabel("Name");
		add(lblName);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JLabel lblErscheinungsdatum = new JLabel("Erscheinungsdatum");
		add(lblErscheinungsdatum);
		
		JSpinner spinner = new JSpinner();
		add(spinner);

	}

}
