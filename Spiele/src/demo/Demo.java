package demo;

import fachlogik.DBWrapper;

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
		}
		
		finally
		{
			db.disconnectDB();
		}

	}

}
