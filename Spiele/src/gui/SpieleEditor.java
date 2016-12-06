package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import fachlogik.Spiel;

import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import java.awt.Component;

public class SpieleEditor extends JPanel {
	private JTextField textField;
	
	private SpinnerDateModel spinnerModel = new SpinnerDateModel();

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
		spinner.setModel(spinnerModel);
		DateEditor de_spinner = new JSpinner.DateEditor(spinner, "dd.MM.yyyy");
		spinner.setEditor(de_spinner);
		add(spinner);
	}
	
	public void showSpiel(Spiel s)
	{
		textField.setText(s.getName());
		spinnerModel.setValue(s.getGe());
	}

}
