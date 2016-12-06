package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import fachlogik.Spiel;
import gui.SpieleEditor;
import javax.swing.JSplitPane;
public class SpieleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private SpieleEditor editor = new SpieleEditor();
	
	private JList spieleListe = new JList();
	DefaultListModel<Spiel> spielModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SpieleDialog dialog = new SpieleDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SpieleDialog() {
		setBounds(100, 100, 550, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		spielModel = new DefaultListModel<>();
		spieleListe.setModel(spielModel);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JSplitPane splitPane = new JSplitPane();
			contentPanel.add(splitPane);
			{
				JScrollPane scrollPane = new JScrollPane();
				splitPane.setLeftComponent(scrollPane);
				{
					scrollPane.setViewportView(spieleListe);
				}
				
				JPanel panel = new JPanel();
				splitPane.setRightComponent(panel);
				{
					panel.add(editor);
				}
			}
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
