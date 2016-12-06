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

import fachlogik.DBWrapper;
import fachlogik.Spiel;
import gui.SpieleEditor;
import javax.swing.JSplitPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class SpieleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private SpieleEditor editor = new SpieleEditor();
	
	private ArrayList<Spiel> games;
	
	private JList<Spiel> spieleListe = new JList<Spiel>();
	DefaultListModel<Spiel> spielModel;
	
	private DBWrapper database;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SpieleDialog dialog = new SpieleDialog(new DBWrapper());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SpieleDialog(DBWrapper db){
		setBounds(100, 100, 550, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(6, 6, 6, 6));
		this.database = db;
		
		spielModel = new DefaultListModel<>();
		spieleListe.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				editor.showSpiel(spieleListe.getSelectedValue());
			}
		});
		spieleListe.setModel(spielModel);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			splitPane.setDividerLocation(200);
			contentPanel.add(splitPane);
			{
				JScrollPane scrollPane = new JScrollPane();
				splitPane.setLeftComponent(scrollPane);
				{
					scrollPane.setViewportView(spieleListe);
				}
				
				splitPane.setRightComponent(editor);
				{
					//panel.add(editor);
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
					mntmSpielHinzufgen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try
							{
								db.connectDB();
								db.addSpiel(new Spiel());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							finally
							{
								try {
									db.disconnectDB();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					});
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
	
	public void aktualisieren()
	{
		spielModel.clear();
		ArrayList<Spiel> gameslist = getGames();
		
		for(Spiel s: gameslist)
		{
			spielModel.addElement(s);
		}	
	}
}
