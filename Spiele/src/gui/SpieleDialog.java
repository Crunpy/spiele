package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
public class SpieleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private SpieleEditor editor = new SpieleEditor();
	
	private ArrayList<Spiel> games;
	
	private JList<Spiel> spieleListe = new JList<Spiel>();
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
		setBounds(100, 100, 550, 300);
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
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnSpiel = new JMenu("Spiel");
				menuBar.add(mnSpiel);
				{
					JMenuItem mntmSpielHinzufgen = new JMenuItem("Spiel hinzuf\u00FCgen");
					mnSpiel.add(mntmSpielHinzufgen);
				}
			}
		}
	}

	public SpieleEditor getEditor()
	{
		return editor;
	}

	public ArrayList<Spiel> getGames() {
		return games;
	}

	public void setGames(ArrayList<Spiel> games) {
		this.games = games;
	}
}
