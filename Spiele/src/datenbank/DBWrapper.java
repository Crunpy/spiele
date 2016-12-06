package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import fachlogik.Spiel;
import gui.SpieleDialog;

import java.sql.Date;

public class DBWrapper {

	private ResultSet rs;
	private Connection con;
	private PreparedStatement pstmt;

	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/Spiele?user=?password=?&useSSL=false";
	private String user = "root";
	private String pw;
	private String sql;

			
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
		int nextID = 0;
		
		sql = "SELECT ID, Name, Geb FROM Spiel";
		pstmt = con.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		if(rs.last())
		{
			nextID = rs.getInt("ID") + 1;
		}
		
		sql = "INSERT INTO Spiel(ID, Name, Geb) VALUES (?, ?, ?);";
		
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, nextID);
		pstmt.setString(2, s.getName());
		pstmt.setDate(3, new java.sql.Date(s.getGe().getTime()));
		
		pstmt.executeUpdate();
	}
	
	public void deleteSpiel(Spiel s) throws Exception
	{
		
		
		sql = "DELETE FROM Spiel WHERE ID = ?;";
		
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, s.getId());
		
		pstmt.executeUpdate();
	}
}
