package demo;

import javax.swing.JDialog;

import datenbank.DBWrapper;
import gui.SpieleDialog;

public class Demo {

	public Demo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception{
		DBWrapper db = new DBWrapper();
		
		try
		{
			db.connectDB();
			db.printAll();
			
			try {
				SpieleDialog dialog = new SpieleDialog(db);
				dialog.setGames(db.getSpiele());
				dialog.aktualisieren();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		finally
		{
			db.disconnectDB();
		}

	}

}
