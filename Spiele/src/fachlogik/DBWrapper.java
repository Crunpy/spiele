package fachlogik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

public class DBWrapper {

	private ResultSet rs;
	private Connection con;
	private PreparedStatement pstmt;
	
	private String init = "DROP DATABASE IF EXISTS Spiele;";

	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/Spiele?user=?password=?&useSSL=false";
	private String user = "root";
	private String pw;
	private String sql;

	public DBWrapper()
	{
		init += "CREATE DATABASE Spiele;\n";

		init += "USE Spiele;\n";

		init += "CREATE TABLE Spiel\n";
		init += "(\n";
		init += "	ID INT PRIMARY KEY,\n";
		init += "   Name VARCHAR(50),\n";
		init += "    Geb DATE\n";
		init += ");\t";
		init += "INSERT INTO Spiel(ID, Name, Geb) VALUES\n";
		init += "(1, 'Halo', '2002-03-14'),\n";
		init += "(2, 'Red Dead Redemption', '2010-05-21'),\n";
		init += "(3, 'Mass Effect', '2007-11-21'),\n";
		init += "(4, 'League of Legends','2009-10-27'),\n";
		init += "(5, 'Diablo 3', '2014-08-19');\n";
	}
			
	public void connectDB() throws Exception
	{
		Class.forName(driver);
		System.out.println("Treiberklasse ist geladen!");
		
		con = DriverManager.getConnection(url, user, pw);
		System.out.println("Verbindung ist aufgebaut!");
	}
	
	public void disconnectDB() throws Exception
	{
		if(rs!=null)
		{
			rs.close();
		}
		
		if(pstmt!=null)
		{
			pstmt.close();
		}
		
		if(con!=null)
		{
			con.close();
		}
		
		System.out.println("Verbindung ist geschlossen!");
	}
	
	public void printAll() throws Exception
	{
		sql = "SELECT ID, Name, Geb FROM Spiel";
		pstmt = con.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			int id = rs.getInt("ID");
			String name = rs.getString("Name");
			Date geb = rs.getDate("Geb");
			
			System.out.println(id + "\t" + name + "\t" + geb);
		}
	}
	
	public void fill() throws Exception
	{
		pstmt = con.prepareStatement(init);
		pstmt.execute();
	}
	
	public ArrayList<Spiel> getSpiele() throws Exception
	{
		
		ArrayList<Spiel> games = new ArrayList<Spiel>();
		sql = "SELECT ID, Name, Geb FROM Spiel";
		pstmt = con.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			Spiel s = new Spiel();
			s.setId(rs.getInt("ID"));
			s.setName(rs.getString("Name"));
			s.setGe(rs.getDate("Geb"));
			
			games.add(s);
		}
		
		return games;
	}
	
	public void addSpiel(Spiel s) throws Exception
	{
		sql = "INSERT INTO Spiel(ID, Name, Geb) VALUES ?, '?', '?'";
		
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, s.getId());
		pstmt.setString(2, s.getName());
		pstmt.setDate(3, (Date) s.getGe());
		
		pstmt.executeQuery();
	}
}
